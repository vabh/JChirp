package twitterObjects;

public class Users {

	private boolean contributors_enabled;
	private String created_at;
	
	private boolean default_profile;
	private boolean default_profile_image;
	
	private String description;
	private Entities entities;
	private int favourites_count;
	
	private /*Type?*/ boolean follow_request_sent;
	private /*Type?*/boolean following;
	private int followers_count;
	private int friends_count;
	
	private boolean geo_enabled;
	private long id;
	private String id_str;
	private boolean is_translator;
	private String lang;
	private int listed_count;
	private String location;
	private String name;
	
	//notifications deprecated
	private boolean notifications;
	
	private String profile_background_color;
	private String profile_background_image_url;
	private String profile_background_image_url_https;
	private boolean profile_background_tile;
	private String profile_banner_url;
	private String profile_image_url;
	private String profile_image_url_https;
	
	private String profile_link_color;
	private String profile_sidebar_border_color;
	private String profile_sidebar_fill_color;
	private String profile_text_color;
	private boolean profile_use_background_image;
	
	private boolean protected_;
	private String screen_name;
	
	private boolean show_all_inline_media;
	
	private Tweets status;
	private int statuses_count;
	
	private String time_zone;
	private String url;
	private int utc_offset;
	private boolean verified;
	
	private String[] withheld_in_countries;
	private String withheld_scope;			
}
