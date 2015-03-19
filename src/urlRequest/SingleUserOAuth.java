package urlRequest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class SingleUserOAuth {

	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;

	public static void main(String args[]) throws IOException
	{
		BufferedReader credentialsFile = new BufferedReader(new FileReader("credentials.txt"));
		SingleUserOAuth TwitterQuery;
		try
		{
			//SingleUserOAuth obj = new SingleUserOAuth(consumer_key, consumer_secret,access_token,access_token_secret);

			TwitterQuery = new SingleUserOAuth(credentialsFile.readLine(),
					credentialsFile.readLine(),
					credentialsFile.readLine(),
					credentialsFile.readLine());


			String url = "https://api.twitter.com/1.1/users/lookup.json?screen_name=twitterapi,twitter"; 
//			System.out.println(TwitterQuery.get(url));
			
//			TwitterJSON x = TwitterQuery.get(url);
			
			System.out.println(TwitterQuery.get(url));
//			System.out.println(TwitterQuery.printJSONArray(TwitterQuery.stringToJSONArray("[\"a\",\"b\",\"c\"]")));
			
//			System.out.println(TwitterQuery.printJSONArray(TwitterQuery.stringToJSONArray("[{\"in_reply_to_status_id_str\":null,\"in_reply_to_status_id\":null,\"coordinates\":null,\"created_at\":\"Tue Mar 21 20:50:14 +0000 2006\",\"truncated\":false,\"in_reply_to_user_id_str\":null,\"source\":\"<a href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\">Twitter Web Client<\\\\/a>\",\"retweet_count\":52404,\"retweeted\":false,\"geo\":null,\"in_reply_to_screen_name\":null,\"entities\":{\"urls\":[],\"hashtags\":[],\"user_mentions\":[],\"symbols\":[]},\"id_str\":\"20\",\"in_reply_to_user_id\":null,\"favorite_count\":36010,\"id\":20,\"text\":\"just setting up my twttr\",\"place\":null,\"contributors\":null,\"lang\":\"en\",\"user\":{\"utc_offset\":-25200,\"friends_count\":1342,\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/539479004370444288\\/Lc2Wp5y5_normal.jpeg\",\"listed_count\":24273,\"profile_background_image_url\":\"http:\\/\\/abs.twimg.com\\/images\\/themes\\/theme7\\/bg.gif\",\"default_profile_image\":false,\"favourites_count\":5335,\"description\":\"\",\"created_at\":\"Tue Mar 21 20:50:14 +0000 2006\",\"is_translator\":false,\"profile_background_image_url_https\":\"https:\\/\\/abs.twimg.com\\/images\\/themes\\/theme7\\/bg.gif\",\"protected\":false,\"screen_name\":\"jack\",\"id_str\":\"12\",\"profile_link_color\":\"990000\",\"is_translation_enabled\":false,\"id\":12,\"geo_enabled\":true,\"profile_background_color\":\"EBEBEB\",\"lang\":\"en\",\"profile_sidebar_border_color\":\"DFDFDF\",\"profile_location\":null,\"profile_text_color\":\"333333\",\"verified\":true,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/539479004370444288\\/Lc2Wp5y5_normal.jpeg\",\"time_zone\":\"Pacific Time (US & Canada)\",\"url\":null,\"contributors_enabled\":false,\"profile_background_tile\":false,\"entities\":{\"description\":{\"urls\":[]}},\"statuses_count\":16113,\"follow_request_sent\":false,\"followers_count\":2892910,\"profile_use_background_image\":true,\"default_profile\":false,\"following\":false,\"name\":\"Jack\",\"location\":\"California\",\"profile_sidebar_fill_color\":\"F3F3F3\",\"notifications\":false},\"favorited\":false},{\"in_reply_to_status_id_str\":null,\"in_reply_to_status_id\":null,\"possibly_sensitive\":false,\"coordinates\":null,\"created_at\":\"Sun Feb 09 23:25:34 +0000 2014\",\"truncated\":false,\"in_reply_to_user_id_str\":null,\"source\":\"<a href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\">Twitter Web Client<\\\\/a>\",\"retweet_count\":2,\"retweeted\":false,\"geo\":null,\"in_reply_to_screen_name\":null,\"entities\":{\"urls\":[{\"display_url\":\"dev.twitter.com\\/docs\\/api\\/1.1\\/p\\u2026\",\"indices\":[42,65],\"expanded_url\":\"https:\\/\\/dev.twitter.com\\/docs\\/api\\/1.1\\/post\\/statuses\\/update\",\"url\":\"https:\\/\\/t.co\\/9S8YO69xzf\"}],\"hashtags\":[],\"user_mentions\":[],\"symbols\":[]},\"id_str\":\"432656548536401920\",\"in_reply_to_user_id\":null,\"favorite_count\":6,\"id\":432656548536401920,\"text\":\"POST statuses\\/update. Great way to start. https:\\/\\/t.co\\/9S8YO69xzf (disclaimer, this was not posted via the API).\",\"place\":null,\"contributors\":null,\"lang\":\"en\",\"user\":{\"utc_offset\":-25200,\"friends_count\":1468,\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/530814764687949824\\/npQQVkq8_normal.png\",\"listed_count\":379,\"profile_background_image_url\":\"http:\\/\\/abs.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"default_profile_image\":false,\"favourites_count\":539,\"description\":\"Developers and Platform Relations @Twitter. We are developer advocates. We can\'t answer all your questions, but we listen to all of them!\",\"created_at\":\"Sat Dec 14 04:35:55 +0000 2013\",\"is_translator\":false,\"profile_background_image_url_https\":\"https:\\/\\/abs.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"protected\":false,\"screen_name\":\"TwitterDev\",\"id_str\":\"2244994945\",\"profile_link_color\":\"0084B4\",\"is_translation_enabled\":false,\"id\":2244994945,\"geo_enabled\":true,\"profile_background_color\":\"FFFFFF\",\"lang\":\"en\",\"profile_sidebar_border_color\":\"FFFFFF\",\"profile_location\":null,\"profile_text_color\":\"333333\",\"verified\":true,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/530814764687949824\\/npQQVkq8_normal.png\",\"time_zone\":\"Pacific Time (US & Canada)\",\"url\":\"https:\\/\\/t.co\\/66w26cd6ZO\",\"contributors_enabled\":false,\"profile_background_tile\":false,\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/2244994945\\/1396995246\",\"entities\":{\"description\":{\"urls\":[]},\"url\":{\"urls\":[{\"display_url\":\"dev.twitter.com\",\"indices\":[0,23],\"expanded_url\":\"https:\\/\\/dev.twitter.com\\/\",\"url\":\"https:\\/\\/t.co\\/66w26cd6ZO\"}]}},\"statuses_count\":1131,\"follow_request_sent\":false,\"followers_count\":62454,\"profile_use_background_image\":false,\"default_profile\":false,\"following\":false,\"name\":\"TwitterDev\",\"location\":\"Internet\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"notifications\":false},\"favorited\":false}]")));

			
			

		}
		finally
		{
			credentialsFile.close();
		}
	}
	
	

	public SingleUserOAuth(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret; 
	}

	public TwitterJSON post(String url)
	{
		try
		{
			URLCodec urlEncoder = new URLCodec();
			String encodedURL = "POST&" + urlEncoder.encode(baseURL(url))+"&";
			String paramsURL = "";
			
			Map<String, String> parameterMap = getURLParameters(url);
			
			List<NameValuePair> postPrameters = new ArrayList<NameValuePair>();
			for(String parameterName : parameterMap.keySet())
				postPrameters.add(new BasicNameValuePair(parameterName, parameterMap.get(parameterName)));
			
			
			parameterMap.put("oauth_consumer_key", consumerKey);
			parameterMap.put("oauth_token", accessToken);
			parameterMap.put("oauth_timestamp", ""+(System.currentTimeMillis()/1000));
			parameterMap.put("oauth_nonce", generateNonce());
			parameterMap.put("oauth_version", "1.0");
			parameterMap.put("oauth_signature_method", "HMAC-SHA1");
			

			for(String key : parameterMap.keySet())
				paramsURL += urlEncoder.encode(key) + "=" + urlEncoder.encode(parameterMap.get(key))+"&";
			
			paramsURL = paramsURL.substring(0, paramsURL.length() - 1);
			encodedURL += urlEncoder.encode(paramsURL);

			
			
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(baseURL(url));
			
			if(url.indexOf('?') != -1)
				httpPost.setEntity(new UrlEncodedFormEntity(postPrameters));
			
			httpPost.addHeader("Authorization", "OAuth oauth_consumer_key=\""+ consumerKey +"\", oauth_nonce=\""
					+parameterMap.get("oauth_nonce")+"\", oauth_signature=\""+oAuthSign(encodedURL)
					+"\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\""
					+parameterMap.get("oauth_timestamp")+"\", oauth_token=\""
					+accessToken+"\", oauth_version=\"1.0\"");
			

			CloseableHttpResponse response = httpclient.execute(httpPost);
			String responseData = null;
			
			try 
			{
				HttpEntity entity = response.getEntity();

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
			finally 
			{
				response.close();
			}
			return stringToJSONArray(responseData);
		}
		catch(Exception e)
		{
			System.err.println("Please format your URL properly");
			e.printStackTrace();
		}
		return stringToJSONArray("");
	}

	public TwitterJSON get(String url)
	{
		try
		{
			URLCodec urlEncoder = new URLCodec();
			String encodedURL = "GET&" + urlEncoder.encode(baseURL(url))+"&";
			String paramsURL = "";

			Map<String, String> parameterMap = getURLParameters(url);
			parameterMap.put("oauth_consumer_key", consumerKey);
			parameterMap.put("oauth_token", accessToken);
			parameterMap.put("oauth_timestamp", ""+(System.currentTimeMillis()/1000));
			parameterMap.put("oauth_nonce", generateNonce());
			parameterMap.put("oauth_version", "1.0");
			parameterMap.put("oauth_signature_method", "HMAC-SHA1");

			for(String parameterName : parameterMap.keySet())
				paramsURL += urlEncoder.encode(parameterName) + "=" + urlEncoder.encode(parameterMap.get(parameterName))+"&";
			
			paramsURL = paramsURL.substring(0, paramsURL.length() - 1);
			encodedURL += urlEncoder.encode(paramsURL);

			
			
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			
			httpGet.addHeader("Authorization", "OAuth oauth_consumer_key=\""+ consumerKey +"\", oauth_nonce=\""
					+parameterMap.get("oauth_nonce")+"\", oauth_signature=\""+oAuthSign(encodedURL)
					+"\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\""
					+parameterMap.get("oauth_timestamp")+"\", oauth_token=\""
					+accessToken+"\", oauth_version=\"1.0\"");
			

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
			return stringToJSONArray(responseData);
		}
		catch(Exception e)
		{
			System.err.println("Please format your URL properly");
			e.printStackTrace();
		}
		return stringToJSONArray("");
	}
	
	private TwitterJSON stringToJSONArray(String jsonString)
	{
		jsonString = jsonString.trim();
		jsonString = jsonString.charAt(0)=='[' ? jsonString : "["+jsonString+"]";
		return new TwitterJSON(jsonString);
	}

	private String oAuthSign(String input)
	{
		try
		{
			String key = consumerSecret + "&" + accessTokenSecret;
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			
			return new URLCodec().encode(new String(Base64.encodeBase64(mac.doFinal(input.getBytes()))));
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		
		catch(InvalidKeyException e)
		{
			System.err.println("The consumer secret key and/or access token secret are not correct.");
			System.exit(0);
		}
		
		catch(EncoderException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private String baseURL(String url)
	{
		return url.split("\\?")[0];
	}

	private Map<String,String> getURLParameters(String url)
    {
    	Map<String, String> parameterMap = new TreeMap<String,String>();
    	
    	if(url.indexOf('?') != -1)
    	{
	    	for(String parameter : url.split("\\?")[1].split("&"))
	    	{
	    		String keyValue[] = parameter.split("=");
	    		parameterMap.put(keyValue[0], keyValue[1]);
	    	}
    	}
		return parameterMap;
    }

	private String generateNonce()
	{
		char characters[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
				'S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n',
				'o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
		
		
		Random gen = new Random();
		StringBuilder nonce = new StringBuilder();

		for(int i = 0; i < 32; i++)
			nonce.append(characters[gen.nextInt(characters.length)]);

		return nonce.toString();
	}

}
