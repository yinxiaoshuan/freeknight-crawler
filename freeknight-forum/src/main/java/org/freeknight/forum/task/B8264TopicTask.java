
package org.freeknight.forum.task;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.freeknight.forum.resource.B8264TargetResource;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.forum.task.parser.B8264TopicParser;

public class B8264TopicTask
		extends TopicTask
{

	public B8264TopicTask (
			TopicTaskInModel taskInModel ) {
		super ( taskInModel );
		this.resource = new B8264TargetResource ( );
		this.topicParser = new B8264TopicParser ( );
	}

	@Override
	protected TopicTask getTopicTask (
			TopicTaskInModel taskInModel )
	{
		return new B8264TopicTask ( taskInModel );
	}

	public List< TopicTaskOutModel > call ( ) throws Exception
	{
		final CloseableHttpClient httpClient = HttpClients.createDefault ( );
		final HttpGet httpGet = new HttpGet ( taskInModel.getTarget ( ) );
		String host = parseHost ( );
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
						"Bz6S_ee0d_lastvisit=1507764652; bdshare_firstime=1507768270640; showboxtime=1508118156000; Bz6S_ee0d_recent_forums=106%2C158%2C53; Bz6S_ee0d_sid=8FyJ96; Bz6S_ee0d_lastact=1508068931%09index.php%09; Bz6S_ee0d_editormode_e=-1; Hm_lvt_49299785f8cc72bacc96c9a5109227da=1507782099,1507785946,1507794271,1508028230; Hm_lpvt_49299785f8cc72bacc96c9a5109227da=1508068931" );

		final String respBody = httpClient.execute ( httpGet, new TopicResponseHandler ( "gbk" ) );
		List< TopicTaskOutModel > topics = topicParser.doParse ( respBody, null );
		return topics;
	}

	private String parseHost ( )
	{
		String host = null;
		try
		{
			URL url = new URL ( taskInModel.getTarget ( ) );
			host = url.getHost ( );
		}
		catch ( MalformedURLException e )
		{
			e.printStackTrace ( );
		}
		return host;
	}
}
