package api;

import javax.crypto.spec.SecretKeySpec;

public class KeyStore {
	private String consumerKey;
	private String accessToken;
	private SecretKeySpec secretKeys;
	
	
	public KeyStore(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		this.consumerKey = consumerKey;
		this.accessToken = accessToken;
		secretKeys = new SecretKeySpec((consumerSecret + "&" + accessTokenSecret).getBytes(), "HmacSHA1");
	}

	public String getConsumerKey()
	{
		return consumerKey;
	}
	
	public String getAccessToken()
	{
		return accessToken;
	}
	
	public SecretKeySpec getSecret()
	{
		return secretKeys;
	}
}
