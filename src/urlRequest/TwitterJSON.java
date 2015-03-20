package urlRequest;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class TwitterJSON extends JSONArray 
{
	private final static String TAB = "    ";

	TwitterJSON(String jsonString)
	{
		super(jsonString);
	}

	@Override
	public String toString()
	{
		return printJSONArray();
	}

	public String printJSONArray()
	{
		String str = "{\n";
		for(int i = 0; i < length(); i++)
		{
			JSONObject j = optJSONObject(i);


			@SuppressWarnings("unchecked")
			Iterator<String> x = j.keys();

			while(x.hasNext())
			{
				String key = x.next();
				if(j.get(key) instanceof JSONArray)
					str +=  TAB + "[\n" + printJSONArray(j.getJSONArray(key), 2) + TAB + "]\n";
				else if(j.get(key) instanceof JSONObject)
						str += TAB + key + " : {\n" + printJSONObject(j.getJSONObject(key), 2) + TAB + "}\n";
					else
						str += TAB + key + " : " + j.get(key) + "\n";
			}

			str += "}\n\n{\n";


		}
		return str.substring(0, str.length()-2);
	}

	private String printJSONArray(JSONArray arr, int tabCount)
	{
		String str = "", tabs = "";

		int c= tabCount;
		while(c-- > 0)
			tabs += TAB;

		for(int i = 0; i < arr.length(); i++)
		{
			JSONObject jsonObject = arr.optJSONObject(i);
			if(jsonObject != null)
			{
				@SuppressWarnings("unchecked")
				Iterator<String> iter = jsonObject.keys();

				while(iter.hasNext())
				{
					String key = iter.next();
					
					if(jsonObject.get(key) instanceof JSONArray)
						str += tabs + key + ": [\n" + printJSONArray(jsonObject.getJSONArray(key), tabCount + 1) + tabs + "]\n";
					
					else if(jsonObject.get(key) instanceof JSONObject)
							str += tabs + key + " : {\n" + printJSONObject(jsonObject.getJSONObject(key), tabCount + 1) + tabs + "}\n";
						else
							str += tabs + key + " : " + jsonObject.get(key) + "\n";
				}

			}
			else
				str += tabs + arr.get(i) + "\n";
		}
		
		return str;
	}

	private String printJSONObject(JSONObject jsonObject, int tabCount)
	{
		String str = "", tabs = "";

		int c= tabCount;
		while(c-- > 0)
			tabs += TAB;

		@SuppressWarnings("unchecked")
		Iterator<String> iter = jsonObject.keys();

		while(iter.hasNext())
		{
			String key = iter.next();
			if(jsonObject.get(key) instanceof JSONArray)
				str += tabs + key + " : [\n" +printJSONArray(jsonObject.getJSONArray(key), tabCount+1) + tabs + "]\n";
			else if(jsonObject.get(key) instanceof JSONObject)
					str += tabs + key + " : {\n" + printJSONObject(jsonObject.getJSONObject(key), tabCount+1) + tabs+"}\n";
			else
				str += tabs + key + " : " + jsonObject.get(key) + "\n";
		}

		return str;
	}
}
