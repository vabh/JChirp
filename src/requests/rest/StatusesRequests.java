package requests.rest;

import requests.HttpRequestHandler;

public class StatusesRequests extends HttpRequestHandler{
		
	
	public StatusesRequests(HttpRequestHandler request)
	{
		super(request);
	}
	
	public StatusesRequests(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
	
	public String GETstatusesshowid(long id) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String url = "https://api.twitter.com/1.1/statuses/show.json?id=" + id;
		String result = get(url);
//		System.out.println(printJSON(result).toString());
		return result;
	}
	
	
}
