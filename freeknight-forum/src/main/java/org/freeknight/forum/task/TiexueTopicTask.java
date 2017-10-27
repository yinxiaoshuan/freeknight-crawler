
package org.freeknight.forum.task;

import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.freeknight.forum.resource.TiexueTargetResource;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.forum.task.parser.TiexueTopicParser;

public class TiexueTopicTask
		extends TopicTask
{

	public TiexueTopicTask (
			TopicTaskInModel taskInModel ) {
		super ( taskInModel );
		this.resource = new TiexueTargetResource ( );
		this.topicParser = new TiexueTopicParser ( );
	}

	@Override
	protected TopicTask getTopicTask (
			final TopicTaskInModel taskInModel )
	{
		return new TiexueTopicTask ( taskInModel );
	}

	public List< TopicTaskOutModel > call ( ) throws Exception
	{
		final CloseableHttpClient httpClient = HttpClients.createDefault ( );
		final HttpGet httpGet = new HttpGet ( this.taskInModel.getTarget ( ) );
		httpGet
				.addHeader (
						HTTP.USER_AGENT,
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36" );
		httpGet.addHeader ( HTTP.TARGET_HOST, "bbs.tiexue.net" );
		httpGet.addHeader ( HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE );
		httpGet.addHeader ( HTTP.CONTENT_ENCODING, "gzip,deflate" );
		httpGet
				.addHeader (
						"Cookie",
						"_testrefer=other; UM_distinctid=15f1955052c8c4-0545e1b30a7645-31667c00-15f900-15f1955052d930; cid=DHDEYAKL; BDTUJIAID=0d28d31ee4dde1d8e0b7351fa86619bb; tx_abtest=1107; uservisitnum=2; Hm_lvt_9b19a3a1e7d7608026f3c086a9a7c41e=1507961614; Hm_lpvt_9b19a3a1e7d7608026f3c086a9a7c41e=1507979913; _logck=C43A7B3014C846D02E126BB79D6663CE; __utmt=1; __utma=247579266.853687210.1507958523.1507983495.1507990210.5; __utmb=247579266.7.10.1507990210; __utmc=247579266; __utmz=247579266.1507990210.5.4.utmcsr=bbs.tiexue.net|utmccn=(referral)|utmcmd=referral|utmcct=/bbs283-0-1.html; __utmv=247579266.|1=loginuser=0=1; Hm_lvt_c8d128c127cc299c41e73a24f1158b7c=1507958523; Hm_lpvt_c8d128c127cc299c41e73a24f1158b7c=1507990693; CNZZDATA1256225720=289800827-1507958448-http%253A%252F%252Fbbs.jrj.com.cn%252F%7C1507985448; tiexueusersource=1" );

		final String respBody = httpClient.execute ( /* proxy, */httpGet, new TopicResponseHandler ( "gbk" ) );
		final List< TopicTaskOutModel > topics = topicParser.doParse ( respBody, null );
		return topics;
	}

}
