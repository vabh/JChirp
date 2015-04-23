package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import twitterObjects.Rates;
import twitterObjects.Tweets;
import twitterObjects.Users;
import api.Api;

public class Test {
	public static void main(String[] args) throws IOException {
		BufferedReader credentialsFile = new BufferedReader(new FileReader("credentials.txt"));		
		
			Api api = new Api(credentialsFile.readLine(),
					credentialsFile.readLine(),
					credentialsFile.readLine(),
					credentialsFile.readLine());			
			
			long id = 576895494066065408l;
			try {

				//tests for all implemented methods:
				
				//get statuses show id
				Tweets t1 = api.getStatusesShowId(id+"");
				System.out.println(t1);
				
				//rates
				Rates r1 = api.GETapplicationrate_limit_status(new String[]{"users", "help"}); //selected few
				System.out.println(r1);
				System.out.println("----");
				Rates r2 = api.GETapplicationrate_limit_status(); //all
				System.out.println(r2);
				System.out.println("----");
				System.out.println(r2.statusesLookup.limit);
				System.out.println(r2.statusesLookup.remaining);
				
				//get statuses retweets ids
				for(Tweets t : api.GETstatusesretweetsid("509457288717819904"))
					System.out.println(t.text);
				
				//post status
				System.out.println(api.POSTstatusesupdate("testing status via JChirp! ;-)",
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
