package twitterObjects;

import java.util.Arrays;

public class Users {

	public boolean contributors_enabled;
	public String created_at;
	
	public boolean default_profile;
	public boolean default_profile_image;
	
	public String description;
	public Entities entities;
	public int favourites_count;
	
	public /*Type?*/ boolean follow_request_sent;
	public /*Type?*/boolean following;
	public int followers_count;
	public int friends_count;
	
	public boolean geo_enabled;
	public long id;
	public String id_str;
	public boolean is_translator;
	public String lang;
	public int listed_count;
	public String location;
	public String name;
	
	//notifications deprecated
	public boolean notifications;
	
	public String profile_background_color;
	public String profile_background_image_url;
	public String profile_background_image_url_https;
	public boolean profile_background_tile;
	public String profile_banner_url;
	public String profile_image_url;
	public String profile_image_url_https;
	
	public String profile_link_color;
	public String profile_sidebar_border_color;
	public String profile_sidebar_fill_color;
	public String profile_text_color;
	public boolean profile_use_background_image;
	
	public boolean protected_;
	public String screen_name;
	
	public boolean show_all_inline_media;
	
	public Tweets status;
	public int statuses_count;
	
	public String time_zone;
	public String url;
	public int utc_offset;
	public boolean verified;
	
	public String[] withheld_in_countries;
	public String withheld_scope;
	@Override
	public String toString() {
		return "Users [contributors_enabled=" + contributors_enabled
				+ ", created_at=" + created_at + ", default_profile="
				+ default_profile + ", default_profile_image="
				+ default_profile_image + ", description=" + description
				+ ", entities=" + entities + ", favourites_count="
				+ favourites_count + ", follow_request_sent="
				+ follow_request_sent + ", following=" + following
				+ ", followers_count=" + followers_count + ", friends_count="
				+ friends_count + ", geo_enabled=" + geo_enabled + ", id=" + id
				+ ", id_str=" + id_str + ", is_translator=" + is_translator
				+ ", lang=" + lang + ", listed_count=" + listed_count
				+ ", location=" + location + ", name=" + name
				+ ", notifications=" + notifications
				+ ", profile_background_color=" + profile_background_color
				+ ", profile_background_image_url="
				+ profile_background_image_url
				+ ", profile_background_image_url_https="
				+ profile_background_image_url_https
				+ ", profile_background_tile=" + profile_background_tile
				+ ", profile_banner_url=" + profile_banner_url
				+ ", profile_image_url=" + profile_image_url
				+ ", profile_image_url_https=" + profile_image_url_https
				+ ", profile_link_color=" + profile_link_color
				+ ", profile_sidebar_border_color="
				+ profile_sidebar_border_color
				+ ", profile_sidebar_fill_color=" + profile_sidebar_fill_color
				+ ", profile_text_color=" + profile_text_color
				+ ", profile_use_background_image="
				+ profile_use_background_image + ", protected_=" + protected_
				+ ", screen_name=" + screen_name + ", show_all_inline_media="
				+ show_all_inline_media + ", status=" + status
				+ ", statuses_count=" + statuses_count + ", time_zone="
				+ time_zone + ", url=" + url + ", utc_offset=" + utc_offset
				+ ", verified=" + verified + ", withheld_in_countries="
				+ Arrays.toString(withheld_in_countries) + ", withheld_scope="
				+ withheld_scope + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}	
}
