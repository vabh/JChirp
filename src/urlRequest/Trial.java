package urlRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Trial {

	public static void main(String[] args) throws IOException
	{
		System.out.println(getURL("https://api.twitter.com/1.1/friends/ids.json?cursor=-1&screen_name=twitterapi&count=5000"));

	}
	
	public static String getURL(String urlString) throws IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(urlString);
		CloseableHttpResponse response = httpclient.execute(httpget);
		String responseData = null;

		try
		{
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					responseData = EntityUtils.toString(entity);
				} finally {
					instream.close();
				}
			}
		}
		finally
		{
			response.close();
		}
		return responseData;
		
	}

	@Deprecated
	public static String get(String urlString) throws IOException
	{
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String inputLine;
		StringBuilder out = new StringBuilder("");

		while ((inputLine = in.readLine()) != null)
		{
			out.append(inputLine);
			out.append("\n");
		}

		in.close();
		return out.toString();
	}

}
