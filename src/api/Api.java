package api;

import java.lang.reflect.Field;

import org.json.JSONObject;

import requests.rest.RateRequests;
import requests.rest.StatusesRequests;
import requests.rest.UsersRequests;
import twitterObjects.Rates;
import twitterObjects.Tweets;
import twitterObjects.Users;

public class Api {

	private StatusesRequests statusesRequests;
	private UsersRequests usersRequests;
	private RateRequests rateRequests;

	public Api(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		//because Statuses is now an HTTP Request object, I think this can be allowed
		statusesRequests = new StatusesRequests(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		usersRequests = new UsersRequests(consumerKey, consumerSecret, accessToken, accessTokenSecret);	
		rateRequests = new RateRequests(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}

	public Tweets tweetsObjectCreator(String jsonString) throws ClassNotFoundException, InstantiationException, IllegalAccessException{

		JSONObject json = new JSONObject(jsonString);
		//fully qualified name required!
		Class<?> c = Class.forName("twitterObjects.Tweets");

		Tweets tweet = (Tweets) c.newInstance();

		Field []fields = c.getFields();

		for (Field field : fields) {
			Class<?> type = field.getType();

			String fieldType = type.getSimpleName();
			String fieldName = field.getName();				

			if(json.has(fieldName) && !json.isNull(fieldName)){

				if (type.isPrimitive() || type == String.class){

					try{

						//					System.out.println(name + ": " + value);
						if (fieldType.equals("int")) {							
							field.set(tweet, json.getInt(fieldName));						
						}
						else if (fieldType.equals("long")) {								
							field.set(tweet, json.getLong(fieldName));

						}else if (fieldType.equals("boolean")) {					    	
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
						field.set(tweet, u);
					}
					catch(Exception e){
						//						e.printStackTrace();
					}				
				}
				//for retweeted_status object
				else if(type == Tweets.class){
					try{						
						Tweets t = tweetsObjectCreator(json.get(fieldName).toString());
						field.set(tweet, t);
					}
					catch(Exception e){
						//						e.printStackTrace();
					}
				}
				//looks hacky, can be bettered but exams :/
				else if(fieldName.equals("coordinates")){
					try{
						JSONObject tjson = json.getJSONObject(fieldName);
						String coordsStr = tjson.get(fieldName).toString();
						String typeCoords = tjson.getString("type");

						String coords[] = (coordsStr + ", " + typeCoords).split(",");						
						coords[0] = coords[0].substring(coords[0].indexOf('[') + 1).trim();
						coords[1] = coords[1].substring(0, coords[1].indexOf(']')).trim();
						coords[2] = coords[2].trim();

						field.set(tweet, coords);
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
		//		System.out.println(new JSONHandler().printJSON(result).toString());
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
	public Rates GETapplicationrate_limit_status(String types[])
	{
		String result =  rateRequests.getRateLimitStatus(types);		
		try {
			return new Rates(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Rates GETapplicationrate_limit_status()
	{
		try
		{
			String result =  rateRequests.getRateLimitStatus();		
			return new Rates(result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
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
