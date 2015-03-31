package requests.rest;

import requests.HttpRequestHandler;
import requests.JSONHandler;

public class Statuses extends JSONHandler{
		
	HttpRequestHandler requests;
	
	public Statuses(HttpRequestHandler requests)
	{
		this.requests = requests;
	}
	
	public String GETstatusesshowid(long id)
	{
		String url = "https://api.twitter.com/1.1/statuses/show.json?id=" + id;
		String result = requests.get(url);
		return this.printJSON(result).toString();
	}
}
