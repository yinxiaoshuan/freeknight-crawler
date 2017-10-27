
package org.freeknight.forum.daemon;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.freeknight.forum.task.B8264TopicTask;
import org.freeknight.forum.task.TopicResponseHandler;
import org.freeknight.forum.task.TopicTask;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTotalPageTaskInModel;
import org.freeknight.forum.task.parser.B8264TotalPageParser;
import org.freeknight.forum.task.parser.Parser;

public class B8264TopicWorker
		extends AbstractVirtualWorker
{

	private Parser< String, Void, Integer >	topicTotalPageParser;

	public B8264TopicWorker ( ) {
		this.topicTotalPageParser = new B8264TotalPageParser ( );
	}

	@Override
	public String getName ( )
	{
		return DaemonWorkerEngine.$8264.getForumName ( );
	}

	@Override
	protected TopicTask assemblyTopicTask (
			TopicTaskInModel taskInModel )
	{
		return new B8264TopicTask ( taskInModel );
	}

	@Override
	protected int doPreTask (
			TopicTotalPageTaskInModel totalPageTaskInModel )
	{
		final CloseableHttpClient httpClient = HttpClients.createDefault ( );
		final HttpGet httpGet = new HttpGet ( totalPageTaskInModel.getTarget ( ) );

		String host = parseHost ( totalPageTaskInModel.getTarget ( ) );
		httpGet
				.addHeader (
						HTTP.USER_AGENT,
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36" );
		httpGet.addHeader ( HTTP.TARGET_HOST, host );
		httpGet.addHeader ( HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE );
		httpGet.addHeader ( HTTP.CONTENT_ENCODING, "gzip,deflate" );
		httpGet.addHeader ( "Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6" );
		httpGet.addHeader ( "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8" );
		httpGet
				.addHeader (
						"Cookie",
						"Bz6S_ee0d_lastvisit=1507764652; bdshare_firstime=1507768270640; Bz6S_ee0d_oldtopics=D5444891D; Bz6S_ee0d_fid12=1508009517; Bz6S_ee0d_sid=2lOC2J; Bz6S_ee0d_lastact=1508031718%09forum.php%09forumdisplay; Bz6S_ee0d_editormode_e=-1; Bz6S_ee0d_recent_forums=12%2C166%2C60; Hm_lvt_49299785f8cc72bacc96c9a5109227da=1507782099,1507785946,1507794271,1508028230; Hm_lpvt_49299785f8cc72bacc96c9a5109227da=1508031719" );

		String respBody = null;
		try
		{
			respBody = httpClient.execute ( httpGet, new TopicResponseHandler ( "gbk" ) );
		}
		catch ( IOException e )
		{
			logger.error ( "Pre-Task Exception occurred, Ignore it.", e );
		}
		final int maxPage = topicTotalPageParser.doParse ( respBody, null );
		return maxPage;
	}

	@Override
	protected void doProcess (
			TopicTask task )
	{
	}

	private String parseHost (
			String target )
	{
		String host = null;
		try
		{
			URL url = new URL ( target );
			host = url.getHost ( );
		}
		catch ( MalformedURLException e )
		{
			e.printStackTrace ( );
		}
		return host;
	}
}
