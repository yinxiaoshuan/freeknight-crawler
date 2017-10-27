
package org.freeknight.forum.task;

import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.freeknight.forum.resource.XCarTargetResource;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.forum.task.parser.XCarTopicParser;

public class XCarTopicTask
		extends TopicTask
{

	public XCarTopicTask (
			TopicTaskInModel taskInModel ) {
		super ( taskInModel );
		this.resource = new XCarTargetResource ( );
		this.topicParser = new XCarTopicParser ( );
	}

	@Override
	protected TopicTask getTopicTask (
			final TopicTaskInModel taskInModel )
	{
		return new XCarTopicTask ( taskInModel );
	}

	public List< TopicTaskOutModel > call ( ) throws Exception
	{
		final CloseableHttpClient httpClient = HttpClients.createDefault ( );
		final HttpGet httpGet = new HttpGet ( taskInModel.getTarget ( ) );
		httpGet.addHeader ( "Host", "www.xcar.com.cn" );
		httpGet.addHeader ( "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8" );
		httpGet.addHeader ( "Accept-Encoding", "gzip, deflate" );
		httpGet.addHeader ( "Upgrade-Insecure-Requests", "1" );
		httpGet.addHeader ( "Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6" );
		httpGet
				.addHeader (
						"User-Agent",
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36" );
		httpGet
				.addHeader (
						"Cookie",
						"TY_SESSION_ID=b6fc1dbd-ee7d-4686-a80c-2fc621e4e298; _fwck_club=a54e696045a1e6672b0ba68d3907b94a; _appuv_club=38581dbe9f74ccfb3c4d397ae1eeaa04; _Xdwnewuv=1; _PVXuv=59d7bc6b28783; _fwck_www=76d9275bac3d4a1da1f5c70c93bf184c; _appuv_www=02e4613e3908eaf0c82703e770e8764c; _Xdwuv=5073106986317; _fwck_tools=4a4f657cef4661809de7f969e3d91bdf; _appuv_tools=d8e7c170a9d16dbe4d93718667ee099c; BIGipServerpool-c26-xcar-bbsweb-80=197988106.20480.0000; ad__city=475; uv_firstv_refer=%28%243%29%281%29%3Ffid%3D931; bbs_sid=bGL6h6; uv_firstv_refers=http%3A//www.xcar.com.cn/bbs/forumdisplay.php%3Ffid%3D931; bbs_visitedfid=540D931D1984D1158D1974D1997D2000D874D589D1859D93D1807D1151; place_prid=1; place_crid=475; place_ip=111.197.112.23_1; _Xdwstime=1507637473; Hm_lvt_53eb54d089f7b5dd4ae2927686b183e0=1507310706,1507622862; Hm_lpvt_53eb54d089f7b5dd4ae2927686b183e0=1507637473" );

		final String respBody = httpClient.execute ( httpGet, new TopicResponseHandler ( "gbk" ) );
		List< TopicTaskOutModel > taskOutModels = topicParser.doParse ( respBody, null );
		return taskOutModels;
	}
}
