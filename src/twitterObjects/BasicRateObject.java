package twitterObjects;

public class BasicRateObject {
	int limit; 
	int remaining;
	long reset;
	public BasicRateObject()
	{
		
	}
	
	public void setValues(int a, int b, long c)
	{
		limit = a;
		remaining = b;
		reset = c;
	}
}
