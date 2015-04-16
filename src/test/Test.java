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
		
			Api api = new Api(credentialsFile.readLine(),
					credentialsFile.readLine(),
					credentialsFile.readLine(),
					credentialsFile.readLine());			
			
			long id = 586581749817184256l;
			
	}
}
