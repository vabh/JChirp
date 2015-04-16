package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
				api.GETapplicationrate_limit_status();
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
