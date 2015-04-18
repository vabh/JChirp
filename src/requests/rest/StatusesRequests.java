package requests.rest;

import java.util.Collection;
import java.util.List;

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
		return result;
	}
	
	public String GETstatuseslookup(Collection<String> ids, boolean includeEntities, boolean trimUser, boolean map)
	{
		String csvIDs = "";
		for(String id : ids)
			csvIDs += id+",";
		csvIDs = csvIDs.substring(0, csvIDs.length()-1);
		return get("https://api.twitter.com/1.1/statuses/lookup.json?id=" + csvIDs + "&include_entities=" + includeEntities + "&trim_user=" + trimUser + "&map=" + map);
		
	}
	
	
}
