package twitterObjects;

import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

public class Rates {
	
	public Rates (String jsonString) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		JSONObject bareJson = new JSONObject(jsonString).getJSONObject("resources");
		Iterator it = bareJson.keys();
		while(it.hasNext())
		{
			String k = (String)it.next();
			Iterator it2 = bareJson.getJSONObject(k).keys();
//			System.out.println(k);
//			Iterator it2 = bareJson.keys();
			while(it2.hasNext())
			{
				String s = (String)it2.next();
				s = s.replaceAll(":", "");
				s = s.substring(1);
				String f = "";
				for(String w : s.split("[/_]"))
					f += w.substring(0, 1).toUpperCase() + w.substring(1);
				f = f.substring(0, 1).toLowerCase() + f.substring(1);
//					System.out.print(w + " ");
				
				Class c= getClass().getField(f).getType();
//				System.out.println(c.getName());
				BasicRateObject o = (BasicRateObject) c.newInstance();
				o.setValues(1,23,4);
//				System.out.println(f);
//					f += w.substring(0, 1).toUpperCase() + w.substring(1);
//				System.out.println(f);
			}
		}
		System.out.println("00");
		System.out.println(favoritesList.limit);
	}
	
	
	public BasicRateObject favoritesList;
	public BasicRateObject blocksIds;
	public BasicRateObject blocksList;
	public BasicRateObject friendshipsNoRetweetsIds;
	public BasicRateObject friendshipsIncoming;
	public BasicRateObject friendshipsShow;
	public BasicRateObject friendshipsLookup;
	public BasicRateObject friendshipsOutgoing;
	public BasicRateObject mutesUsersList;
	public BasicRateObject mutesUsersIds;
	public BasicRateObject directMessagesShow;
	public BasicRateObject directMessages;
	public BasicRateObject directMessagesSent;
	public BasicRateObject directMessagesSentAndReceived;
	public BasicRateObject usersShowId;
	public BasicRateObject usersProfileBanner;
	public BasicRateObject usersReportSpam;
	public BasicRateObject usersLookup;
	public BasicRateObject usersSearch;
	public BasicRateObject usersSuggestionsSlug;
	public BasicRateObject usersSuggestionsSlugMembers;
	public BasicRateObject usersDerivedInfo;
	public BasicRateObject usersSuggestions;
	public BasicRateObject friendsFollowingIds;
	public BasicRateObject friendsList;
	public BasicRateObject friendsIds;
	public BasicRateObject friendsFollowingList;
	public BasicRateObject geoIdPlaceId;
	public BasicRateObject geoSimilarPlaces;
	public BasicRateObject geoReverseGeocode;
	public BasicRateObject geoSearch;
	public BasicRateObject helpPrivacy;
	public BasicRateObject helpLanguages;
	public BasicRateObject helpConfiguration;
	public BasicRateObject helpTos;
	public BasicRateObject helpSettings;
	public BasicRateObject searchTweets;
	public BasicRateObject followersIds;
	public BasicRateObject followersList;
	public BasicRateObject applicationRateLimitStatus;
	public BasicRateObject listsMembersShow;
	public BasicRateObject listsMembers;
	public BasicRateObject listsOwnerships;
	public BasicRateObject listsStatuses;
	public BasicRateObject listsMemberships;
	public BasicRateObject listsShow;
	public BasicRateObject listsSubscribersShow;
	public BasicRateObject listsList;
	public BasicRateObject listsSubscriptions;
	public BasicRateObject listsSubscribers;
	public BasicRateObject statusesFriends;
	public BasicRateObject statusesShowId;
	public BasicRateObject statusesUserTimeline;
	public BasicRateObject statusesMentionsTimeline;
	public BasicRateObject statusesRetweetsId;
	public BasicRateObject statusesRetweetersIds;
	public BasicRateObject statusesHomeTimeline;
	public BasicRateObject statusesOembed;
	public BasicRateObject statusesLookup;
	public BasicRateObject statusesRetweetsOfMe;
	public BasicRateObject deviceToken;
	public BasicRateObject contactsUsers;
	public BasicRateObject contactsDeleteStatus;
	public BasicRateObject contactsAddressbook;
	public BasicRateObject contactsUploadedBy;
	public BasicRateObject contactsUsersAndUploadedBy;
	public BasicRateObject accountUpdateProfile;
	public BasicRateObject accountVerifyCredentials;
	public BasicRateObject accountSettings;
	public BasicRateObject accountLoginVerificationEnrollment;
	public BasicRateObject savedSearchesShowId;
	public BasicRateObject savedSearchesDestroyId;
	public BasicRateObject savedSearchesList;
	public BasicRateObject trendsPlace;
	public BasicRateObject trendsAvailable;
	public BasicRateObject trendsClosest;

}


