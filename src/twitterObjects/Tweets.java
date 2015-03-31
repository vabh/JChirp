package twitterObjects;

public class Tweets {
	
	private Tweets annotations;
	private Users[] contributors;
	private String[] coordinates;
	private String created_at;
	private String[] current_user_retweet;
	private Entities[] entities;
	private long favorite_count;
	private boolean favorited;
	
	private enum filter_level_values { none, low, medium}
	private String filter_level;
	
	private long id;
	private String id_str;
	
	private String in_reply_to_screen_name;
	private long in_reply_to_status_id;
	private String in_reply_to_status_id_str;
	private long in_reply_to_user_id;
	private String in_reply_to_user_id_str;
	
	private String lang;
	private Places place;
	private boolean possibly_sensitive;
	
	//for promoted tweets
	private Object scopes;
	
	private int retweet_count;
	private boolean retweeted;
	private Tweets retweeted_status;
	
	private String source;
	
	private String text;
	
	private boolean truncated;
	
	private Users user;
	
	private boolean withheld_copyright;
	private boolean withheld_in_countries;
	private boolean withheld_scope;
}
