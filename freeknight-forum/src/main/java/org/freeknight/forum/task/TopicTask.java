
package org.freeknight.forum.task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.freeknight.forum.resource.TargetResource;
import org.freeknight.forum.resource.TopicTargetResource;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.forum.task.parser.Parser;
import org.freeknight.forum.task.parser.TopicTaskParser;
import org.freeknight.framework.crawler.db.MySQLHelper;
import org.freeknight.framework.crawler.db.entity.TopicEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicTask
		implements Runnable
{

	Logger						logger	= LoggerFactory.getLogger ( TopicTask.class );

	TopicTaskInModel	taskInModel;

	public TopicTaskInModel getTaskInModel ( )
	{
		return taskInModel;
	}

	TargetResource																		resource;

	Parser< String, Void, List< TopicTaskOutModel > >	topicParser;

	protected TopicTask getTopicTask (
			final TopicTaskInModel taskInModel )
	{
		return new TopicTask ( taskInModel );
	}

	public TopicTask (
			final TopicTaskInModel taskInModel ) {
		this.taskInModel = taskInModel;
		this.resource = new TopicTargetResource ( );
		this.topicParser = new TopicTaskParser ( );
	}

	@Override
	public void run ( )
	{
		final int maxPage = taskInModel.getTotalPage ( );

		// earlyTerm: 提前结束.
		// 类似铁血网站, 分页存在Bug, 后续页码会不显示主题内容. 进行提前结束.
		boolean earlyTerm = false;
		for ( int pageNum = 1; pageNum <= maxPage; pageNum++ )
		{
			if ( earlyTerm )
			{
				break;
			}

			String target = resource.getTarget ( taskInModel.getForumSrcId ( ), taskInModel.getTarget ( ), pageNum );
			List< TopicTaskOutModel > taskOutModels = null;
			try
			{
				taskOutModels = fetch ( target );
			}
			catch ( Exception e )
			{
				logger.error ( "" );
				continue;
			}

			List< TopicEntity > entities = new ArrayList< TopicEntity > ( );
			for ( TopicTaskOutModel topic : taskOutModels )
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
			if ( entities.size ( ) == 1 )
			{
				earlyTerm = true;// 提前结束
			}
			logger.info ( "forum: " + taskInModel.getForumName ( ) + ", target: " + target + ", total: " + entities.size ( ) );

			try
			{
				MySQLHelper.insertTopics ( entities );
			}
			catch ( SQLException e )
			{
				e.printStackTrace ( );
			}

		}
	}

	public List< TopicTaskOutModel > fetch (
			String target ) throws Exception
	{
		/* final HttpHost proxy = new HttpHost ( "119.5.0.2", 22 ); */
		final CloseableHttpClient httpClient = HttpClients.createDefault ( );
		final HttpGet httpGet = new HttpGet ( target );
		httpGet
				.addHeader (
						HTTP.USER_AGENT,
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36" );
		httpGet.addHeader ( HTTP.TARGET_HOST, "club.autohome.com.cn" );
		httpGet.addHeader ( HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE );
		httpGet.addHeader ( HTTP.CONTENT_ENCODING, "gzip,deflate" );

		final String respBody = httpClient.execute ( httpGet, new TopicResponseHandler ( target ) );
		final List< TopicTaskOutModel > topics = topicParser.doParse ( respBody, null );
		return topics;
	}
}
