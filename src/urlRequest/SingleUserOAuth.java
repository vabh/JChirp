package urlRequest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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


			String url = "https://api.twitter.com/1.1/users/lookup.json?screen_name=mourjo_sen,anuvabh18,twitterapi,twitter"; 
			System.out.println(TwitterQuery.post(url));


		}
		finally
		{
			credentialsFile.close();
		}
	}

	SingleUserOAuth(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret; 
	}

	public String post(String url)
	{
		try
		{
			URLCodec urlEncoder = new URLCodec();
			String encodedURL = "POST&" + urlEncoder.encode(baseURL(url))+"&";
			String paramsURL = "";
			
			Map<String, String> params= getURLParameters(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			
			for(String key : params.keySet())
				nvps.add(new BasicNameValuePair(key, params.get(key)));

			
			params.put("oauth_consumer_key", consumerKey);
			params.put("oauth_token", accessToken);
			params.put("oauth_timestamp", ""+(System.currentTimeMillis()/1000));
			params.put("oauth_nonce", generateNonce());
			params.put("oauth_version", "1.0");
			params.put("oauth_signature_method", "HMAC-SHA1");

			for(String key : params.keySet())
				paramsURL += urlEncoder.encode(key) + "=" + urlEncoder.encode(params.get(key))+"&";
			
			paramsURL = paramsURL.substring(0, paramsURL.length() - 1);
			encodedURL += urlEncoder.encode(paramsURL);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(baseURL(url));
			
			if(url.indexOf('?') != -1)
				httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			
			httpPost.addHeader("Authorization", "OAuth oauth_consumer_key=\""+ consumerKey +"\", oauth_nonce=\""
					+params.get("oauth_nonce")+"\", oauth_signature=\""+oAuthSign(encodedURL)
					+"\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\""
					+params.get("oauth_timestamp")+"\", oauth_token=\""
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
			return responseData;
		}
		catch(Exception e)
		{
			System.err.println("Please format your URL properly");
			e.printStackTrace();
		}
		return "";
	}

	public String get(String url)
	{
		try
		{
			URLCodec urlEncoder = new URLCodec();
			String encodedURL = "GET&" + urlEncoder.encode(baseURL(url))+"&";
			String paramsURL = "";

			Map<String, String> params = getURLParameters(url);
			params.put("oauth_consumer_key", consumerKey);
			params.put("oauth_token", accessToken);
			params.put("oauth_timestamp", ""+(System.currentTimeMillis()/1000));
			params.put("oauth_nonce", generateNonce());
			params.put("oauth_version", "1.0");
			params.put("oauth_signature_method", "HMAC-SHA1");

			for(String key : params.keySet())
				paramsURL += urlEncoder.encode(key) + "=" + urlEncoder.encode(params.get(key))+"&";

			paramsURL = paramsURL.substring(0, paramsURL.length() - 1);
			encodedURL += urlEncoder.encode(paramsURL);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);

			httpget.addHeader("Authorization", "OAuth oauth_consumer_key=\""+ consumerKey +"\", oauth_nonce=\""
					+params.get("oauth_nonce")+"\", oauth_signature=\""+oAuthSign(encodedURL)
					+"\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\""
					+params.get("oauth_timestamp")+"\", oauth_token=\""
					+accessToken+"\", oauth_version=\"1.0\"");

			CloseableHttpResponse response = httpclient.execute(httpget);
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
			return responseData;
		}
		catch(Exception e)
		{
			System.err.println("Please format your URL properly");
			e.printStackTrace();
		}
		return "";
	}

	private String oAuthSign(String input) throws NoSuchAlgorithmException, InvalidKeyException, URISyntaxException, EncoderException
	{
		String key = consumerSecret + "&" + accessTokenSecret;
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signingKey);
		return new URLCodec().encode(new String(Base64.encodeBase64(mac.doFinal(input.getBytes()))));
	}

	private String baseURL(String url)
	{
		return url.split("\\?")[0];
	}

	private Map<String,String> getURLParameters(String url) throws EncoderException
    {
    	Map<String, String> params = new TreeMap<String,String>();
    	if(url.indexOf('?') != -1)
    	{
	    	for(String x : url.split("\\?")[1].split("&"))
	    	{
	    		String keyValue[] = x.split("=");
	    		params.put(keyValue[0], keyValue[1]);
	    	}
    	}
		return params;
    }

	private String generateNonce()
	{
		char characters[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
		Random gen = new Random();
		StringBuilder nonce = new StringBuilder();

		for(int i = 0; i < 32; i++)
			nonce.append(characters[gen.nextInt(characters.length)]);

		return nonce.toString();
	}

}
