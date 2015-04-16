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
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;
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

import requests.HttpRequestHandler;

public class Trial {
	private static SecureRandom random = new SecureRandom();

	public static void main(String[] args) throws IOException, URISyntaxException
	{
		//very very bad code :\
		for(String url : urls)
		{
			try
			{
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpGet httpGet = new HttpGet(url);
				CloseableHttpResponse response = httpclient.execute(httpGet);
				String responseData = null;
		
				try
				{
					HttpEntity entity = response.getEntity();
					if (entity != null) 
					{
						InputStream instream = entity.getContent();
						try 
						{
							responseData = EntityUtils.toString(entity);
						} 
						finally 
						{
							instream.close();
							EntityUtils.consume(entity);
						}
					}
				}
				finally
				{
					response.close();
				}
				
				
				System.out.println(p.split(responseData)[1].split("</div>")[0]);
			}
			catch(Exception e)
			{
				System.out.println("Error: " + url);
			}
		}
		
//		BufferedReader credentialsFile = new BufferedReader(new FileReader("credentials.txt"));
//		
//		try
//		{
//			//SingleUserOAuth obj = new SingleUserOAuth(consumer_key, consumer_secret,access_token,access_token_secret);
//			/*
//			String key = credentialsFile.readLine();
//			String sec = credentialsFile.readLine();
//			String url = "https://api.twitter.com/oauth2/token";
//		
//			String result = appOnlyAuth(key, sec, url);
//			
//			System.out.println(new TwitterJSON("["+result+"]"));
//			*/
//			
//			URI uri = new URI(
//			        "https", 
//			        "api.twitter.com", 
//			        "/1.1/statuses/update.json",
//			        "status=teststatus, @twitterapi",
//			        null);
//			String url = "https://api.twitter.com/1.1/statuses/update.json?status=teststatus, @twitterapi";
//			uriGenerator(url);
//			
//			String request = uri.toASCIIString();
//			System.out.println(request);
//			
//		}
//		finally
//		{
//			credentialsFile.close();
//		}		
//		OAuth(key, sec, url);
//		postTest();
		
	}
	
	public static String percentEncode(String text)
	{
		URLCodec urlEncoder = new URLCodec();
		try {
			return urlEncoder.encode(text, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
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
	static Pattern p = Pattern.compile("FieldName-fieldExampleRequest");
	static String urls[] = {"https://dev.twitter.com/rest/reference/get/statuses/mentions_timeline",
		"https://dev.twitter.com/rest/reference/get/statuses/user_timeline",
		"https://dev.twitter.com/rest/reference/get/statuses/home_timeline",
		"https://dev.twitter.com/rest/reference/get/statuses/retweets_of_me",
		"https://dev.twitter.com/rest/reference/get/statuses/retweets/:id",
		"https://dev.twitter.com/rest/reference/get/statuses/show/:id",
		"https://dev.twitter.com/rest/reference/post/statuses/destroy/:id",
		"https://dev.twitter.com/rest/reference/post/statuses/update",
		"https://dev.twitter.com/rest/reference/post/statuses/retweet/:id",
		"https://dev.twitter.com/rest/reference/post/statuses/update_with_media",
		"https://dev.twitter.com/rest/reference/get/statuses/oembed",
		"https://dev.twitter.com/rest/reference/get/statuses/retweeters/ids",
		"https://dev.twitter.com/rest/reference/get/statuses/lookup",
		"https://dev.twitter.com/rest/reference/post/media/upload",
		"https://dev.twitter.com/rest/reference/get/direct_messages/sent",
		"https://dev.twitter.com/rest/reference/get/direct_messages/show",
		"https://dev.twitter.com/rest/reference/get/search/tweets",
		"https://dev.twitter.com/rest/reference/get/direct_messages",
		"https://dev.twitter.com/rest/reference/post/direct_messages/destroy",
		"https://dev.twitter.com/rest/reference/post/direct_messages/new",
		"https://dev.twitter.com/rest/reference/get/friendships/no_retweets/ids",
		"https://dev.twitter.com/rest/reference/get/friends/ids",
		"https://dev.twitter.com/rest/reference/get/followers/ids",
		"https://dev.twitter.com/rest/reference/get/friendships/incoming",
		"https://dev.twitter.com/rest/reference/get/friendships/outgoing",
		"https://dev.twitter.com/rest/reference/post/friendships/create",
		"https://dev.twitter.com/rest/reference/post/friendships/destroy",
		"https://dev.twitter.com/rest/reference/post/friendships/update",
		"https://dev.twitter.com/rest/reference/get/friendships/show",
		"https://dev.twitter.com/rest/reference/get/friends/list",
		"https://dev.twitter.com/rest/reference/get/followers/list",
		"https://dev.twitter.com/rest/reference/get/friendships/lookup",
		"https://dev.twitter.com/rest/reference/get/account/settings",
		"https://dev.twitter.com/rest/reference/get/account/verify_credentials",
		"https://dev.twitter.com/rest/reference/post/account/settings",
		"https://dev.twitter.com/rest/reference/post/account/update_delivery_device",
		"https://dev.twitter.com/rest/reference/post/account/update_profile",
		"https://dev.twitter.com/rest/reference/post/account/update_profile_background_image",
		"https://dev.twitter.com/rest/reference/post/account/update_profile_image",
		"https://dev.twitter.com/rest/reference/get/blocks/list",
		"https://dev.twitter.com/rest/reference/get/blocks/ids",
		"https://dev.twitter.com/rest/reference/post/blocks/create",
		"https://dev.twitter.com/rest/reference/post/blocks/destroy",
		"https://dev.twitter.com/rest/reference/get/users/lookup",
		"https://dev.twitter.com/rest/reference/get/users/show",
		"https://dev.twitter.com/rest/reference/get/users/search",
		"https://dev.twitter.com/rest/reference/post/account/remove_profile_banner",
		"https://dev.twitter.com/rest/reference/post/account/update_profile_banner",
		"https://dev.twitter.com/rest/reference/get/users/profile_banner",
		"https://dev.twitter.com/rest/reference/post/mutes/users/create",
		"https://dev.twitter.com/rest/reference/post/mutes/users/destroy",
		"https://dev.twitter.com/rest/reference/get/mutes/users/ids",
		"https://dev.twitter.com/rest/reference/get/mutes/users/list",
		"https://dev.twitter.com/rest/reference/get/users/suggestions/:slug",
		"https://dev.twitter.com/rest/reference/get/users/suggestions",
		"https://dev.twitter.com/rest/reference/get/users/suggestions/:slug/members",
		"https://dev.twitter.com/rest/reference/get/favorites/list",
		"https://dev.twitter.com/rest/reference/post/favorites/destroy",
		"https://dev.twitter.com/rest/reference/post/favorites/create",
		"https://dev.twitter.com/rest/reference/get/lists/list",
		"https://dev.twitter.com/rest/reference/get/lists/statuses",
		"https://dev.twitter.com/rest/reference/post/lists/members/destroy",
		"https://dev.twitter.com/rest/reference/get/lists/memberships",
		"https://dev.twitter.com/rest/reference/get/lists/subscribers",
		"https://dev.twitter.com/rest/reference/post/lists/subscribers/create",
		"https://dev.twitter.com/rest/reference/get/lists/subscribers/show",
		"https://dev.twitter.com/rest/reference/post/lists/subscribers/destroy",
		"https://dev.twitter.com/rest/reference/post/lists/members/create_all",
		"https://dev.twitter.com/rest/reference/get/lists/members/show",
		"https://dev.twitter.com/rest/reference/get/lists/members",
		"https://dev.twitter.com/rest/reference/post/lists/members/create",
		"https://dev.twitter.com/rest/reference/post/lists/destroy",
		"https://dev.twitter.com/rest/reference/post/lists/update",
		"https://dev.twitter.com/rest/reference/post/lists/create",
		"https://dev.twitter.com/rest/reference/get/lists/show",
		"https://dev.twitter.com/rest/reference/get/lists/subscriptions",
		"https://dev.twitter.com/rest/reference/post/lists/members/destroy_all",
		"https://dev.twitter.com/rest/reference/get/lists/ownerships",
		"https://dev.twitter.com/rest/reference/get/saved_searches/list",
		"https://dev.twitter.com/rest/reference/get/saved_searches/show/:id",
		"https://dev.twitter.com/rest/reference/post/saved_searches/create",
		"https://dev.twitter.com/rest/reference/post/saved_searches/destroy/:id",
		"https://dev.twitter.com/rest/reference/get/geo/id/:place_id",
		"https://dev.twitter.com/rest/reference/get/geo/reverse_geocode",
		"https://dev.twitter.com/rest/reference/get/geo/search",
		"https://dev.twitter.com/rest/reference/post/geo/place",
		"https://dev.twitter.com/rest/reference/get/trends/place",
		"https://dev.twitter.com/rest/reference/get/trends/available",
		"https://dev.twitter.com/rest/reference/get/application/rate_limit_status",
		"https://dev.twitter.com/rest/reference/get/help/configuration",
		"https://dev.twitter.com/rest/reference/get/help/languages",
		"https://dev.twitter.com/rest/reference/get/help/privacy",
		"https://dev.twitter.com/rest/reference/get/help/tos",
		"https://dev.twitter.com/rest/reference/get/trends/closest",
		"https://dev.twitter.com/rest/reference/post/users/report_spam"};

}