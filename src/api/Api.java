package api;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.codec.net.URLCodec;
import org.json.JSONArray;
import org.json.JSONException;
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
		statusesRequests = new StatusesRequests(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		usersRequests = new UsersRequests(consumerKey, consumerSecret, accessToken, accessTokenSecret);	
		rateRequests = new RateRequests(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}

	private Tweets[] tweetObjectArrayCreator(String str)
	{
		JSONArray tweetsJson = new JSONArray(str);
		Tweets tweets[] = new Tweets[tweetsJson.length()];
		for(int i = 0; i < tweetsJson.length(); i++)
		{
			try {
				tweets[i] = new Tweets(tweetsJson.getJSONObject(i).toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return tweets;
	}

	private Users[] userObjectArrayCreator(String str)
	{
		JSONArray usersJson = new JSONArray(str);
		Users users[] = new Users[usersJson.length()];
		for(int i = 0; i < usersJson.length(); i++)
		{
			try {
				users[i] = new Users(usersJson.getJSONObject(i).toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return users;
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

	public Tweets[] GETstatusesretweetsid(String id, Object... optionalParams)
	{
		return tweetObjectArrayCreator(statusesRequests.GETstatusesretweetsid(id, objectArrayToStringArray(optionalParams)));
	}

	public Tweets getStatusesShowId(String id, Object... optionalParams) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String result =  statusesRequests.GETstatusesshowid(id, objectArrayToStringArray(optionalParams));		
		return new Tweets(result);
	}

	public void POSTstatusesdestroyid()
	{

	}
	public Tweets POSTstatusesupdate(String status, Object... optionalParams) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		return new Tweets(statusesRequests.POSTstatusesretweetid(status, objectArrayToStringArray(optionalParams)));
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

	public Tweets[] GETstatuseslookup(String[] ids, Object... optionalParams)
	{
		return tweetObjectArrayCreator(statusesRequests.POSTstatuseslookup(Arrays.asList(ids), objectArrayToStringArray(optionalParams)));
	}

	public Tweets[] POSTstatuseslookup(Collection<String> ids, Object... optionalParams)
	{
		return tweetObjectArrayCreator(statusesRequests.POSTstatuseslookup(ids, objectArrayToStringArray(optionalParams)));
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
	public Tweets[] GETsearchtweets(String q, Object... optionalParams)
	{
		JSONObject response = new JSONObject(statusesRequests.GETsearchtweets(q, objectArrayToStringArray(optionalParams)));
		return tweetObjectArrayCreator(response.get("statuses").toString());
		//search_metadata is not returned right now, should it?
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
	
	public Users[] POSTuserslookupByUserID(String[] userIDs, Object... optionalParams)
	{
		return userObjectArrayCreator(usersRequests.POSTuserslookupByUserID(Arrays.asList(userIDs), objectArrayToStringArray(optionalParams)));
	}

	public Users[] POSTuserslookupByUserID(Collection<String> userIDs, Object... optionalParams)
	{
		return userObjectArrayCreator(usersRequests.POSTuserslookupByUserID(userIDs, objectArrayToStringArray(optionalParams)));
	}
	
	public Users[] POSTuserslookupByScreenName(String[] screenNames, Object... optionalParams)
	{
		String res = usersRequests.POSTuserslookupByScreenName(Arrays.asList(screenNames), objectArrayToStringArray(optionalParams));
		return userObjectArrayCreator(res);
	}

	public Users[] POSTuserslookupByScreenName(Collection<String> screenNames, Object... optionalParams)
	{
		String res = usersRequests.POSTuserslookupByScreenName(screenNames, objectArrayToStringArray(optionalParams));
		return userObjectArrayCreator(res);
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
	
	private String[] objectArrayToStringArray(Object... arr)
	{
		String[] stringParams = new String[arr.length];
		for(int i = 0; i < arr.length; i++)
			stringParams[i] = arr[i].toString();
		
		return stringParams;
	}

}
