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
	
	public Tweets createObject(String jsonString) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		String key = "";
		JSONObject json = new JSONObject(jsonString);
		//fully qualified name required!
		Class<?> c = Class.forName("twitterObjects.Tweets");
		
		Tweets tweet = (Tweets) c.newInstance();
		
		Field []fields = c.getFields();
		
		for (Field field : fields) {
			Class<?> type = field.getType();
					
			if(type.isPrimitive() || type == String.class){
								
				try{
					String name = field.getName();	
					String value = json.getString(name).toString();
//					System.out.println(name + ": " + value);
					if (type == Integer.class || type == int.class) {				
						field.set(tweet, Integer.parseInt(value));
						System.out.println(value);
				    }
					else if (type == Long.class || type == long.class) {						
						field.set(tweet, Long.parseLong(value));
				    }else if (type == Boolean.class || type == boolean.class) {
				    	field.set(tweet, Boolean.parseBoolean(value));
				    }
				    else if (type == Object.class || type == String.class) {
				        field.set(tweet, value);
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
