package requests.rest;

import java.util.Collection;

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
	
	
	public String GETuserslookupByUserID(Collection<String> userIDs, boolean includeEntities)
	{
		String uidsCsv = "";
		for(String uid : userIDs)
			uidsCsv += uid + ",";
		uidsCsv.substring(0, uidsCsv.length() - 1);
		
		return get("https://api.twitter.com/1.1/users/lookup.json?user_id=" + uidsCsv + "&include_entities=" + includeEntities);
	}
	
	public String GETuserslookupByScreenName(Collection<String> screenNames, boolean includeEntities)
	{
		String uidsCsv = "";
		for(String uid : screenNames)
			uidsCsv += uid + ",";
		uidsCsv.substring(0, uidsCsv.length() - 1);
		
		return get("https://api.twitter.com/1.1/users/lookup.json?screen_name=" + uidsCsv + "&include_entities=" + includeEntities);
	}
}
