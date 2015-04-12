package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import requests.JSONHandler;
import requests.TwitterJSON;
import requests.rest.StatusesRequests;
import twitterObjects.Tweets;
import api.Api;

public class Test {
	public static void main(String[] args) throws IOException {
		BufferedReader credentialsFile = new BufferedReader(new FileReader("credentials.txt"));		
		try
		{
			//SingleUserOAuth obj = new SingleUserOAuth(consumer_key, consumer_secret,access_token,access_token_secret);

			Api api = new Api(credentialsFile.readLine(),
					credentialsFile.readLine(),
					credentialsFile.readLine(),
					credentialsFile.readLine());			
			
			long id = 576895223080443905l;
//			String r = api.getStatusesShowId(id);
			
			try {
				Tweets t = api.getStatusesShowId(id);
				System.out.println(t.retweeted_status);															
			} catch (Exception e){
				e.printStackTrace();
			}
						
//			System.out.println(r);
			//the status must not be encoded, ie spaces should be spaces and not %20, the twitter documentation is not consistent with other calls!
//			String url = "https://api.twitter.com/1.1/followers/ids.json?cursor=-1&screen_name=mourjo_sen&count=5000"; 
			
//			System.out.println(statusQuery.printJSON(statusQuery.get(url)));
//			System.out.println(statusQuery.printJSON(statusQuery.post("https://api.twitter.com/1.1/users/lookup.json?screen_name=twitterapi,twitter")));	
		}
		finally
		{
			credentialsFile.close();
		}
	}
}
