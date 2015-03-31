package api;

public class Auth {
	
	protected KeyStore keyStore;
	
	public Auth()
	{
		keyStore = null;
	}
	
	public Auth(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		keyStore = new KeyStore(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
	
	public String getConsumerKey()
	{
		return keyStore.getConsumerKey();
	}
	
	public String getAccessToken()
	{
		return keyStore.getAccessToken();
	}
	
	public String getSecret()
	{
		return keyStore.getSecret();
	}

}
