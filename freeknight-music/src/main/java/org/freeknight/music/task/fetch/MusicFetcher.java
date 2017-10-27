
package org.freeknight.music.task.fetch;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class MusicFetcher
{

	public String doFetch (
			String target ) throws IOException
	{
		RequestConfig config = RequestConfig.custom ( ).setConnectTimeout ( 5000 ).setSocketTimeout ( 5000 ).build ( );
		HttpGet httpGet = new HttpGet ( target );
		httpGet.setConfig ( config );

		CloseableHttpClient httpClient = HttpClients.createDefault ( );
		String respBody = null;
		try
		{
			respBody = httpClient.execute ( httpGet, new ResponseHandler< String > ( )
			{

				@Override
				public String handleResponse (
						HttpResponse response ) throws ClientProtocolException, IOException
				{
					StatusLine statusLine = response.getStatusLine ( );
					if ( statusLine.getStatusCode ( ) != HttpStatus.SC_OK )
					{
						throw new IOException ( "statusLine: " + ( statusLine.getStatusCode ( ) + '/' + statusLine.getReasonPhrase ( ) ) );
					}

					HttpEntity entity = response.getEntity ( );
					String respBody = EntityUtils.toString ( entity, "utf-8" );
					return respBody;
				}
			} );
		}
		catch ( IOException e )
		{
			throw e;
		}
		return respBody;
	}

}
