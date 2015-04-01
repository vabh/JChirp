package requests.rest;

import requests.HttpRequestHandler;

public class Users extends HttpRequestHandler{

	public Users(HttpRequestHandler request)
	{
		super(request);
	}

	public Users(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
}
