package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.directory.InvalidAttributesException;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SingleUserOAuth extends Auth{

	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;
	private URLCodec urlEncoder;

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

			//the status must not be encoded, ie spaces should be spaces and not %20, the twitter documentation is not consistent with other calls!
			String url = "https://api.twitter.com/1.1/statuses/update.json?status=JChirp :) @mourjo_sen @anuvabh18"; 
			System.out.println(TwitterQuery.post(url));
			
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
		urlEncoder = new URLCodec();
	}

	public TwitterJSON getJSON(String url)
	{
		return stringToJSONArray(get(url));
	}

	public TwitterJSON postJSON(String url)
	{
		return stringToJSONArray(post(url));
	}

	public String post(String url)
	{
		try
		{
			String encodedURL = "POST&" + percentEncode(baseURL(url)) + "&";
			String paramsURL = "";

			Map<String, String> parameterMap = getURLParameters(url);

			List<NameValuePair> postPrameters = new ArrayList<NameValuePair>();
			for(String parameterName : parameterMap.keySet()){
				postPrameters.add(new BasicNameValuePair(parameterName, parameterMap.get(parameterName)));
			}

			parameterMap.put("oauth_consumer_key", consumerKey);
			parameterMap.put("oauth_token", accessToken);
			parameterMap.put("oauth_timestamp", ""+(System.currentTimeMillis()/1000));
			parameterMap.put("oauth_nonce", generateNonce());
			parameterMap.put("oauth_version", "1.0");
			parameterMap.put("oauth_signature_method", "HMAC-SHA1");


			for(String key : parameterMap.keySet()){
				paramsURL += percentEncode(key) + "=" + percentEncode(parameterMap.get(key)) + "&";
			}
			
			paramsURL = paramsURL.substring(0, paramsURL.length() - 1);
			encodedURL += percentEncode(paramsURL);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(baseURL(url));

			if(url.indexOf('?') != -1){
				httpPost.setEntity(new UrlEncodedFormEntity(postPrameters));
			}
			httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
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

			return responseData;
		}
		catch(InvalidAttributesException e)
		{
			e.printStackTrace();
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
			String encodedURL = "GET&" + percentEncode(baseURL(url)) + "&";
			String paramsURL = "";

			Map<String, String> parameterMap = getURLParameters(url);
			parameterMap.put("oauth_consumer_key", consumerKey);
			parameterMap.put("oauth_token", accessToken);
			parameterMap.put("oauth_timestamp", ""+(System.currentTimeMillis()/1000));
			parameterMap.put("oauth_nonce", generateNonce());
			parameterMap.put("oauth_version", "1.0");
			parameterMap.put("oauth_signature_method", "HMAC-SHA1");

			for(String parameterName : parameterMap.keySet()){
				paramsURL += percentEncode(parameterName) + "=" + percentEncode(parameterMap.get(parameterName)) + "&";
			}

			paramsURL = paramsURL.substring(0, paramsURL.length() - 1);
			encodedURL += percentEncode(paramsURL);



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
			return responseData;
		}
		catch(InvalidAttributesException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.err.println("Please format your URL properly");
			e.printStackTrace();
		}
		return "";
	}

	private TwitterJSON stringToJSONArray(String jsonString)
	{
		jsonString = jsonString.trim();
		jsonString = jsonString.charAt(0)=='[' ? jsonString : "["+jsonString+"]";
		return new TwitterJSON(jsonString);
	}

	private String oAuthSign(String input) throws InvalidAttributesException
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
			throw new InvalidAttributesException("Only official keys/tokens generated by Twitter are accepted");
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

		for(int i = 0; i < 32; i++){
			nonce.append(characters[gen.nextInt(characters.length)]);
		}

		return nonce.toString();
	}
	
	public String percentEncode(String text)
	{
		try {
			return urlEncoder.encode(text, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}