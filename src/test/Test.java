package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
				Rates r1 = api.GETapplicationrate_limit_status(new String[]{"statuses"}); //selected few
				System.out.println(r1);
				System.out.println("----");
				Rates r2 = api.GETapplicationrate_limit_status(); //all
				System.out.println(r2);
				System.out.println("----");
				System.out.println(r2.statusesLookup.limit);
				System.out.println(r2.statusesLookup.remaining);
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
