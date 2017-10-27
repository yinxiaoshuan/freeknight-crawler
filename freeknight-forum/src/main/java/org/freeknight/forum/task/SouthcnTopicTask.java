
package org.freeknight.forum.task;

import java.util.List;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.freeknight.forum.resource.SouthcnTopicTargetResource;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.forum.task.parser.SouthcnTopicParser;

/**
 * 主题/帖子列表任务.
 * 
 * @author yrj
 *
 */
public class SouthcnTopicTask
		extends TopicTask
{

	public SouthcnTopicTask (
			TopicTaskInModel taskInModel ) {
		super ( taskInModel );
		this.resource = new SouthcnTopicTargetResource ( );
		this.topicParser = new SouthcnTopicParser ( );
	}

	public List< TopicTaskOutModel > call ( ) throws Exception
	{
		final RequestConfig requestConfig = RequestConfig.custom ( ).setCookieSpec ( CookieSpecs.IGNORE_COOKIES ).build ( );
		final CloseableHttpClient httpClient = HttpClients.custom ( ).setDefaultRequestConfig ( requestConfig ).build ( );
		final HttpGet httpGet = new HttpGet ( taskInModel.getTarget ( ) );
		httpGet.addHeader ( "Accept", "*/*" );
		String respBody = httpClient.execute ( httpGet, new TopicResponseHandler ( ) );
		List< TopicTaskOutModel > topics = this.topicParser.doParse ( respBody, null );
		return topics;
	}
}
