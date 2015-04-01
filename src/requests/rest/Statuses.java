package requests.rest;

import requests.HttpRequestHandler;

public class Statuses extends HttpRequestHandler{
		
	
	public Statuses(HttpRequestHandler request)
	{
		super(request);
	}
	
	public Statuses(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
	
	public String GETstatusesshowid(long id)
	{
		String url = "https://api.twitter.com/1.1/statuses/show.json?id=" + id;
		String result = get(url);
		return printJSON(result).toString();
	}
}
