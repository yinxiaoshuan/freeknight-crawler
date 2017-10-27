
package org.freeknight.forum.task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.freeknight.forum.resource.B55TargetResource;
import org.freeknight.forum.resource.TargetResource;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.forum.task.parser.B55TopicParser;
import org.freeknight.forum.task.parser.Parser;
import org.freeknight.framework.crawler.db.MySQLHelper;
import org.freeknight.framework.crawler.db.entity.TopicEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B55TopicTask
		extends TopicTask
		implements Runnable
{

	Logger																						logger	= LoggerFactory.getLogger ( B55TopicTask.class );

	Parser< String, Long, List< TopicTaskOutModel > >	topicParser;

	private TopicTaskInModel													taskInModel;

	private TargetResource														targetResource;

	public B55TopicTask (
			TopicTaskInModel taskInModel ) {
		super ( taskInModel );
		this.targetResource = new B55TargetResource ( );
		this.taskInModel = taskInModel;
		this.topicParser = new B55TopicParser ( );
	}

	@Override
	public void run ( )
	{
		String target =
				targetResource.getTarget ( taskInModel.getForumSrcId ( ), taskInModel.getTarget ( ), taskInModel.getTotalPage ( ) );
		List< TopicTaskOutModel > topics = null;
		try
		{
			topics = doFetch ( target );
			logger.info ( taskInModel.getForumName ( ) + "Fetch Target: " + target + ".ok, total: " + topics.size ( ) );
		}
		catch ( IOException e )
		{
			e.printStackTrace ( );
		}

		if ( topics != null )
		{
			List< TopicEntity > entities = new ArrayList< TopicEntity > ( );
			for ( TopicTaskOutModel topic : topics )
			{
				TopicEntity entity = new TopicEntity ( );
				entity.setForumId ( taskInModel.getForumId ( ) );
				entity.setTopicId ( topic.getTopicId ( ) );
				entity.setTitle ( topic.getTitle ( ) );
				entity.setAuthorId ( topic.getAuthorId ( ) );
				entity.setAuthorName ( topic.getAuthor ( ) );
				entity.setIssueTime ( topic.issueTime ( ) );
				entity.setReplyNum ( topic.getReplyNum ( ) );
				entity.setReadNum ( topic.getReadNum ( ) );
				entities.add ( entity );
			}

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

	private List< TopicTaskOutModel > doFetch (
			String target ) throws IOException
	{
		final CloseableHttpClient httpClient = HttpClients.createDefault ( );
		final HttpGet httpGet = new HttpGet ( target );
		httpGet
				.addHeader (
						HTTP.USER_AGENT,
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36" );
		httpGet.addHeader ( HTTP.TARGET_HOST, "bbs.55bbs.com" );
		httpGet.addHeader ( HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE );
		httpGet.addHeader ( HTTP.CONTENT_ENCODING, "gzip,deflate" );
		httpGet.addHeader ( "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8" );
		httpGet
				.addHeader (
						"Cookie",
						"bdshare_firstime=1507742893140; 5Mg_sid=25AMDO; Hm_lvt_8b5ba22d8a39ed5f13c34402db63b51a=1507742790,1508060833; Hm_lpvt_8b5ba22d8a39ed5f13c34402db63b51a=1508074828; 55bbs_pageWidth=1665%7C2329; z_pro_city=s_provice%3Dbeijing%26s_city%3Dchaoyangqu; 5Mg_visitedfid=8D180D37D17D10D7D16D46D205D207D47; ANALYSIS_UV_KEY=1a469ac-b8fb-eba9-2fb4-a5c6-2799a09%7C1508160174383; Hm_lvt_85b3587e89215570b2de602fb8bd93c0=1507742794,1508060833; Hm_lpvt_85b3587e89215570b2de602fb8bd93c0=1508160174; urltarget=1; Hm_lvt_dbf7966275372bffa3846649aa53f35a=1507742790,1508060833; Hm_lpvt_dbf7966275372bffa3846649aa53f35a=1508160175" );

		final String respBody = httpClient.execute ( httpGet, new TopicResponseHandler ( "gbk" ) );
		final List< TopicTaskOutModel > topics = this.topicParser.doParse ( respBody, taskInModel.getForumId ( ) );
		return topics;
	}

}
