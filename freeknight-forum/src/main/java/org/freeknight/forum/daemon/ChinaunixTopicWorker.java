
package org.freeknight.forum.daemon;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.freeknight.forum.task.ChinaunixTopicTask;
import org.freeknight.forum.task.TopicResponseHandler;
import org.freeknight.forum.task.TopicTask;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTotalPageTaskInModel;
import org.freeknight.forum.task.parser.ChinaunixTotalPageParser;
import org.freeknight.forum.task.parser.Parser;

public class ChinaunixTopicWorker
		extends AbstractVirtualWorker
{

	private Parser< String, Void, Integer >	topicTotalPageParser;

	public ChinaunixTopicWorker ( ) {
		this.topicTotalPageParser = new ChinaunixTotalPageParser ( );
	}

	@Override
	public String getName ( )
	{
		return DaemonWorkerEngine.CHINAUNIX.getForumName ( );
	}

	@Override
	protected TopicTask assemblyTopicTask (
			TopicTaskInModel taskInModel )
	{
		return new ChinaunixTopicTask ( taskInModel );
	}

	@Override
	protected int doPreTask (
			TopicTotalPageTaskInModel totalPageTaskInModel )
	{
		final CloseableHttpClient httpClient = HttpClients.createDefault ( );
		final HttpGet httpGet = new HttpGet ( totalPageTaskInModel.getTarget ( ) );
		httpGet.addHeader ( "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8" );
		httpGet
				.addHeader (
						"User-Agent",
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36" );

		String respBody = null;
		try
		{
			respBody = httpClient.execute ( httpGet, new TopicResponseHandler ( ) );
		}
		catch ( IOException e )
		{
			logger.error ( "Pre-Task Exception occurred, Ignore it.", e );
		}

		int totalPage = topicTotalPageParser.doParse ( respBody, null );
		return totalPage;
	}

	@Override
	protected void doProcess (
			TopicTask task )
	{
	}

}
