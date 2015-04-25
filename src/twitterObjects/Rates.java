package twitterObjects;

import java.util.Iterator;
import java.util.regex.Pattern;

import org.json.JSONObject;

public class Rates {
	Pattern fieldNameSplitter = Pattern.compile("[/_]");
	public Rates (String jsonString)
	{
		try
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
					level2String = level2String.replaceAll(":", "").substring(1);
	
					StringBuilder fieldNameBuilder = new StringBuilder();
					for(String w : fieldNameSplitter.split(level2String))
						fieldNameBuilder.append(w.substring(0, 1).toUpperCase() + w.substring(1));
	
					String formattedFieldName = fieldNameBuilder.substring(0, 1).toLowerCase() + fieldNameBuilder.substring(1);
	
					JSONObject rateValues = level2JSON.getJSONObject(fieldName);
					BasicRateObject rateItem = new BasicRateObject(rateValues.getInt("limit"),rateValues.getInt("remaining"),rateValues.getInt("reset"));
					getClass().getField(formattedFieldName).set(this, rateItem);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}


	public static class BasicRateObject {
		public int limit; 
		public int remaining;
		public long reset;
		public BasicRateObject(int a, int b, long c)
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
		return (accountLoginVerificationEnrollment == null ? "" : "accountLoginVerificationEnrollment: " + accountLoginVerificationEnrollment.toString())+
				(accountSettings == null ? "" : "accountSettings: " + accountSettings.toString())+
				(accountUpdateProfile == null ? "" : "accountUpdateProfile: " + accountUpdateProfile.toString())+
				(accountVerifyCredentials == null ? "" : "accountVerifyCredentials: " + accountVerifyCredentials.toString())+
				(applicationRateLimitStatus == null ? "" : "applicationRateLimitStatus: " + applicationRateLimitStatus.toString())+
				(blocksIds == null ? "" : "blocksIds: " + blocksIds.toString())+
				(blocksList == null ? "" : "blocksList: " + blocksList.toString())+
				(contactsAddressbook == null ? "" : "contactsAddressbook: " + contactsAddressbook.toString())+
				(contactsDeleteStatus == null ? "" : "contactsDeleteStatus: " + contactsDeleteStatus.toString())+
				(contactsUploadedBy == null ? "" : "contactsUploadedBy: " + contactsUploadedBy.toString())+
				(contactsUsers == null ? "" : "contactsUsers: " + contactsUsers.toString())+
				(contactsUsersAndUploadedBy == null ? "" : "contactsUsersAndUploadedBy: " + contactsUsersAndUploadedBy.toString())+
				(deviceToken == null ? "" : "deviceToken: " + deviceToken.toString())+
				(directMessages == null ? "" : "directMessages: " + directMessages.toString())+
				(directMessagesSent == null ? "" : "directMessagesSent: " + directMessagesSent.toString())+
				(directMessagesSentAndReceived == null ? "" : "directMessagesSentAndReceived: " + directMessagesSentAndReceived.toString())+
				(directMessagesShow == null ? "" : "directMessagesShow: " + directMessagesShow.toString())+
				(favoritesList == null ? "" : "favoritesList: " + favoritesList.toString())+
				(followersIds == null ? "" : "followersIds: " + followersIds.toString())+
				(followersList == null ? "" : "followersList: " + followersList.toString())+
				(friendsFollowingIds == null ? "" : "friendsFollowingIds: " + friendsFollowingIds.toString())+
				(friendsFollowingList == null ? "" : "friendsFollowingList: " + friendsFollowingList.toString())+
				(friendsIds == null ? "" : "friendsIds: " + friendsIds.toString())+
				(friendsList == null ? "" : "friendsList: " + friendsList.toString())+
				(friendshipsIncoming == null ? "" : "friendshipsIncoming: " + friendshipsIncoming.toString())+
				(friendshipsLookup == null ? "" : "friendshipsLookup: " + friendshipsLookup.toString())+
				(friendshipsNoRetweetsIds == null ? "" : "friendshipsNoRetweetsIds: " + friendshipsNoRetweetsIds.toString())+
				(friendshipsOutgoing == null ? "" : "friendshipsOutgoing: " + friendshipsOutgoing.toString())+
				(friendshipsShow == null ? "" : "friendshipsShow: " + friendshipsShow.toString())+
				(geoIdPlaceId == null ? "" : "geoIdPlaceId: " + geoIdPlaceId.toString())+
				(geoReverseGeocode == null ? "" : "geoReverseGeocode: " + geoReverseGeocode.toString())+
				(geoSearch == null ? "" : "geoSearch: " + geoSearch.toString())+
				(geoSimilarPlaces == null ? "" : "geoSimilarPlaces: " + geoSimilarPlaces.toString())+
				(helpConfiguration == null ? "" : "helpConfiguration: " + helpConfiguration.toString())+
				(helpLanguages == null ? "" : "helpLanguages: " + helpLanguages.toString())+
				(helpPrivacy == null ? "" : "helpPrivacy: " + helpPrivacy.toString())+
				(helpSettings == null ? "" : "helpSettings: " + helpSettings.toString())+
				(helpTos == null ? "" : "helpTos: " + helpTos.toString())+
				(listsList == null ? "" : "listsList: " + listsList.toString())+
				(listsMembers == null ? "" : "listsMembers: " + listsMembers.toString())+
				(listsMembersShow == null ? "" : "listsMembersShow: " + listsMembersShow.toString())+
				(listsMemberships == null ? "" : "listsMemberships: " + listsMemberships.toString())+
				(listsOwnerships == null ? "" : "listsOwnerships: " + listsOwnerships.toString())+
				(listsShow == null ? "" : "listsShow: " + listsShow.toString())+
				(listsStatuses == null ? "" : "listsStatuses: " + listsStatuses.toString())+
				(listsSubscribers == null ? "" : "listsSubscribers: " + listsSubscribers.toString())+
				(listsSubscribersShow == null ? "" : "listsSubscribersShow: " + listsSubscribersShow.toString())+
				(listsSubscriptions == null ? "" : "listsSubscriptions: " + listsSubscriptions.toString())+
				(mutesUsersIds == null ? "" : "mutesUsersIds: " + mutesUsersIds.toString())+
				(mutesUsersList == null ? "" : "mutesUsersList: " + mutesUsersList.toString())+
				(savedSearchesDestroyId == null ? "" : "savedSearchesDestroyId: " + savedSearchesDestroyId.toString())+
				(savedSearchesList == null ? "" : "savedSearchesList: " + savedSearchesList.toString())+
				(savedSearchesShowId == null ? "" : "savedSearchesShowId: " + savedSearchesShowId.toString())+
				(searchTweets == null ? "" : "searchTweets: " + searchTweets.toString())+
				(statusesFriends == null ? "" : "statusesFriends: " + statusesFriends.toString())+
				(statusesHomeTimeline == null ? "" : "statusesHomeTimeline: " + statusesHomeTimeline.toString())+
				(statusesLookup == null ? "" : "statusesLookup: " + statusesLookup.toString())+
				(statusesMentionsTimeline == null ? "" : "statusesMentionsTimeline: " + statusesMentionsTimeline.toString())+
				(statusesOembed == null ? "" : "statusesOembed: " + statusesOembed.toString())+
				(statusesRetweetersIds == null ? "" : "statusesRetweetersIds: " + statusesRetweetersIds.toString())+
				(statusesRetweetsId == null ? "" : "statusesRetweetsId: " + statusesRetweetsId.toString())+
				(statusesRetweetsOfMe == null ? "" : "statusesRetweetsOfMe: " + statusesRetweetsOfMe.toString())+
				(statusesShowId == null ? "" : "statusesShowId: " + statusesShowId.toString())+
				(statusesUserTimeline == null ? "" : "statusesUserTimeline: " + statusesUserTimeline.toString())+
				(trendsAvailable == null ? "" : "trendsAvailable: " + trendsAvailable.toString())+
				(trendsClosest == null ? "" : "trendsClosest: " + trendsClosest.toString())+
				(trendsPlace == null ? "" : "trendsPlace: " + trendsPlace.toString())+
				(usersDerivedInfo == null ? "" : "usersDerivedInfo: " + usersDerivedInfo.toString())+
				(usersLookup == null ? "" : "usersLookup: " + usersLookup.toString())+
				(usersProfileBanner == null ? "" : "usersProfileBanner: " + usersProfileBanner.toString())+
				(usersReportSpam == null ? "" : "usersReportSpam: " + usersReportSpam.toString())+
				(usersSearch == null ? "" : "usersSearch: " + usersSearch.toString())+
				(usersShowId == null ? "" : "usersShowId: " + usersShowId.toString())+
				(usersSuggestions == null ? "" : "usersSuggestions: " + usersSuggestions.toString())+
				(usersSuggestionsSlug == null ? "" : "usersSuggestionsSlug: " + usersSuggestionsSlug.toString())+
				(usersSuggestionsSlugMembers == null ? "" : "usersSuggestionsSlugMembers: " + usersSuggestionsSlugMembers.toString());

	}

	public BasicRateObject accountLoginVerificationEnrollment;
	public BasicRateObject accountSettings;
	public BasicRateObject accountUpdateProfile;
	public BasicRateObject accountVerifyCredentials;
	public BasicRateObject applicationRateLimitStatus;
	public BasicRateObject blocksIds;
	public BasicRateObject blocksList;
	public BasicRateObject contactsAddressbook;
	public BasicRateObject contactsDeleteStatus;
	public BasicRateObject contactsUploadedBy;
	public BasicRateObject contactsUsers;
	public BasicRateObject contactsUsersAndUploadedBy;
	public BasicRateObject deviceToken;
	public BasicRateObject directMessages;
	public BasicRateObject directMessagesSent;
	public BasicRateObject directMessagesSentAndReceived;
	public BasicRateObject directMessagesShow;
	public BasicRateObject favoritesList;
	public BasicRateObject followersIds;
	public BasicRateObject followersList;
	public BasicRateObject friendsFollowingIds;
	public BasicRateObject friendsFollowingList;
	public BasicRateObject friendsIds;
	public BasicRateObject friendsList;
	public BasicRateObject friendshipsIncoming;
	public BasicRateObject friendshipsLookup;
	public BasicRateObject friendshipsNoRetweetsIds;
	public BasicRateObject friendshipsOutgoing;
	public BasicRateObject friendshipsShow;
	public BasicRateObject geoIdPlaceId;
	public BasicRateObject geoReverseGeocode;
	public BasicRateObject geoSearch;
	public BasicRateObject geoSimilarPlaces;
	public BasicRateObject helpConfiguration;
	public BasicRateObject helpLanguages;
	public BasicRateObject helpPrivacy;
	public BasicRateObject helpSettings;
	public BasicRateObject helpTos;
	public BasicRateObject listsList;
	public BasicRateObject listsMembers;
	public BasicRateObject listsMembersShow;
	public BasicRateObject listsMemberships;
	public BasicRateObject listsOwnerships;
	public BasicRateObject listsShow;
	public BasicRateObject listsStatuses;
	public BasicRateObject listsSubscribers;
	public BasicRateObject listsSubscribersShow;
	public BasicRateObject listsSubscriptions;
	public BasicRateObject mutesUsersIds;
	public BasicRateObject mutesUsersList;
	public BasicRateObject savedSearchesDestroyId;
	public BasicRateObject savedSearchesList;
	public BasicRateObject savedSearchesShowId;
	public BasicRateObject searchTweets;
	public BasicRateObject statusesFriends;
	public BasicRateObject statusesHomeTimeline;
	public BasicRateObject statusesLookup;
	public BasicRateObject statusesMentionsTimeline;
	public BasicRateObject statusesOembed;
	public BasicRateObject statusesRetweetersIds;
	public BasicRateObject statusesRetweetsId;
	public BasicRateObject statusesRetweetsOfMe;
	public BasicRateObject statusesShowId;
	public BasicRateObject statusesUserTimeline;
	public BasicRateObject trendsAvailable;
	public BasicRateObject trendsClosest;
	public BasicRateObject trendsPlace;
	public BasicRateObject usersDerivedInfo;
	public BasicRateObject usersLookup;
	public BasicRateObject usersProfileBanner;
	public BasicRateObject usersReportSpam;
	public BasicRateObject usersSearch;
	public BasicRateObject usersShowId;
	public BasicRateObject usersSuggestions;
	public BasicRateObject usersSuggestionsSlug;
	public BasicRateObject usersSuggestionsSlugMembers;

}


