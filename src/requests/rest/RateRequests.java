package requests.rest;

import requests.HttpRequestHandler;

public class RateRequests extends HttpRequestHandler{

	public RateRequests(HttpRequestHandler request) {
		super(request);
	}
	
	public RateRequests(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
	
	public String getRateLimitStatus(String[] types)
	{
		if(types == null || types.length == 0)
			return getRateLimitStatus();
		
		StringBuilder url = new StringBuilder("https://api.twitter.com/1.1/application/rate_limit_status.json?resources=");
		for(String type : types)
			url.append(type + ",");
		
		url.replace(url.length()-1, url.length(), "");
		String result = get(url.toString());
		return result;
	}
	
	public String getRateLimitStatus()
	{
		StringBuilder url = new StringBuilder("https://api.twitter.com/1.1/application/rate_limit_status.json");
		String result = get(url.toString());
		return result;
	}

}
