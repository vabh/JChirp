package requests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.directory.InvalidAttributesException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import api.SingleUserOAuth;

public class HttpRequestHandler extends JSONHandler{
	SingleUserOAuth authenticator;	
	
	public HttpRequestHandler(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret)
	{
		authenticator = new SingleUserOAuth(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
	
	public HttpRequestHandler(HttpRequestHandler obj)
	{
		this.authenticator = obj.authenticator;
	}
			
	protected String post(String baseURL, Map<String, String> parameterMap)
	{
		try
		{
			StringBuilder encodedBaseURL = new StringBuilder("POST&");
			encodedBaseURL.append(authenticator.percentEncode(baseURL));
			encodedBaseURL.append("&");

			List<NameValuePair> postPrameters = new ArrayList<NameValuePair>();
			for(String parameterName : parameterMap.keySet())
				postPrameters.add(new BasicNameValuePair(parameterName, parameterMap.get(parameterName)));
			

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(baseURL);

			if(parameterMap.size() > 0)
				httpPost.setEntity(new UrlEncodedFormEntity(postPrameters));

			httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
			httpPost.addHeader("Authorization", authenticator.generateAuthenticationHeader(encodedBaseURL.toString(), parameterMap));


			CloseableHttpResponse response = httpclient.execute(httpPost);
			String responseData = null;

			HttpEntity entity = response.getEntity();
			if (entity != null) 
			{
				try 
				{
					entity.getContent();
					responseData = EntityUtils.toString(entity);
				} 
				finally 
				{
					EntityUtils.consume(entity);
					response.close();
				}
			}
			return responseData;
		}
		catch(InvalidAttributesException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.err.println("Please format your URL properly");
			e.printStackTrace();
		}
		return "";
	}
	
	protected String get(String baseURL, Map<String, String> parameterMap)
	{
		try
		{
			StringBuilder encodedBaseURL = new StringBuilder("GET&");
			encodedBaseURL.append(authenticator.percentEncode(baseURL));
			encodedBaseURL.append("&");
			
			StringBuffer urlBuilder = new StringBuffer(baseURL);
			urlBuilder.append("?");
			
			for(String parameterName : parameterMap.keySet())
			{
				urlBuilder.append(authenticator.percentEncode(parameterName));
				urlBuilder.append("=");
				urlBuilder.append(authenticator.percentEncode(parameterMap.get(parameterName)));
				urlBuilder.append("&");
			}
			String url = urlBuilder.substring(0, urlBuilder.length()-1);
			
			
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);

			httpGet.addHeader("Authorization", authenticator.generateAuthenticationHeader(encodedBaseURL.toString(), parameterMap));

			CloseableHttpResponse response = httpclient.execute(httpGet);
			String responseData = null;

			HttpEntity entity = response.getEntity();
			if (entity != null) 
			{
				try 
				{
					entity.getContent();
					responseData = EntityUtils.toString(entity);
				} 
				finally 
				{
					EntityUtils.consume(entity);
					response.close();
				}
			}
			return responseData;
		}
		catch(InvalidAttributesException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.err.println("Please format your URL properly");
			e.printStackTrace();
		}
		return "";
	}
	
	protected void addOptionalParametersToParameterMap(Map<String, String> parameterMap, String... optionalParams)
	{
		if(optionalParams.length > 0)
		{
			optionalParams[0] = optionalParams[0].replaceAll("\\s", "");
			String paramNames[] = optionalParams[0].split(",");

			if(paramNames.length != optionalParams.length - 1)
				throw new IllegalArgumentException();

			for(int i = 0; i < paramNames.length; i++)
				parameterMap.put(paramNames[i], optionalParams[i+1]);
		}
	}
}
