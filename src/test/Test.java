package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import twitterObjects.Rates;
import twitterObjects.Tweets;
import api.Api;

public class Test {
	public static void main(String[] args) throws IOException {
		BufferedReader credentialsFile = new BufferedReader(new FileReader("credentials.txt"));		
		try
		{
			Api api = new Api(credentialsFile.readLine(),
					credentialsFile.readLine(),
					credentialsFile.readLine(),
					credentialsFile.readLine());			
			
			long id = 576895494066065408l;
			try {
//				Tweets t = api.getStatusesShowId(id);
//				System.out.println(api.GETapplicationrate_limit_status());
				
				
				
				
//				Rates r1 = api.GETapplicationrate_limit_status(new String[]{"users", "help"}); //selected few
//				System.out.println(r1);
//				System.out.println("----");
//				Rates r2 = api.GETapplicationrate_limit_status(); //all
//				System.out.println(r2);
//				System.out.println("----");
//				System.out.println(r2.statusesLookup.limit);
//				System.out.println(r2.statusesLookup.remaining);
				
				for(Tweets t : api.GETstatuseslookup(Arrays.asList(new String[]{"20", "432656548536401920"}), true, true, false))
				System.out.println(t);
				
				
				
//				System.out.println(t.coordinates[1]);															
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
