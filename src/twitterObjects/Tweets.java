package twitterObjects;

import java.util.Arrays;

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
	@Override
	public String toString() {
		return "Tweets [annotations=" + annotations + ", contributors="
				+ Arrays.toString(contributors) + ", coordinates="
				+ Arrays.toString(coordinates) + ", created_at=" + created_at
				+ ", current_user_retweet="
				+ Arrays.toString(current_user_retweet) + ", entities="
				+ Arrays.toString(entities) + ", favorite_count="
				+ favorite_count + ", favorited=" + favorited
				+ ", filter_level=" + filter_level + ", id=" + id + ", id_str="
				+ id_str + ", in_reply_to_screen_name="
				+ in_reply_to_screen_name + ", in_reply_to_status_id="
				+ in_reply_to_status_id + ", in_reply_to_status_id_str="
				+ in_reply_to_status_id_str + ", in_reply_to_user_id="
				+ in_reply_to_user_id + ", in_reply_to_user_id_str="
				+ in_reply_to_user_id_str + ", lang=" + lang + ", place="
				+ place + ", possibly_sensitive=" + possibly_sensitive
				+ ", scopes=" + scopes + ", retweet_count=" + retweet_count
				+ ", retweeted=" + retweeted + ", retweeted_status="
				+ retweeted_status + ", source=" + source + ", text=" + text
				+ ", truncated=" + truncated + ", user=" + user
				+ ", withheld_copyright=" + withheld_copyright
				+ ", withheld_in_countries=" + withheld_in_countries
				+ ", withheld_scope=" + withheld_scope + "]";
	}
	
}
