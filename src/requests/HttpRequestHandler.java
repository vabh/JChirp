package requests;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
			
	@Deprecated
	public String postOldVersion(String url)
	{
		try
		{
			String encodedBaseURL = "POST&" + authenticator.percentEncode(authenticator.baseURL(url)) + "&";

			Map<String, String> parameterMap = getURLParameters(url);

			List<NameValuePair> postPrameters = new ArrayList<NameValuePair>();
			for(String parameterName : parameterMap.keySet())
				postPrameters.add(new BasicNameValuePair(parameterName, parameterMap.get(parameterName)));

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(authenticator.baseURL(url));

			if(url.indexOf('?') != -1)
				httpPost.setEntity(new UrlEncodedFormEntity(postPrameters));

			httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
			httpPost.addHeader("Authorization", authenticator.generateAuthenticationHeader(encodedBaseURL, parameterMap));


			CloseableHttpResponse response = httpclient.execute(httpPost);
			String responseData = null;

			try 
			{
				HttpEntity entity = response.getEntity();

				InputStream instream = entity.getContent();
				try 
				{
					responseData = EntityUtils.toString(entity);
				} 
				finally 
				{
					instream.close();
					EntityUtils.consume(entity);
				}

			} 
			finally 
			{
				response.close();
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

	@Deprecated
	public String getOldVersion(String url)
	{
		try
		{
			String encodedBaseURL = "GET&" + authenticator.percentEncode(authenticator.baseURL(url)) + "&";

			Map<String, String> parameterMap = getURLParameters(url);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);

			httpGet.addHeader("Authorization", authenticator.generateAuthenticationHeader(encodedBaseURL, parameterMap));


			CloseableHttpResponse response = httpclient.execute(httpGet);
			String responseData = null;

			try
			{
				HttpEntity entity = response.getEntity();
				if (entity != null) 
				{
					InputStream instream = entity.getContent();
					try 
					{
						responseData = EntityUtils.toString(entity);
					} 
					finally 
					{
						instream.close();
						EntityUtils.consume(entity);
					}
				}
			}
			finally
			{
				response.close();
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
	
	public String post(String baseURL, Map<String, String> parameterMap)
	{
		try
		{
			String encodedBaseURL = "POST&" + authenticator.percentEncode(baseURL) + "&";

			List<NameValuePair> postPrameters = new ArrayList<NameValuePair>();
			for(String parameterName : parameterMap.keySet())
				postPrameters.add(new BasicNameValuePair(parameterName, parameterMap.get(parameterName)));
			

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(baseURL);

			if(parameterMap.size() > 0)
				httpPost.setEntity(new UrlEncodedFormEntity(postPrameters));

			httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
			httpPost.addHeader("Authorization", authenticator.generateAuthenticationHeader(encodedBaseURL, parameterMap));


			CloseableHttpResponse response = httpclient.execute(httpPost);
			String responseData = null;

			try 
			{
				HttpEntity entity = response.getEntity();

				InputStream instream = entity.getContent();
				try 
				{
					responseData = EntityUtils.toString(entity);
				} 
				finally 
				{
					instream.close();
					EntityUtils.consume(entity);
				}

			} 
			finally 
			{
				response.close();
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
	
	public String get(String baseURL, Map<String, String> parameterMap)
	{
		try
		{
			String encodedBaseURL = "GET&" + authenticator.percentEncode(baseURL) + "&";
			String url = baseURL + "?";
			
			for(String parameterName : parameterMap.keySet())
				url += parameterName + "=" + parameterMap.get(parameterName) + "&";
			url = url.substring(0, url.length()-1);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);

			httpGet.addHeader("Authorization", authenticator.generateAuthenticationHeader(encodedBaseURL, parameterMap));

			CloseableHttpResponse response = httpclient.execute(httpGet);
			String responseData = null;

			try
			{
				HttpEntity entity = response.getEntity();
				if (entity != null) 
				{
					InputStream instream = entity.getContent();
					try 
					{
						responseData = EntityUtils.toString(entity);
					} 
					finally 
					{
						instream.close();
						EntityUtils.consume(entity);
					}
				}
			}
			finally
			{
				response.close();
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

	private Map<String,String> getURLParameters(String url)
	{
		Map<String, String> parameterMap = new TreeMap<String,String>();

		if(url.indexOf('?') != -1)
		{
			for(String parameter : url.split("\\?")[1].split("&"))
			{
				String keyValue[] = parameter.split("=");
				parameterMap.put(keyValue[0], keyValue[1]);
			}
		}
		return parameterMap;
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
