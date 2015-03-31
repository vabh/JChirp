package requests;


public class JSONHandler
{
	
	public TwitterJSON printJSON(String jsonString)
	{
		jsonString = jsonString.trim();
		jsonString = jsonString.charAt(0)=='[' ? jsonString : "["+jsonString+"]";
		return new TwitterJSON(jsonString);
	}
}