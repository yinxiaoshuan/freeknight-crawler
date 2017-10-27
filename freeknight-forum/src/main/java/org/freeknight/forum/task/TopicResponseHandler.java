
package org.freeknight.forum.task;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

public class TopicResponseHandler
		implements ResponseHandler< String >
{

	String	encoding;

	public TopicResponseHandler ( ) {
		this ( Consts.UTF_8.name ( ) );
	}

	public TopicResponseHandler (
			String encoding ) {
		this.encoding = encoding;
	}

	@Override
	public String handleResponse (
			HttpResponse httpResp ) throws ClientProtocolException, IOException
	{
		final StatusLine statusLine = httpResp.getStatusLine ( );
		if ( statusLine.getStatusCode ( ) != HttpsURLConnection.HTTP_OK )
		{
			throw new IOException ( "StatusLine: " + statusLine.getStatusCode ( ) + "/" + statusLine.getReasonPhrase ( ) );
		}

		final HttpEntity entity = httpResp.getEntity ( );
		final String respBody = EntityUtils.toString ( entity, encoding );
		return respBody;
	}
}
