package twitterObjects;

public class URL {
	public String display_url;
	public String expanded_url;
	
	public int indices[];
	
	/*Wrapped URL, corresponding to the value embedded directly into 
	the raw Tweet text, and the values for the indices parameter. 
	For eg. "url":"http:\/\/t.co\/IOwBrTZR"*/
	public String url;
}
