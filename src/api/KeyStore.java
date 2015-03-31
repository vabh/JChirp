package api;

public class KeyStore {
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;
	
	
	public KeyStore(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret; 
	}

	public String getConsumerKey()
	{
		return consumerKey;
	}
	
	public String getAccessToken()
	{
		return accessToken;
	}
	
	public String getSecret()
	{
		return consumerSecret + "&" + accessTokenSecret;
	}
}
