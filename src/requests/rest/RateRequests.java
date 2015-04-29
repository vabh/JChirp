package requests.rest;

import java.util.TreeMap;

import requests.HttpRequestHandler;

public class RateRequests extends HttpRequestHandler{

	public RateRequests(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
	
	public String getRateLimitStatus(String... types)
	{
		if(types == null || types.length == 0)
			return getRateLimitStatus();
		
		TreeMap<String,String> parameterMap = new TreeMap<String,String>();
		
		if(types.length > 1)
		{
			StringBuilder typeParameters = new StringBuilder();
			for(String type : types)
			{
				typeParameters.append(type);
				typeParameters.append(",");
			}
			typeParameters.replace(typeParameters.length()-1, typeParameters.length(), "");
			parameterMap.put("resources", typeParameters.toString());
		}
		else
			parameterMap.put("resources", types[0]);
		
		return get("https://api.twitter.com/1.1/application/rate_limit_status.json", parameterMap);
	}
	
	public String getRateLimitStatus()
	{
		String url = "https://api.twitter.com/1.1/application/rate_limit_status.json";
		return get(url, new TreeMap<String,String>());
	}

}
