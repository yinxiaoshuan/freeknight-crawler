
package org.freeknight.forum.daemon;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.freeknight.forum.task.B55TopicTask;
import org.freeknight.forum.task.TopicResponseHandler;
import org.freeknight.forum.task.TopicTask;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTotalPageTaskInModel;
import org.freeknight.forum.task.parser.B55TotalPageParser;
import org.freeknight.forum.task.parser.Parser;

public class B55TopicWorker
		extends AbstractVirtualWorker
{

	private Parser< String, Void, Integer >	topicTotalPageParser;

	public B55TopicWorker ( ) {
		this.topicTotalPageParser = new B55TotalPageParser ( );
	}

	@Override
	public String getName ( )
	{
		return DaemonWorkerEngine.BBS55.getForumName ( );
	}

	@Override
	protected TopicTask assemblyTopicTask (
			TopicTaskInModel taskInModel )
	{
		return new B55TopicTask ( taskInModel );
	}

	@Override
	protected int doPreTask (
			TopicTotalPageTaskInModel totalPageTaskInModel )
	{
		final CloseableHttpClient httpClient = HttpClients.createDefault ( );
		final HttpGet httpGet = new HttpGet ( totalPageTaskInModel.getTarget ( ) );

		httpGet
				.addHeader (
						HTTP.USER_AGENT,
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36" );
		httpGet.addHeader ( HTTP.TARGET_HOST, "bbs.55bbs.com" );
		httpGet.addHeader ( HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE );
		httpGet.addHeader ( HTTP.CONTENT_ENCODING, "gzip,deflate" );
		httpGet
				.addHeader (
						"Cookie",
						"bdshare_firstime=1507742893140; 5Mg_sid=25AMDO; Hm_lvt_8b5ba22d8a39ed5f13c34402db63b51a=1507742790,1508060833; Hm_lpvt_8b5ba22d8a39ed5f13c34402db63b51a=1508074828; 55bbs_pageWidth=1665%7C2329; z_pro_city=s_provice%3Dbeijing%26s_city%3Dchaoyangqu; 5Mg_visitedfid=8D180D37D17D10D7D16D46D205D207D47; ANALYSIS_UV_KEY=1a469ac-b8fb-eba9-2fb4-a5c6-2799a09%7C1508160174383; Hm_lvt_85b3587e89215570b2de602fb8bd93c0=1507742794,1508060833; Hm_lpvt_85b3587e89215570b2de602fb8bd93c0=1508160174; urltarget=1; Hm_lvt_dbf7966275372bffa3846649aa53f35a=1507742790,1508060833; Hm_lpvt_dbf7966275372bffa3846649aa53f35a=1508160175" );

		String respBody = null;
		try
		{
			respBody = httpClient.execute ( httpGet, new TopicResponseHandler ( ) );
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

}
