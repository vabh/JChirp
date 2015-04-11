package requests.rest;

import requests.HttpRequestHandler;

public class UsersRequests extends HttpRequestHandler{

	public UsersRequests(HttpRequestHandler request)
	{
		super(request);
	}

	public UsersRequests(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
}
