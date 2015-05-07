package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

import twitterObjects.Rates;
import twitterObjects.Tweets;
import twitterObjects.Users;
import api.Api;
import api.SingleUserOAuth;

public class Test {
	public static void main(String[] args) throws IOException {
		BufferedReader credentialsFile = new BufferedReader(new FileReader("credentials.txt"));		
		try
		{
			Api api = new Api(credentialsFile.readLine(),
					credentialsFile.readLine(),
					credentialsFile.readLine(),
					credentialsFile.readLine());			
			
			try {
//				SingleUserOAuth suo = new SingleUserOAuth("igww5OmcxwF7rkFbURosDkzs3","hLfh1kx8DO0gfwt9MWu5RZIce5KwNvOtVVWjGUsnH0xNfFVBYJ",
//						"451474915-u8IFqro8FygvTRK1Hlh85A9bLG2KXCVh1Igxjj8u", "ZYhROTQk4xW5SjWM7MX8ZfLii5KOjQlwxmz2z1szOSHeA");
//				TreeMap<String, String> t = new TreeMap<String,String>();
//				t.put("sdfsdf", "*");
//				System.out.println(suo.generateAuthenticationHeader("https://api.twitter.com/1.1/statuses/retweet/id.json", t));
//				System.out.println("awe****sd88 *sd8a**+++dsasd* sdasd".replace("*","%2A").replace("+",""));
				
				//tests for all implemented methods:
				
				
				//rates
				Rates r1 = api.GETapplicationrate_limit_status("users,friends"); //selected few
				System.out.println(r1);
				System.out.println("----");
				Rates r2 = api.GETapplicationrate_limit_status(); //all
				System.out.println(r2);
				System.out.println("----");
				System.out.println(r2.statusesLookup.limit);
				System.out.println(r2.statusesLookup.remaining);
				
				//get statuses retweets ids.
				for(Tweets t : api.GETstatusesretweetsid("509457288717819904"))
					System.out.println(t.text);
				
				//post status
				System.out.println(api.POSTstatusesupdate("Hello Ladies + Gentlemen, a signed OAuth request!!",
						"place_id,display_coordinates,trim_user",
						"df51dec6f4ee2b2c",true,true));//all characters but * are working (wonder why * is not)  
				
				//statuses show id
				System.out.println(api.getStatusesShowId("210462857140252672"));
				System.out.println(api.getStatusesShowId("210462857140252672", "trim_user,include_my_retweet,include_entities",true, true, false));
				
				//statuses lookup (post)
				for(Tweets t : api.POSTstatuseslookup(Arrays.asList(new String[]{"20","432656548536401920"}), "trim_user, include_entities ", true, false))
					System.out.println(t);
				
				//retweets of tweets
				for(Tweets t : api.GETstatusesretweetsid("509457288717819904","trim_user,count",false,10))
					System.out.println(t);
				for(Tweets t : api.GETstatusesretweetsid("509457288717819904"))
					System.out.println(t);

				//users lookup by uid (post)
				for(Users u : api.POSTuserslookupByUserID(new String[]{"783214","6253282"}))
					System.out.println(u);
				for(Users u : api.POSTuserslookupByUserID(new String[]{"783214","6253282"},"include_entities",false))
					System.out.println(u);
				
				//users lookup by screen name (post)
				for(Users u : api.POSTuserslookupByScreenName(new String[]{"mourjo_sen","anuvabh18"}))
					System.out.println(u);
				for(Users u : api.POSTuserslookupByScreenName(new String[]{"mourjo_sen","anuvabh18"},"include_entities ",false))
					System.out.println(u);
				
				
				//search tweets
				for(Tweets t : api.GETsearchtweets("net neutrality"))
					System.out.println(t.text);
				for(Tweets t : api.GETsearchtweets("net neutrality", "result_type,count,since_id,", "popular", 100, 12345))
					System.out.println(t.text);
					
				
				
				//follower ids by uid
				System.out.println(api.GETfollowersidsByUserID("18839785"));
				System.out.println(api.GETfollowersidsByUserID("18839785","cursor", 1499397628062133319L));
				//follower ids by screenname
				System.out.println(api.GETfollowersidsByScreenName("mourjo_sen"));
				
				//friends by id
				System.out.println(api.GETfriendsidsByUserID("18839785").ids[0]);
				System.out.println(api.GETfriendsidsByUserID("18839785"));
				//friends by screenname
				System.out.println(api.GETfriendsidsByScreenName("anuvabh18", "stringify_ids", true));
				
				
				//users/show
				System.out.println(api.GETusersshowByUserID("18839785","include_entities",false));
				System.out.println(api.GETusersshowByScreenName("nytimes").description);
				
				
				//users/search
				for(Users u : api.GETuserssearch("kolkata -atletico -kkr"))
					System.out.println(u.screen_name);
					
					
				
				
				
				
			}catch (Exception e){
				e.printStackTrace();
			}							
		}
		finally
		{
			credentialsFile.close();
		}
	}
}