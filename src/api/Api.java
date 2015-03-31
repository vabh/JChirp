package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import requests.HttpRequestHandler;
import requests.JSONHandler;
import requests.rest.Statuses;
import requests.rest.Users;

public class Api extends JSONHandler {

	private Statuses statuses;
	private Users users;

	HttpRequestHandler requests;

	public static void main(String args[]) throws IOException
	{
//		BufferedReader credentialsFile = new BufferedReader(new FileReader("credentials.txt"));		
//		try
//		{
//			//SingleUserOAuth obj = new SingleUserOAuth(consumer_key, consumer_secret,access_token,access_token_secret);
//
//			Api api = new Api(credentialsFile.readLine(),
//					credentialsFile.readLine(),
//					credentialsFile.readLine(),
//					credentialsFile.readLine());			
//			
//			long id = 576805180622041088l;
//			String r = api.GETstatusesshowid(id);
//
//			System.out.println(r);
//			//the status must not be encoded, ie spaces should be spaces and not %20, the twitter documentation is not consistent with other calls!
//			//String url = "https://api.twitter.com/1.1/followers/ids.json?cursor=-1&screen_name=mourjo_sen&count=5000"; 
//			//System.out.println(statusQuery.printJSON(statusQuery.get(url)));
//			//System.out.println(statusQuery.printJSON(statusQuery.post("https://api.twitter.com/1.1/users/lookup.json?screen_name=twitterapi,twitter")));
//
//
//
//		}
//		finally
//		{
//			credentialsFile.close();
//		}
	}

	public Api(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		statuses = new Statuses();
		requests = new HttpRequestHandler(consumerKey, consumerSecret, accessToken, accessTokenSecret);
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
	public String GETstatusesshowid(long id)
	{
		String url = statuses.GETstatusesshowid(id);
		String result = requests.get(url);
		return this.printJSON(result).toString();
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
