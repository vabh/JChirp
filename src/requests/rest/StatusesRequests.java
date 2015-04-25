package requests.rest;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

	public String GETstatusesshowid(String id, String... optionalParams) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Map<String, String> parameterMap = new TreeMap<String, String>();
		parameterMap.put("id", id);
		addOptionalParametersToParameterMap(parameterMap, optionalParams);
		String result = get("https://api.twitter.com/1.1/statuses/show.json", parameterMap);
		return result;
	}

	public String POSTstatuseslookup(Collection<String> ids, String... optionalParams)
	{
		String csvIDs = "";
		for(String id : ids)
			csvIDs += id+",";
		csvIDs = csvIDs.substring(0, csvIDs.length()-1);
		
		String baseURL = "https://api.twitter.com/1.1/statuses/lookup.json";
		Map<String, String> parameterMap = new TreeMap<String, String>();
		parameterMap.put("id", csvIDs);
		addOptionalParametersToParameterMap(parameterMap, optionalParams);

		return post(baseURL, parameterMap);	
	}

	public String GETstatusesretweetsid(String id, String... optionalParams)
	{
		String baseURL = "https://api.twitter.com/1.1/statuses/retweets/" + id + ".json";
		Map<String, String> parameterMap = new TreeMap<String, String>();
		addOptionalParametersToParameterMap(parameterMap, optionalParams);

		return get(baseURL, parameterMap);
	}

	public String POSTstatusesretweetid(String status, String... optionalParams)
	{
		String baseURL = "https://api.twitter.com/1.1/statuses/update.json";
		Map<String, String> parameterMap = new TreeMap<String, String>();
		parameterMap.put("status", status);
		addOptionalParametersToParameterMap(parameterMap, optionalParams);

		return post(baseURL, parameterMap);
	}
	
	public String GETsearchtweets(String q, String... optionalParams)
	{
		String baseURL = "https://api.twitter.com/1.1/search/tweets.json";
		Map<String, String> parameterMap = new TreeMap<String, String>();
		parameterMap.put("q", q);
		addOptionalParametersToParameterMap(parameterMap, optionalParams);
		return get(baseURL, parameterMap);
	}
	
}
