package twitterObjects;

import org.json.JSONArray;
import org.json.JSONObject;

public class IDCollection {
	public String ids[];
	public String previousCursor;
	public String nextCursor;
	
	public IDCollection(String httpResponse)
	{
		JSONObject jsonResponse = new JSONObject(httpResponse);
		JSONArray jsonIDs = jsonResponse.getJSONArray("ids");

		ids = new String[jsonIDs.length()];
		
		for(int i = 0; i < jsonIDs.length(); i++)
			ids[i] = jsonIDs.get(i).toString();

		previousCursor = jsonResponse.getString("previous_cursor_str");
		nextCursor = jsonResponse.getString("next_cursor_str");
	}
	
	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder("ids: ");
		for(String id : ids)
		{
			s.append(id);
			s.append(", ");
		}
		s.replace(s.length()-2, s.length(), "");
		
		s.append("\n");
		s.append("next_cursor: ");
		s.append(nextCursor);
		s.append("\n");
		s.append("previous_cursor: ");
		s.append(previousCursor);
		s.append("\n");
		
		return s.toString();
	}
}
