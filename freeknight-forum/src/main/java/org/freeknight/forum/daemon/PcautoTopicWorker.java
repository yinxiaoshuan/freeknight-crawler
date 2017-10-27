
package org.freeknight.forum.daemon;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.freeknight.forum.task.PcautoTopicTask;
import org.freeknight.forum.task.TopicResponseHandler;
import org.freeknight.forum.task.TopicTask;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTotalPageTaskInModel;
import org.freeknight.forum.task.parser.Parser;
import org.freeknight.forum.task.parser.PcautoTotalPageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PcautoTopicWorker
		extends AbstractVirtualWorker
{

	Logger																	logger	= LoggerFactory.getLogger ( PcautoTopicWorker.class );

	private Parser< String, Void, Integer >	topicTotalPageParser;

	public PcautoTopicWorker ( ) {
		this.topicTotalPageParser = new PcautoTotalPageParser ( );
	}

	@Override
	public String getName ( )
	{
		return DaemonWorkerEngine.PCAUTO.getForumName ( );
	}

	@Override
	protected TopicTask assemblyTopicTask (
			TopicTaskInModel taskInModel )
	{
		return new PcautoTopicTask ( taskInModel );
	}

	@Override
	protected int doPreTask (
			TopicTotalPageTaskInModel totalPageTaskInModel )
	{
		final CloseableHttpClient httpClient = HttpClients.createDefault ( );
		final HttpGet httpGet = new HttpGet ( totalPageTaskInModel.getTarget ( ) );
		httpGet.addHeader ( "Host", "bbs.pcauto.com.cn" );
		httpGet.addHeader ( "Accept", "text/html;charset=UTF-8" );
		httpGet.addHeader ( "Accept-Encoding", "gzip, deflate" );
		httpGet.addHeader ( "Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6" );
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

		final Integer totalpage = topicTotalPageParser.doParse ( respBody, null );
		return totalpage;
	}

	@Override
	protected void doProcess (
			TopicTask task )
	{
	}

}
