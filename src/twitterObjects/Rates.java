package twitterObjects;

import java.util.Iterator;
import java.util.regex.Pattern;

import org.json.JSONObject;

public class Rates {
	static Pattern fieldNameSplitter = Pattern.compile("[/_]");
	public Rates (String jsonString) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		JSONObject outerJSON = new JSONObject(jsonString).getJSONObject("resources");
		Iterator<?> outerKeysIterator = outerJSON.keys();
		
		while(outerKeysIterator.hasNext())
		{
			String level1String = (String)outerKeysIterator.next();
			JSONObject level2JSON = outerJSON.getJSONObject(level1String);
			Iterator<?> level2Iterator = level2JSON.keys();

			while(level2Iterator.hasNext())
			{
				String level2String = (String)level2Iterator.next();
				String fieldName = level2String;

				level2String = level2String.replaceAll(":", "");
				level2String = level2String.substring(1);

				String formattedFieldName = "";
				for(String w : fieldNameSplitter.split(level2String))
					formattedFieldName += w.substring(0, 1).toUpperCase() + w.substring(1);
				formattedFieldName = formattedFieldName.substring(0, 1).toLowerCase() + formattedFieldName.substring(1);

				Class<?> c= getClass().getField(formattedFieldName).getType();
				BasicRateObject rateItem = (BasicRateObject) c.newInstance();
				getClass().getField(formattedFieldName).set(this, rateItem);
				JSONObject rateValues = level2JSON.getJSONObject(fieldName);

				rateItem.setValues(rateValues.getInt("limit"),rateValues.getInt("remaining"),rateValues.getInt("reset"));
			}
		}

	}


	public static class BasicRateObject {
		public int limit; 
		public int remaining;
		public long reset;
		public BasicRateObject()
		{

		}

		public void setValues(int a, int b, long c)
		{
			limit = a;
			remaining = b;
			reset = c;
		}

		@Override
		public String toString()
		{
			return "Limit=" + limit + ", Remaining=" + remaining + ", Reset=" + reset + "\n";
		}

	}

	@Override
	public String toString()
	{
		return (favoritesList == null ? "" : "favoritesList: " + favoritesList.toString())+
				(blocksIds == null ? "" : "blocksIds: " + blocksIds.toString())+
				(blocksList == null ? "" : "blocksList: " + blocksList.toString())+
				(friendshipsNoRetweetsIds == null ? "" : "friendshipsNoRetweetsIds: " + friendshipsNoRetweetsIds.toString())+
				(friendshipsIncoming == null ? "" : "friendshipsIncoming: " + friendshipsIncoming.toString())+
				(friendshipsShow == null ? "" : "friendshipsShow: " + friendshipsShow.toString())+
				(friendshipsLookup == null ? "" : "friendshipsLookup: " + friendshipsLookup.toString())+
				(friendshipsOutgoing == null ? "" : "friendshipsOutgoing: " + friendshipsOutgoing.toString())+
				(mutesUsersList == null ? "" : "mutesUsersList: " + mutesUsersList.toString())+
				(mutesUsersIds == null ? "" : "mutesUsersIds: " + mutesUsersIds.toString())+
				(directMessagesShow == null ? "" : "directMessagesShow: " + directMessagesShow.toString())+
				(directMessages == null ? "" : "directMessages: " + directMessages.toString())+
				(directMessagesSent == null ? "" : "directMessagesSent: " + directMessagesSent.toString())+
				(directMessagesSentAndReceived == null ? "" : "directMessagesSentAndReceived: " + directMessagesSentAndReceived.toString())+
				(usersShowId == null ? "" : "usersShowId: " + usersShowId.toString())+
				(usersProfileBanner == null ? "" : "usersProfileBanner: " + usersProfileBanner.toString())+
				(usersReportSpam == null ? "" : "usersReportSpam: " + usersReportSpam.toString())+
				(usersLookup == null ? "" : "usersLookup: " + usersLookup.toString())+
				(usersSearch == null ? "" : "usersSearch: " + usersSearch.toString())+
				(usersSuggestionsSlug == null ? "" : "usersSuggestionsSlug: " + usersSuggestionsSlug.toString())+
				(usersSuggestionsSlugMembers == null ? "" : "usersSuggestionsSlugMembers: " + usersSuggestionsSlugMembers.toString())+
				(usersDerivedInfo == null ? "" : "usersDerivedInfo: " + usersDerivedInfo.toString())+
				(usersSuggestions == null ? "" : "usersSuggestions: " + usersSuggestions.toString())+
				(friendsFollowingIds == null ? "" : "friendsFollowingIds: " + friendsFollowingIds.toString())+
				(friendsList == null ? "" : "friendsList: " + friendsList.toString())+
				(friendsIds == null ? "" : "friendsIds: " + friendsIds.toString())+
				(friendsFollowingList == null ? "" : "friendsFollowingList: " + friendsFollowingList.toString())+
				(geoIdPlaceId == null ? "" : "geoIdPlaceId: " + geoIdPlaceId.toString())+
				(geoSimilarPlaces == null ? "" : "geoSimilarPlaces: " + geoSimilarPlaces.toString())+
				(geoReverseGeocode == null ? "" : "geoReverseGeocode: " + geoReverseGeocode.toString())+
				(geoSearch == null ? "" : "geoSearch: " + geoSearch.toString())+
				(helpPrivacy == null ? "" : "helpPrivacy: " + helpPrivacy.toString())+
				(helpLanguages == null ? "" : "helpLanguages: " + helpLanguages.toString())+
				(helpConfiguration == null ? "" : "helpConfiguration: " + helpConfiguration.toString())+
				(helpTos == null ? "" : "helpTos: " + helpTos.toString())+
				(helpSettings == null ? "" : "helpSettings: " + helpSettings.toString())+
				(searchTweets == null ? "" : "searchTweets: " + searchTweets.toString())+
				(followersIds == null ? "" : "followersIds: " + followersIds.toString())+
				(followersList == null ? "" : "followersList: " + followersList.toString())+
				(applicationRateLimitStatus == null ? "" : "applicationRateLimitStatus: " + applicationRateLimitStatus.toString())+
				(listsMembersShow == null ? "" : "listsMembersShow: " + listsMembersShow.toString())+
				(listsMembers == null ? "" : "listsMembers: " + listsMembers.toString())+
				(listsOwnerships == null ? "" : "listsOwnerships: " + listsOwnerships.toString())+
				(listsStatuses == null ? "" : "listsStatuses: " + listsStatuses.toString())+
				(listsMemberships == null ? "" : "listsMemberships: " + listsMemberships.toString())+
				(listsShow == null ? "" : "listsShow: " + listsShow.toString())+
				(listsSubscribersShow == null ? "" : "listsSubscribersShow: " + listsSubscribersShow.toString())+
				(listsList == null ? "" : "listsList: " + listsList.toString())+
				(listsSubscriptions == null ? "" : "listsSubscriptions: " + listsSubscriptions.toString())+
				(listsSubscribers == null ? "" : "listsSubscribers: " + listsSubscribers.toString())+
				(statusesFriends == null ? "" : "statusesFriends: " + statusesFriends.toString())+
				(statusesShowId == null ? "" : "statusesShowId: " + statusesShowId.toString())+
				(statusesUserTimeline == null ? "" : "statusesUserTimeline: " + statusesUserTimeline.toString())+
				(statusesMentionsTimeline == null ? "" : "statusesMentionsTimeline: " + statusesMentionsTimeline.toString())+
				(statusesRetweetsId == null ? "" : "statusesRetweetsId: " + statusesRetweetsId.toString())+
				(statusesRetweetersIds == null ? "" : "statusesRetweetersIds: " + statusesRetweetersIds.toString())+
				(statusesHomeTimeline == null ? "" : "statusesHomeTimeline: " + statusesHomeTimeline.toString())+
				(statusesOembed == null ? "" : "statusesOembed: " + statusesOembed.toString())+
				(statusesLookup == null ? "" : "statusesLookup: " + statusesLookup.toString())+
				(statusesRetweetsOfMe == null ? "" : "statusesRetweetsOfMe: " + statusesRetweetsOfMe.toString())+
				(deviceToken == null ? "" : "deviceToken: " + deviceToken.toString())+
				(contactsUsers == null ? "" : "contactsUsers: " + contactsUsers.toString())+
				(contactsDeleteStatus == null ? "" : "contactsDeleteStatus: " + contactsDeleteStatus.toString())+
				(contactsAddressbook == null ? "" : "contactsAddressbook: " + contactsAddressbook.toString())+
				(contactsUploadedBy == null ? "" : "contactsUploadedBy: " + contactsUploadedBy.toString())+
				(contactsUsersAndUploadedBy == null ? "" : "contactsUsersAndUploadedBy: " + contactsUsersAndUploadedBy.toString())+
				(accountUpdateProfile == null ? "" : "accountUpdateProfile: " + accountUpdateProfile.toString())+
				(accountVerifyCredentials == null ? "" : "accountVerifyCredentials: " + accountVerifyCredentials.toString())+
				(accountSettings == null ? "" : "accountSettings: " + accountSettings.toString())+
				(accountLoginVerificationEnrollment == null ? "" : "accountLoginVerificationEnrollment: " + accountLoginVerificationEnrollment.toString())+
				(savedSearchesShowId == null ? "" : "savedSearchesShowId: " + savedSearchesShowId.toString())+
				(savedSearchesDestroyId == null ? "" : "savedSearchesDestroyId: " + savedSearchesDestroyId.toString())+
				(savedSearchesList == null ? "" : "savedSearchesList: " + savedSearchesList.toString())+
				(trendsPlace == null ? "" : "trendsPlace: " + trendsPlace.toString())+
				(trendsAvailable == null ? "" : "trendsAvailable: " + trendsAvailable.toString())+
				(trendsClosest == null ? "" : "trendsClosest: " + trendsClosest.toString());
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


