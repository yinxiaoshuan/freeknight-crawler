
package org.freeknight.forum.daemon;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.freeknight.forum.task.SouthcnTopicTask;
import org.freeknight.forum.task.TopicResponseHandler;
import org.freeknight.forum.task.TopicTask;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTotalPageTaskInModel;
import org.freeknight.forum.task.parser.Parser;
import org.freeknight.forum.task.parser.SouthcnTotalPageParser;

public class SouthcnTopicWorker
		extends AbstractVirtualWorker
{

	private Parser< String, Void, Integer >	topicTotalPageParser;

	public SouthcnTopicWorker ( ) {
		this.topicTotalPageParser = new SouthcnTotalPageParser ( );
	}

	@Override
	public String getName ( )
	{
		return DaemonWorkerEngine.SOUTHCN.getForumName ( );
	}

	@Override
	protected TopicTask assemblyTopicTask (
			TopicTaskInModel taskInModel )
	{
		return new SouthcnTopicTask ( taskInModel );
	}

	@Override
	protected int doPreTask (
			TopicTotalPageTaskInModel totalPageTaskInModel )
	{
		final CloseableHttpClient httpClient = HttpClients.createDefault ( );
		final HttpGet httpGet = new HttpGet ( totalPageTaskInModel.getTarget ( ) );
		httpGet.addHeader ( "Accept", "*/*" );

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
