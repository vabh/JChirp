package requests.rest;

import java.util.Collection;
import java.util.TreeMap;

import requests.HttpRequestHandler;

public class UsersRequests extends HttpRequestHandler{

	public UsersRequests(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
	
	public String POSTuserslookupByUserID(Collection<String> userIDs, String... optionalParams)
	{
		String uidCSV = "";
		for(String uid : userIDs)
			uidCSV += uid + ",";
		uidCSV = uidCSV.substring(0, uidCSV.length() - 1);
		
		String baseURL = "https://api.twitter.com/1.1/users/lookup.json";
		TreeMap<String, String> parameterMap = new TreeMap<String, String>();
		parameterMap.put("user_id", uidCSV);
		addOptionalParametersToParameterMap(parameterMap, optionalParams);

		return post(baseURL, parameterMap);
	}
	
	public String POSTuserslookupByScreenName(Collection<String> screenNames, String... optionalParams)
	{
		String screenNamesCSV = "";
		for(String screenName : screenNames)
			screenNamesCSV += screenName + ",";
		screenNamesCSV.substring(0, screenNamesCSV.length() - 1);
		
		String baseURL = "https://api.twitter.com/1.1/users/lookup.json";
		TreeMap<String, String> parameterMap = new TreeMap<String, String>();
		parameterMap.put("screen_name", screenNamesCSV);
		addOptionalParametersToParameterMap(parameterMap, optionalParams);

		return post(baseURL, parameterMap);
	}
	
	public String GETfollowersidsByUserID(String userID, String... optionalParams)
	{
		String baseURL = "https://api.twitter.com/1.1/followers/ids.json";
		
		TreeMap<String, String> parameterMap = new TreeMap<String, String>();
		parameterMap.put("user_id", userID);
		addOptionalParametersToParameterMap(parameterMap, optionalParams);

		return get(baseURL, parameterMap);
	}
	
	public String GETfollowersidsByScreenName(String screenName, String... optionalParams)
	{
		String baseURL = "https://api.twitter.com/1.1/followers/ids.json";
		
		TreeMap<String, String> parameterMap = new TreeMap<String, String>();
		parameterMap.put("screen_name", screenName);
		addOptionalParametersToParameterMap(parameterMap, optionalParams);

		return get(baseURL, parameterMap);
	}
	
	public String GETfriendsidsByUserID(String userID, String... optionalParams)
	{
		String baseURL = "https://api.twitter.com/1.1/friends/ids.json";
		
		TreeMap<String, String> parameterMap = new TreeMap<String, String>();
		parameterMap.put("user_id", userID);
		addOptionalParametersToParameterMap(parameterMap, optionalParams);

		return get(baseURL, parameterMap);
	}
	
	public String GETfriendsidsByScreenName(String screenName, String... optionalParams)
	{
		String baseURL = "https://api.twitter.com/1.1/friends/ids.json";
		
		TreeMap<String, String> parameterMap = new TreeMap<String, String>();
		parameterMap.put("screen_name", screenName);
		addOptionalParametersToParameterMap(parameterMap, optionalParams);

		return get(baseURL, parameterMap);
	}
	
	public String GETusersshowByUserID(String userID, String... optionalParams)
	{
		String baseURL = "https://api.twitter.com/1.1/users/show.json";
		
		TreeMap<String, String> parameterMap = new TreeMap<String, String>();
		parameterMap.put("user_id", userID);
		addOptionalParametersToParameterMap(parameterMap, optionalParams);

		return get(baseURL, parameterMap);
	}
	
	public String GETusersshowByScreenName(String screenName, String... optionalParams)
	{
		String baseURL = "https://api.twitter.com/1.1/users/show.json";
		
		TreeMap<String, String> parameterMap = new TreeMap<String, String>();
		parameterMap.put("screen_name", screenName);
		addOptionalParametersToParameterMap(parameterMap, optionalParams);

		return get(baseURL, parameterMap);
	}
	
	public String GETuserssearch(String q, String... optionalParams)
	{
		String baseURL = "https://api.twitter.com/1.1/users/search.json";
		TreeMap<String, String> parameterMap = new TreeMap<String, String>();
		parameterMap.put("q", q);
		addOptionalParametersToParameterMap(parameterMap, optionalParams);
		return get(baseURL, parameterMap);
	}
}
