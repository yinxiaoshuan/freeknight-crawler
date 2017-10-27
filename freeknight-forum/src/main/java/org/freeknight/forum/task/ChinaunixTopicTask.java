
package org.freeknight.forum.task;

import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.freeknight.forum.resource.ChinaunixTargetResource;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.forum.task.parser.ChinaunixTopicParser;

public class ChinaunixTopicTask
		extends TopicTask
{

	public ChinaunixTopicTask (
			TopicTaskInModel taskInModel ) {
		super ( taskInModel );
		this.resource = new ChinaunixTargetResource ( );
		this.topicParser = new ChinaunixTopicParser ( );
	}

	@Override
	protected TopicTask getTopicTask (
			final TopicTaskInModel taskInModel )
	{
		return new ChinaunixTopicTask ( taskInModel );
	}

	public List< TopicTaskOutModel > call ( ) throws Exception
	{
		/* final HttpHost proxy = new HttpHost ( "183.45.173.218", 8088 ); */
		final CloseableHttpClient httpClient = HttpClients.createDefault ( );
		final HttpGet httpGet = new HttpGet ( this.taskInModel.getTarget ( ) );
		httpGet.addHeader ( "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8" );
		httpGet
				.addHeader (
						HTTP.USER_AGENT,
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36" );
		httpGet.addHeader ( HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE );

		final String respBody = httpClient.execute ( /* proxy, */httpGet, new TopicResponseHandler ( ) );
		final List< TopicTaskOutModel > topics = topicParser.doParse ( respBody, null );
		return topics;
	}

}
