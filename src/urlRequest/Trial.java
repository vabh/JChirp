package urlRequest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Trial {

	public static void main(String[] args) throws IOException
	{

		String key = "";
		String sec = "";
		String url = "https://api.twitter.com/oauth2/token";
				
//		OAuth(key, sec, url);
		post();
				
	}
	
	public static String getURL(String urlString) throws IOException
	{
//		System.out.println(getURL("https://api.twitter.com/1.1/friends/ids.json?cursor=-1&screen_name=twitterapi&count=5000"));
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
	
	public static void OAuth(String consumerKey, String consumerSecret, String url) throws ClientProtocolException, IOException{
		
		String consumerKeyURLEncode = URLEncoder.encode(consumerKey, "UTF-8");
		String consumerSecretURLEncode = URLEncoder.encode(consumerSecret, "UTF-8");
		
		String bearerTokenCredentials = consumerKeyURLEncode + ":" + consumerSecretURLEncode;
		
		String base64enc = new String(Base64.encodeBase64(bearerTokenCredentials.getBytes()));
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);		
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("grant_type", "client_credentials"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		
		httppost.setEntity(entity);
		httppost.setHeader("Authorization", "Basic " + base64enc);
		httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		
		CloseableHttpResponse response = httpclient.execute(httppost);
		
		String responseData = null;
		try
		{
			HttpEntity entityRes = response.getEntity();
			if (entityRes != null) {
				InputStream instream = entityRes.getContent();
				try {
					responseData = EntityUtils.toString(entityRes);
				} finally {
					instream.close();
					EntityUtils.consume(entity);
				}
			}
		}
		finally
		{
			response.close();
		}
		
		System.out.println(responseData);
	}
	
	public static void post() throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://www.flipkart.com/");
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("q", "Laptops"));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response = httpclient.execute(httpPost);
		
		
		String responseData = null;
		try {
		    System.out.println(response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    
		    InputStream instream = entity.getContent();
			try {
				responseData = EntityUtils.toString(entity);
			} finally {
				instream.close();
				EntityUtils.consume(entity);
			}
			
		} finally {
		    response.close();
		}
		System.out.println(responseData);
		
//		PrintWriter htmlfile = new PrintWriter(new BufferedWriter(new FileWriter("x.html")));
//		htmlfile.println(responseData);
//		htmlfile.close();
	}

}