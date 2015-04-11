package api;

import java.lang.reflect.Field;

import org.json.JSONObject;

import requests.rest.Statuses;
import requests.rest.UsersRequests;
import twitterObjects.Tweets;
import twitterObjects.Users;

public class Api {

	private Statuses statusesRequests;
	private UsersRequests usersRequests;

	public Api(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{		
		statusesRequests = new Statuses(consumerKey, consumerSecret, accessToken, accessTokenSecret);//because Statuses is now an HTTP Request object, I think this can be allowed
		usersRequests = new UsersRequests(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		
//		statuses = new Statuses(new HttpRequestHandler(consumerKey, consumerSecret, accessToken, accessTokenSecret)); //this is also allowed
//		users = new Users(new HttpRequestHandler(consumerKey, consumerSecret, accessToken, accessTokenSecret));
	}
	
	public Tweets tweetsObjectCreator(String jsonString) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
				
		JSONObject json = new JSONObject(jsonString);
		//fully qualified name required!
		Class<?> c = Class.forName("twitterObjects.Tweets");
		
		Tweets tweet = (Tweets) c.newInstance();
		
		Field []fields = c.getFields();
		
		for (Field field : fields) {
			Class<?> type = field.getType();
				
			String typeName = type.getSimpleName();
			String fieldName = field.getName();	
			
			/*if(json.has(fieldName))
				System.out.println(fieldName + " has");
			else
				System.out.println(fieldName + " Nothas");
			*/
			
			if(json.has(fieldName)){
					
				if (type.isPrimitive() || type == String.class){
			
					try{
											
	//					System.out.println(name + ": " + value);
						if (typeName.equals("int")) {							
							field.set(tweet, json.getInt(fieldName));						
					    }
						else if (typeName.equals("long")) {								
							field.set(tweet, json.getLong(fieldName));
							
					    }else if (typeName.equals("boolean")) {					    	
					    	field.set(tweet, json.getBoolean(fieldName));
					    }
					    else if (type == String.class) {				    	
					        field.set(tweet, json.getString(fieldName));
					    }
					}
					//exception is thrown when the field is "nullable"
					catch(org.json.JSONException e){
//						e.printStackTrace();
					}
				}
				else if(type == Users.class){
					try{						
						Users u = usersObjectCreator(json.get(fieldName).toString());
//						System.out.println(u.followers_count);
						field.set(tweet, u);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
				}
			}			
		}
		return tweet;
	}
	
	public Users usersObjectCreator(String jsonString) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		JSONObject json = new JSONObject(jsonString);
		//fully qualified name required!
		Class<?> c = Class.forName("twitterObjects.Users");
		
		Users user = (Users) c.newInstance();
		
		Field []fields = c.getFields();
		
		for (Field field : fields) {
			Class<?> type = field.getType();
				
			String typeName = type.getSimpleName();
			String fieldName = field.getName();	
			
			if(json.has(fieldName)){
				
				if (type.isPrimitive() || type == String.class){
			
					try{
											
	//					System.out.println(name + ": " + value);
						if (typeName.equals("int")) {							
							field.set(user, json.getInt(fieldName));						
					    }
						else if (typeName.equals("long")) {								
							field.set(user, json.getLong(fieldName));
							
					    }else if (typeName.equals("boolean")) {					    	
					    	field.set(user, json.getBoolean(fieldName));
					    }
					    else if (type == String.class) {				    	
					        field.set(user, json.getString(fieldName));
					    }
					}
					//exception is thrown when the field is "nullable"
					catch(org.json.JSONException e){
//						e.printStackTrace();
					}
				}				
			}
		}
		return user;
	}
	
	public void GETstatusesmentions_timeline()
	{

	}
	public void GETstatusesuser_timeline()
	{

	}
	public void GETstatuseshome_timeline()
	{

	}
	public void GETstatusesretweets_of_me()
	{

	}
	public void GETstatusesretweetsid()
	{
		//todo
	}
	//need to return Statuses object
	public Tweets getStatusesShowId(long id) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String result =  statusesRequests.GETstatusesshowid(id);
		return tweetsObjectCreator(result);
	}
	public void POSTstatusesdestroyid()
	{

	}
	public void POSTstatusesupdate()
	{
		//todo
	}
	public void POSTstatusesretweetid()
	{

	}
	public void POSTstatusesupdate_with_media()
	{

	}
	public void GETstatusesoembed()
	{

	}
	public void GETstatusesretweetersids()
	{

	}
	public void GETstatuseslookup()
	{
		//todo
	}
	public void POSTmediaupload()
	{

	}
	public void GETdirect_messagessent()
	{

	}
	public void GETdirect_messagesshow()
	{

	}
	public void GETsearchtweets()
	{
		//todo
	}
	public void GETdirect_messages()
	{

	}
	public void POSTdirect_messagesdestroy()
	{

	}
	public void POSTdirect_messagesnew()
	{

	}
	public void GETfriendshipsno_retweetsids()
	{

	}
	public void GETfriendsids()
	{
		//todo
	}
	public void GETfollowersids()
	{
		//todo
	}
	public void GETfriendshipsincoming()
	{

	}
	public void GETfriendshipsoutgoing()
	{

	}
	public void POSTfriendshipscreate()
	{

	}
	public void POSTfriendshipsdestroy()
	{

	}
	public void POSTfriendshipsupdate()
	{

	}
	public void GETfriendshipsshow()
	{

	}
	public void GETfriendslist()
	{

	}
	public void GETfollowerslist()
	{

	}
	public void GETfriendshipslookup()
	{

	}
	public void GETaccountsettings()
	{

	}
	public void GETaccountverify_credentials()
	{

	}
	public void POSTaccountsettings()
	{

	}
	public void POSTaccountupdate_delivery_device()
	{

	}
	public void POSTaccountupdate_profile()
	{

	}
	public void POSTaccountupdate_profile_background_image()
	{

	}
	public void POSTaccountupdate_profile_image()
	{

	}
	public void GETblockslist()
	{

	}
	public void GETblocksids()
	{

	}
	public void POSTblockscreate()
	{

	}
	public void POSTblocksdestroy()
	{

	}
	public void GETuserslookup()
	{
		//todo
	}
	public void GETusersshow()
	{
		//todo
	}
	public void GETuserssearch()
	{
		//todo
	}
	public void POSTaccountremove_profile_banner()
	{

	}
	public void POSTaccountupdate_profile_banner()
	{

	}
	public void GETusersprofile_banner()
	{

	}
	public void POSTmutesuserscreate()
	{

	}
	public void POSTmutesusersdestroy()
	{

	}
	public void GETmutesusersids()
	{

	}
	public void GETmutesuserslist()
	{

	}
	public void GETuserssuggestionsslug()
	{

	}
	public void GETuserssuggestions()
	{

	}
	public void GETuserssuggestionsslugmembers()
	{

	}
	public void GETfavoriteslist()
	{

	}
	public void POSTfavoritesdestroy()
	{

	}
	public void POSTfavoritescreate()
	{

	}
	public void GETlistslist()
	{

	}
	public void GETlistsstatuses()
	{

	}
	public void POSTlistsmembersdestroy()
	{

	}
	public void GETlistsmemberships()
	{

	}
	public void GETlistssubscribers()
	{

	}
	public void POSTlistssubscriberscreate()
	{

	}
	public void GETlistssubscribersshow()
	{

	}
	public void POSTlistssubscribersdestroy()
	{

	}
	public void POSTlistsmemberscreate_all()
	{

	}
	public void GETlistsmembersshow()
	{

	}
	public void GETlistsmembers()
	{

	}
	public void POSTlistsmemberscreate()
	{

	}
	public void POSTlistsdestroy()
	{

	}
	public void POSTlistsupdate()
	{

	}
	public void POSTlistscreate()
	{

	}
	public void GETlistsshow()
	{

	}
	public void GETlistssubscriptions()
	{

	}
	public void POSTlistsmembersdestroy_all()
	{

	}
	public void GETlistsownerships()
	{

	}
	public void GETsaved_searcheslist()
	{

	}
	public void GETsaved_searchesshowid()
	{

	}
	public void POSTsaved_searchescreate()
	{

	}
	public void POSTsaved_searchesdestroyid()
	{

	}
	public void GETgeoidplace_id()
	{

	}
	public void GETgeoreverse_geocode()
	{

	}
	public void GETgeosearch()
	{

	}
	public void POSTgeoplace()
	{

	}
	public void GETtrendsplace()
	{
		//todo
	}
	public void GETtrendsavailable()
	{
		//todo
	}
	public void GETapplicationrate_limit_status()
	{
		//todo
	}
	public void GEThelpconfiguration()
	{

	}
	public void GEThelplanguages()
	{

	}
	public void GEThelpprivacy()
	{

	}
	public void GEThelptos()
	{

	}
	public void GETtrendsclosest()
	{

	}
	public void POSTusersreport_spam()
	{

	}

}
