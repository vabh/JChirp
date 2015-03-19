package urlRequest;

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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class AppOnlyAuth {

	private String consumerKey;
	private String consumerSecret;

	private String accessToken;

	public AppOnlyAuth(String consumerKey, String consumerSecret) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
	}

	public TwitterJSON getJSON(String url) {
		return stringToJSONArray(url);
	}

	private TwitterJSON stringToJSONArray(String jsonString) {
		jsonString = jsonString.trim();
		jsonString = jsonString.charAt(0) == '[' ? jsonString : "["
				+ jsonString + "]";
		return new TwitterJSON(jsonString);
	}

	public String authenticate(String url) throws ClientProtocolException,
			IOException {

		String consumerKeyURLEncode = URLEncoder.encode(consumerKey, "UTF-8");
		String consumerSecretURLEncode = URLEncoder.encode(consumerSecret,
				"UTF-8");

		String bearerTokenCredentials = consumerKeyURLEncode + ":"
				+ consumerSecretURLEncode;

		String base64enc = new String(
				Base64.encodeBase64(bearerTokenCredentials.getBytes()));

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("grant_type",
				"client_credentials"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
				Consts.UTF_8);

		httppost.setEntity(entity);
		httppost.setHeader("Authorization", "Basic " + base64enc);
		httppost.setHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");

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

	public static void main(String args[]) throws IOException {
		BufferedReader credentialsFile = new BufferedReader(new FileReader(
				"credentials.txt"));
		AppOnlyAuth TwitterQuery;
		try {
			TwitterQuery = new AppOnlyAuth(credentialsFile.readLine(),
					credentialsFile.readLine());

			String url = "https://api.twitter.com/oauth2/token";

			String response = TwitterQuery.authenticate(url);
			
			TwitterJSON responseJSON = TwitterQuery.stringToJSONArray(response);
			System.out.println(responseJSON);
		} finally {
			credentialsFile.close();
		}
	}
}
