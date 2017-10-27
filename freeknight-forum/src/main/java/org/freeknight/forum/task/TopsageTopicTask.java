
package org.freeknight.forum.task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.forum.task.parser.Parser;
import org.freeknight.forum.task.parser.TopsageTopicParser;
import org.freeknight.framework.crawler.db.MySQLHelper;
import org.freeknight.framework.crawler.db.entity.TopicEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 主题/帖子列表任务.
 * 
 * @author yrj
 *
 */
public class TopsageTopicTask
		extends TopicTask
{

	Logger																										logger			= LoggerFactory.getLogger ( TopsageTopicTask.class );

	private TopicTaskInModel																	taskInModel;

	private Parser< String, Void, List< TopicTaskOutModel >>	topicParser	= new TopsageTopicParser ( );

	public TopsageTopicTask (
			TopicTaskInModel taskInModel ) {
		super ( taskInModel );
		this.taskInModel = taskInModel;
	}

	@Override
	public void run ( )
	{
		String respBody = fetch ( );
		List< TopicTaskOutModel > topics = topicParser.doParse ( respBody, null );
		if ( topics == null || topics.isEmpty ( ) )
		{
			logger.warn ( Thread.currentThread ( ).getName ( )
					+ ", forum: "
					+ taskInModel.getForumName ( )
					+ ", target: "
					+ taskInModel.getTarget ( )
					+ ", no exist resources." );
			return;
		}

		boolean isOk = insertDB ( topics );
		if ( isOk )
		{
			logger.info ( Thread.currentThread ( ).getName ( )
					+ ", forum: "
					+ taskInModel.getForumName ( )
					+ ", target: "
					+ taskInModel.getTarget ( )
					+ ", total: "
					+ topics.size ( ) );
		}
	}

	private String fetch ( )
	{
		RequestConfig config = RequestConfig.custom ( )
		/* .setConnectionRequestTimeout ( 10000 ) */.setConnectTimeout ( 15000 ).setSocketTimeout ( 15000 ).build ( );
		final HttpClient httpClient = HttpClients.custom ( ).setDefaultRequestConfig ( config ).build ( );

		final HttpGet httpGet = new HttpGet ( taskInModel.getTarget ( ) );
		httpGet.addHeader ( "Accept", "*/*" );
		httpGet.addHeader ( HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE );
		httpGet.addHeader ( HTTP.USER_AGENT, "Mozilla/5.0 (X11; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0" );

		String respBody = "";
		try
		{
			respBody = httpClient.execute ( httpGet, new TopicResponseHandler ( ) );
		}
		catch ( IOException e )
		{
			// throw new FetchException ( "fetch failture. forum: "
			// + taskInModel.getForumName ( )
			// + ", target: "
			// + taskInModel.getTarget ( ), e );
			logger.error ( "fetch failture. forum: " + taskInModel.getForumName ( ) + ", target: " + taskInModel.getTarget ( ), e );
		}
		return respBody;
	}

	private boolean insertDB (
			List< TopicTaskOutModel > topics )
	{
		List< TopicEntity > entities = new ArrayList< TopicEntity > ( );
		for ( final TopicTaskOutModel topic : topics )
		{
			TopicEntity entity = new TopicEntity ( );
			entity.setForumId ( taskInModel.getForumId ( ) );
			entity.setTopicId ( topic.getTopicId ( ) );
			entity.setTitle ( topic.getTitle ( ) );
			entity.setIssueTime ( topic.issueTime ( ) );
			entity.setAuthorId ( topic.getAuthorId ( ) );
			entity.setAuthorName ( topic.getAuthor ( ) );
			entity.setReplyNum ( topic.getReplyNum ( ) );
			entity.setReadNum ( topic.getReadNum ( ) );
			entities.add ( entity );
		}

		boolean iok = true;
		try
		{
			MySQLHelper.insertTopics ( entities );
		}
		catch ( SQLException e )
		{
			iok = false;
			logger
					.error ( "insert db failture. forum: " + taskInModel.getForumName ( ) + ", target: " + taskInModel.getTarget ( ), e );
		}
		return iok;
	}
}
