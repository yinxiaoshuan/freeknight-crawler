
package org.freeknight.forum.daemon;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.freeknight.forum.task.TopicResponseHandler;
import org.freeknight.forum.task.TopicTask;
import org.freeknight.forum.task.XCarTopicTask;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTotalPageTaskInModel;
import org.freeknight.forum.task.parser.Parser;
import org.freeknight.forum.task.parser.XCarTotalPageParser;

public class XCarTopicWorker
		extends AbstractVirtualWorker
{

	private Parser< String, Void, Integer >	topicTotalPageParser;

	public XCarTopicWorker ( ) {
		this.topicTotalPageParser = new XCarTotalPageParser ( );
	}

	@Override
	public String getName ( )
	{
		return DaemonWorkerEngine.XCAR.getForumName ( );
	}

	@Override
	protected TopicTask assemblyTopicTask (
			TopicTaskInModel taskInModel )
	{
		return new XCarTopicTask ( taskInModel );
	}

	@Override
	protected int doPreTask (
			TopicTotalPageTaskInModel totalPageTaskInModel )
	{
		final CloseableHttpClient httpClient = HttpClients.createDefault ( );
		final HttpGet httpGet = new HttpGet ( totalPageTaskInModel.getTarget ( ) );
		httpGet.addHeader ( "Host", "www.xcar.com.cn" );
		httpGet.addHeader ( "Accept", "text/html;charset=UTF-8" );
		httpGet.addHeader ( "Accept-Encoding", "gzip, deflate" );
		httpGet.addHeader ( "Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6" );
		httpGet
				.addHeader (
						"User-Agent",
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36" );
		httpGet
				.addHeader (
						"Cookie",
						"TY_SESSION_ID=4b76a28a-5b42-4368-a65c-415cad139ceb; _fwck_club=a54e696045a1e6672b0ba68d3907b94a; _appuv_club=38581dbe9f74ccfb3c4d397ae1eeaa04; _Xdwnewuv=1; _PVXuv=59d7bc6b28783; _fwck_www=76d9275bac3d4a1da1f5c70c93bf184c; _appuv_www=02e4613e3908eaf0c82703e770e8764c; BIGipServerpool-c26-xcar-bbsweb-80=197988106.20480.0000; _Xdwuv=5073106986317; _fwck_tools=4a4f657cef4661809de7f969e3d91bdf; _appuv_tools=d8e7c170a9d16dbe4d93718667ee099c; _locationInfo_=%7Burl%3A%22h%22%2Ccity_id%3A%22475%22%2Cprovince_id%3A%221%22%2C%20city_name%3A%22%25E5%258C%2597%25E4%25BA%25AC%22%7D; ad__city=475; bbs_visitedfid=176D857D91D266D305D866D1312D560D514D562D1975D1700D1696; place_prid=1; place_crid=475; place_ip=111.197.22.25_1; bbs_sid=FQezPo; uv_firstv_refers=http%3A//www.xcar.com.cn/bbs/car.htm; uv_firstv_refer=%28%243%29/bbs; _Xdwstime=1507379486; Hm_lvt_53eb54d089f7b5dd4ae2927686b183e0=1507310706; Hm_lpvt_53eb54d089f7b5dd4ae2927686b183e0=1507379486" );

		String respBody = null;
		try
		{
			respBody = httpClient.execute ( httpGet, new TopicResponseHandler ( "gbk" ) );
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
