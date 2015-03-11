package urlRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Trial {

	public static void main(String[] args) throws IOException
	{
		System.out.println(get("http://mourjo.github.io/"));

	}
	
	public static String get(String urlString) throws IOException
	{
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        String inputLine;
        StringBuilder out = new StringBuilder("");

        while ((inputLine = in.readLine()) != null)
        {
            out.append(inputLine);
        	out.append("\n");
        }
        
        in.close();
        return out.toString();
    }

}
