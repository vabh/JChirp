package requests.rest;

import java.lang.reflect.Field;

import org.json.JSONObject;

import requests.HttpRequestHandler;
import twitterObjects.Tweets;

public class Statuses extends HttpRequestHandler{
		
	
	public Statuses(HttpRequestHandler request)
	{
		super(request);
	}
	
	public Statuses(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
	
	public Tweets GETstatusesshowid(long id) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String url = "https://api.twitter.com/1.1/statuses/show.json?id=" + id;
		String result = get(url);
//		System.out.println(printJSON(result).toString());
		return createObject(result);
	}
	
	private Tweets createObject(String jsonString) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		String key = "";
		JSONObject json = new JSONObject(jsonString);
		//fully qualified name required!
		Class<?> c = Class.forName("twitterObjects.Tweets");
		
		Tweets tweet = (Tweets) c.newInstance();
		
		Field []fields = c.getFields();
		
		for (Field field : fields) {
			Class<?> type = field.getType();
					
			if(type.isPrimitive() || type == String.class){
				
				String typeName = type.getSimpleName();
				try{
					String name = field.getName();						
//					System.out.println(name + ": " + value);
					if (typeName.equals("int")) {							
						field.set(tweet, json.getInt(name));						
				    }
					else if (typeName.equals("long")) {								
						field.set(tweet, json.getLong(name));
												
				    }else if (typeName.equals("boolean")) {
				    	
				    	field.set(tweet, json.getBoolean(name));
				    }
				    else if (type == String.class) {				    	
				        field.set(tweet, json.getString(name));
				    }
				}
				catch(org.json.JSONException e){
//					e.printStackTrace();
				}
			}
		}
		return tweet;		
	}
}
