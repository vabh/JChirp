package twitterObjects;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.json.JSONObject;

public class Tweets {
	
	public Tweets annotations;
	public Users[] contributors;
	//array of length 3, 0,1 -> coordinates, 2->type
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
	
	
	public Tweets(String jsonString)
	{
		JSONObject json = new JSONObject(jsonString);
		//fully qualified name required!
		Class<?> c = getClass();

		Field []fields = c.getFields();

		for (Field field : fields) {
			Class<?> type = field.getType();

			String fieldType = type.getSimpleName();
			String fieldName = field.getName();				

			if(json.has(fieldName) && !json.isNull(fieldName)){

				if (type.isPrimitive() || type == String.class){

					try{
						if (fieldType.equals("int")) {							
							field.set(this, json.getInt(fieldName));						
						}
						else if (fieldType.equals("long")) {								
							field.set(this, json.getLong(fieldName));

						}else if (fieldType.equals("boolean")) {					    	
							field.set(this, json.getBoolean(fieldName));
						}
						else if (type == String.class) {				    	
							field.set(this, json.getString(fieldName));
						}
					}
					//exception is thrown when the field is "nullable"
					catch(org.json.JSONException|IllegalAccessException e){
						//						e.printStackTrace();
					}
				}
				else if(type == Users.class){
					try{						
						Users u = new Users(json.get(fieldName).toString());						
						field.set(this, u);
					}
					catch(Exception e){
						//						e.printStackTrace();
					}				
				}
				//for retweeted_status object
				else if(type == Tweets.class){
					try{						
						Tweets t = new Tweets(json.get(fieldName).toString());
						field.set(this, t);
					}
					catch(Exception e){
						//						e.printStackTrace();
					}
				}
				//looks hacky, can be bettered but exams :/
				else if(fieldName.equals("coordinates")){
					try{
						JSONObject tjson = json.getJSONObject(fieldName);
						String coordsStr = tjson.get(fieldName).toString();
						String typeCoords = tjson.getString("type");

						String coords[] = (coordsStr + ", " + typeCoords).split(",");						
						coords[0] = coords[0].substring(coords[0].indexOf('[') + 1).trim();
						coords[1] = coords[1].substring(0, coords[1].indexOf(']')).trim();
						coords[2] = coords[2].trim();

						field.set(this, coords);
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			}			
		}
	}
	
	
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


/*
 * the following field:value pairs are not being stored in the Tweets object: (why?)
  		possibly_sensitive_appealable: false
		extended_entities: {"media":[{"display_url":"pic.twitter.com/pghiqLbSWZ","indices":[47,69],"sizes":{"small":{"w":340,"h":255,"resize":"fit"},"large":{"w":1024,"h":768,"resize":"fit"},"thumb":{"w":150,"h":150,"resize":"crop"},"medium":{"w":600,"h":450,"resize":"fit"}},"id_str":"576895486780551168","expanded_url":"http://twitter.com/vanessajaaane/status/576895494066065408/photo/1","media_url_https":"https://pbs.twimg.com/media/CAGLY4IUsAAQLFU.jpg","id":576895486780551168,"type":"photo","media_url":"http://pbs.twimg.com/media/CAGLY4IUsAAQLFU.jpg","url":"http://t.co/pghiqLbSWZ"}]}
		geo: {"coordinates":[37.96161415,-121.73181952],"type":"Point"}
 * 
 * */
