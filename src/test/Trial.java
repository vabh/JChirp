package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SecureRandom;
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
	private static SecureRandom random = new SecureRandom();

	public static void main(String[] args) throws IOException, URISyntaxException
	{
		
		BufferedReader credentialsFile = new BufferedReader(new FileReader("credentials.txt"));
		
		try
		{
			//SingleUserOAuth obj = new SingleUserOAuth(consumer_key, consumer_secret,access_token,access_token_secret);
			/*
			String key = credentialsFile.readLine();
			String sec = credentialsFile.readLine();
			String url = "https://api.twitter.com/oauth2/token";
		
			String result = appOnlyAuth(key, sec, url);
			
			System.out.println(new TwitterJSON("["+result+"]"));
			*/
			
			URI uri = new URI(
			        "https", 
			        "api.twitter.com", 
			        "/1.1/statuses/update.json",
			        "status=teststatus, @twitterapi",
			        null);
			String url = "https://api.twitter.com/1.1/statuses/update.json?status=teststatus, @twitterapi";
			uriGenerator(url);
			
			String request = uri.toASCIIString();
			System.out.println(request);
			
		}
		finally
		{
			credentialsFile.close();
		}		
//		OAuth(key, sec, url);
//		postTest();
		
	}
	
	public static URI uriGenerator(String urlstr) throws MalformedURLException, URISyntaxException
	{
		URL url = new URL(urlstr);
		URI x = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
//		x.toURL()
		System.out.println(percentEncode("☃"));
//		String scheme = "", authority = "", path = "", query = "";
//		scheme = url.split("://")[0];
//		authort
		
		return null;
		
	}
	
	public static String percentEncode(String str)
	{
		String encodedString = "";
		StringBuilder encoded = new StringBuilder();
		for(int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			switch(c)
			{
				case ' ': encoded.append("%20");
				break;
				case '\"': encoded.append("%22");
				break;
				case '%': encoded.append("%25");
				break;
				case '-': encoded.append("%2D");
				break;
				case '.': encoded.append("%2E");
				break;
				case '<': encoded.append("%3C");
				break;
				case '>': encoded.append("%3E");
				break;
				case '\\': encoded.append("%5C");
				break;
				case '^': encoded.append("%5E");
				break;
				case '_': encoded.append("%5F");
				break;
				case '`': encoded.append("%60");
				break;
				case '{': encoded.append("%7B");
				break;
				case '|': encoded.append("%7C");
				break;
				case '}': encoded.append("%7D");
				break;
				case '~': encoded.append("%7E");
				break;
			
			
				case '!': encoded.append("%21");
				break;
				case '#': encoded.append("%23");
				break;
				case '$': encoded.append("%24");
				break;
				case '&': encoded.append("%26");
				break;
				case '\'': encoded.append("%27");
				break;
				case '(': encoded.append("%28");
				break;
				case ')': encoded.append("%29");
				break;
				case '*': encoded.append("%2A");
				break;
				case '+': encoded.append("%2B");
				break;
				case ',': encoded.append("%2C");
				break;
				case '/': encoded.append("%2F");
				break;
				case ':': encoded.append("%3A");
				break;
				case ';': encoded.append("%3B");
				break;
				case '=': encoded.append("%3D");
				break;
				case '?': encoded.append("%3F");
				break;
				case '@': encoded.append("%40");
				break;
				case '[': encoded.append("%5B");
				break;
				case ']': encoded.append("%5D");
				break;
				
				default: encoded.append(c);
				
				
			}
		}
		try {
			encodedString = URLEncoder.encode(str, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encodedString;
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
	
	public static String appOnlyAuth(String consumerKey, String consumerSecret, String url) throws ClientProtocolException, IOException{
		
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
			return responseData;
		}		
		
	}
	
	public static void postTest() throws ClientProtocolException, IOException
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
		
	}

}