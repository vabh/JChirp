package twitterObjects;

public class Tweets {
	
	public Tweets annotations;
	public Users[] contributors;
	public String[] coordinates;
	public String created_at;
	public String[] current_user_retweet;
	public Entities[] entities;
	public long favorite_count;
	public boolean favorited;
	
	public enum filter_level_values { none, low, medium}
	public String filter_level;
	
	public long id;
	public String id_str;
	
	public String in_reply_to_screen_name;
	public long in_reply_to_status_id;
	public String in_reply_to_status_id_str;
	public long in_reply_to_user_id;
	public String in_reply_to_user_id_str;
	
	public String lang;
	public Places place;
	public boolean possibly_sensitive;
	
	//for promoted tweets
	public Object scopes;
	
	public int retweet_count;
	public boolean retweeted;
	public Tweets retweeted_status;
	
	public String source;
	
	public String text;
	
	public boolean truncated;
	
	public Users user;
	
	public boolean withheld_copyright;
	public boolean withheld_in_countries;
	public boolean withheld_scope;
}
