package urlRequest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SingleUserOAuth {
	
	private String consumer_key;
    private String consumer_secret;
    private String access_token;
    private String access_token_secret;
    
    
    public static void main(String args[]) throws InvalidKeyException, NoSuchAlgorithmException, URISyntaxException, EncoderException, ClientProtocolException, IOException
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
        	
        	System.out.println(TwitterQuery.get(url));
        	
    	}
    	finally
    	{
    		credentialsFile.close();
    	}
    }
    
    SingleUserOAuth(String ck, String cs, String at, String ats)
    {
    	consumer_key = ck;
        consumer_secret = cs;
        access_token = at;
        access_token_secret = ats;
    }
    
    public String get(String url) throws EncoderException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException, ClientProtocolException, IOException
    {
    	URLCodec urlEncoder = new URLCodec();
    	String encodedURL = "GET&" + urlEncoder.encode(getBaseURL(url))+"&";
    	String paramsURL = "";
    	
    	Map<String, String> params = getURLParams(url);
    	params.put("oauth_consumer_key", consumer_key);
    	params.put("oauth_token", access_token);
    	params.put("oauth_timestamp",""+(System.currentTimeMillis()/1000));
    	params.put("oauth_nonce","4991BC8A968CBD88FF4F4C3AD0AD4DB9");
    	params.put("oauth_version","1.0");
    	params.put("oauth_signature_method","HMAC-SHA1");
    	
    	for(String key : params.keySet())
    		paramsURL += key + "=" + params.get(key)+"&";
    	
    	paramsURL = paramsURL.substring(0, paramsURL.length() - 1);
    	encodedURL += urlEncoder.encode(paramsURL);
    	
    	CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		httpget.addHeader("Authorization", "OAuth oauth_consumer_key=\""+ consumer_key +"\", oauth_nonce=\""+params.get("oauth_nonce")+"\", oauth_signature=\""+getSignature(encodedURL)+"\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\""+params.get("oauth_timestamp")+"\", oauth_token=\""+access_token+"\", oauth_version=\"1.0\"");
		
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
				}
			}
		}
		finally
		{
			response.close();
		}
		return responseData;
    }
    
    private String getSignature(String data) throws NoSuchAlgorithmException, InvalidKeyException, URISyntaxException, EncoderException
    {
    	String key = consumer_secret + "&" + access_token_secret;
    	SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signingKey);
		return new URLCodec().encode(new String(Base64.encodeBase64(mac.doFinal(data.getBytes()))));
    }
    
    private String getBaseURL(String url)
    {
    	return url.split("\\?")[0];
    }
    
    private Map<String,String> getURLParams(String url) throws EncoderException
    {
    	Map<String, String> params = new TreeMap<String,String>();
    	for(String x : url.split("\\?")[1].split("&"))
    	{
    		String keyValue[] = x.split("=");
    		params.put(keyValue[0], new URLCodec().encode(keyValue[1]));
    	}
		return params;
    }

}
