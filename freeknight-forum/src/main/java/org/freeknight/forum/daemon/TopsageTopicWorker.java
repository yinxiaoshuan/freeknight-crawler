
package org.freeknight.forum.daemon;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.freeknight.forum.task.TopicResponseHandler;
import org.freeknight.forum.task.TopicTask;
import org.freeknight.forum.task.TopsageTopicTask;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTotalPageTaskInModel;
import org.freeknight.forum.task.parser.Parser;
import org.freeknight.forum.task.parser.TopsageTotalPageParser;

public class TopsageTopicWorker
		extends AbstractVirtualWorker
{
	private Parser< String, Void, Integer >	topicTotalPageParser;

	public TopsageTopicWorker ( ) {
		this.topicTotalPageParser = new TopsageTotalPageParser ( );
	}

	@Override
	public String getName ( )
	{
		return DaemonWorkerEngine.TOPSAGE.getForumName ( );
	}

	@Override
	protected TopicTask assemblyTopicTask (
			TopicTaskInModel taskInModel )
	{
		return new TopsageTopicTask ( taskInModel );
	}

	@Override
	protected int doPreTask (
			TopicTotalPageTaskInModel totalPageTaskInModel )
	{
		final CloseableHttpClient httpClient = HttpClients.createDefault ( );
		final HttpGet httpGet = new HttpGet ( totalPageTaskInModel.getTarget ( ) );
		httpGet.addHeader ( "Accept", "*/*" );
		httpGet.addHeader ( HTTP.USER_AGENT, "Mozilla/5.0 (X11; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0" );
		httpGet.addHeader ( HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE );
		httpGet.addHeader ( HTTP.CONTENT_ENCODING, "gzip,deflate" );

		String respBody = "";
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
