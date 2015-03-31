package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import requests.TwitterJSON;

public class AppOnlyAuth extends Auth{

	private String consumerKey;
	private String consumerSecret;	

	private String authURL = "https://api.twitter.com/oauth2/token";
	private String invalidateURL = "https://api.twitter.com/oauth2/invalidate_token";

	private String accessToken;
	private boolean authenticated = true ;

	public AppOnlyAuth(String consumerKey, String consumerSecret) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;

		accessToken = null;
		authenticated = false;
	}

	public TwitterJSON toJSON(String url) {
		return stringToJSONArray(url);
	}

	private TwitterJSON stringToJSONArray(String jsonString) {
		jsonString = jsonString.trim();
		jsonString = jsonString.charAt(0) == '[' ? jsonString : "[" + jsonString + "]";
		return new TwitterJSON(jsonString);
	}

	public String getKeyValue(String json, String key) throws org.json.JSONException {
		return new JSONObject(json).getString(key);
	}

	public String authenticate() throws ClientProtocolException, IOException {

		String consumerKeyURLEncode = URLEncoder.encode(consumerKey, "UTF-8");
		String consumerSecretURLEncode = URLEncoder.encode(consumerSecret, "UTF-8");

		String bearerTokenCredentials = consumerKeyURLEncode + ":" + consumerSecretURLEncode;

		String base64enc = new String( Base64.encodeBase64(bearerTokenCredentials.getBytes()));

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(authURL);

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("grant_type", "client_credentials"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

		httppost.setEntity(entity);
		httppost.setHeader("Authorization", "Basic " + base64enc);
		httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

		CloseableHttpResponse response = httpclient.execute(httppost);

		String responseData = null;
		try {
			HttpEntity entityRes = response.getEntity();
			if (entityRes != null) {
				InputStream instream = entityRes.getContent();
				try {
					responseData = EntityUtils.toString(entityRes);
				} finally {
					instream.close();
					EntityUtils.consume(entity);
				}
			}
		} finally {
			response.close();
		}
		return responseData;
	}

	public void getToken() { //@MS: I changed the name from getAccessToken to getToken because the base class Auth has a getToken 

		if (authenticated)
			return;
		try {
			String authResponse = authenticate();
			try {
				accessToken = getKeyValue(authResponse, "access_token");
				authenticated = true;
			} catch (org.json.JSONException e) {
				e.printStackTrace();
				System.out.println("Invalid credentials");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String invalidateToken() throws  ClientProtocolException, IOException {

		String consumerKeyURLEncode = URLEncoder.encode(consumerKey, "UTF-8");
		String consumerSecretURLEncode = URLEncoder.encode(consumerSecret, "UTF-8");

		String bearerTokenCredentials = consumerKeyURLEncode + ":" + consumerSecretURLEncode;

		String base64enc = new String( Base64.encodeBase64(bearerTokenCredentials.getBytes()));

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(invalidateURL);

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();		
		formparams.add(new BasicNameValuePair("access_token", accessToken));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

		httppost.setEntity(entity);
		httppost.setHeader("Authorization", "Basic " + base64enc);
		httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

		CloseableHttpResponse response = httpclient.execute(httppost);

		String responseData = null;
		try {
			HttpEntity entityRes = response.getEntity();
			if (entityRes != null) {
				InputStream instream = entityRes.getContent();
				try {
					responseData = EntityUtils.toString(entityRes);
				} finally {
					instream.close();
					EntityUtils.consume(entity);
				}
			}
		} finally {
			response.close();
		}
		return responseData;
	}

	public String get(String url) throws ClientProtocolException, IOException {

		if(!authenticated)
			//throw exception
			return null;

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Authorization", "Bearer " + accessToken);
		httpget.setHeader("Accept-Encoding", "gzip");

		CloseableHttpResponse response = httpclient.execute(httpget);
		String responseData = null;

		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					responseData = EntityUtils.toString(entity);
				} finally {
					instream.close();
				}
			}
		} finally {
			response.close();
		}
		return responseData;
	}

	public static void main(String args[]) throws IOException {
		BufferedReader credentialsFile = new BufferedReader(new FileReader("credentials.txt"));
		AppOnlyAuth TwitterQuery;

		try {
			TwitterQuery = new AppOnlyAuth(credentialsFile.readLine(), credentialsFile.readLine());

			TwitterQuery.getToken();

			System.out.println(TwitterQuery.accessToken);

			String url = "https://api.twitter.com/1.1/statuses/user_timeline.json?count=10&screen_name=twitterapi";

			System.out.println(TwitterQuery.toJSON(TwitterQuery.get(url)));
			System.out.println(TwitterQuery.toJSON(TwitterQuery.invalidateToken()));

		} 
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			credentialsFile.close();
		}
	}
}