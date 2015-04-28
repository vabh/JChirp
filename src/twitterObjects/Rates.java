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
		StringBuilder s = new StringBuilder();

		s.append((accountLoginVerificationEnrollment == null ? "" : new StringBuilder("accountLoginVerificationEnrollment: " ).append( accountLoginVerificationEnrollment.toString())));
		s.append((accountSettings == null ? "" : new StringBuilder("accountSettings: " ).append( accountSettings.toString())));
		s.append((accountUpdateProfile == null ? "" : new StringBuilder("accountUpdateProfile: " ).append( accountUpdateProfile.toString())));
		s.append((accountVerifyCredentials == null ? "" : new StringBuilder("accountVerifyCredentials: " ).append( accountVerifyCredentials.toString())));
		s.append((applicationRateLimitStatus == null ? "" : new StringBuilder("applicationRateLimitStatus: " ).append( applicationRateLimitStatus.toString())));
		s.append((blocksIds == null ? "" : new StringBuilder("blocksIds: " ).append( blocksIds.toString())));
		s.append((blocksList == null ? "" : new StringBuilder("blocksList: " ).append( blocksList.toString())));
		s.append((contactsAddressbook == null ? "" : new StringBuilder("contactsAddressbook: " ).append( contactsAddressbook.toString())));
		s.append((contactsDeleteStatus == null ? "" : new StringBuilder("contactsDeleteStatus: " ).append( contactsDeleteStatus.toString())));
		s.append((contactsUploadedBy == null ? "" : new StringBuilder("contactsUploadedBy: " ).append( contactsUploadedBy.toString())));
		s.append((contactsUsers == null ? "" : new StringBuilder("contactsUsers: " ).append( contactsUsers.toString())));
		s.append((contactsUsersAndUploadedBy == null ? "" : new StringBuilder("contactsUsersAndUploadedBy: " ).append( contactsUsersAndUploadedBy.toString())));
		s.append((deviceToken == null ? "" : new StringBuilder("deviceToken: " ).append( deviceToken.toString())));
		s.append((directMessages == null ? "" : new StringBuilder("directMessages: " ).append( directMessages.toString())));
		s.append((directMessagesSent == null ? "" : new StringBuilder("directMessagesSent: " ).append( directMessagesSent.toString())));
		s.append((directMessagesSentAndReceived == null ? "" : new StringBuilder("directMessagesSentAndReceived: " ).append( directMessagesSentAndReceived.toString())));
		s.append((directMessagesShow == null ? "" : new StringBuilder("directMessagesShow: " ).append( directMessagesShow.toString())));
		s.append((favoritesList == null ? "" : new StringBuilder("favoritesList: " ).append( favoritesList.toString())));
		s.append((followersIds == null ? "" : new StringBuilder("followersIds: " ).append( followersIds.toString())));
		s.append((followersList == null ? "" : new StringBuilder("followersList: " ).append( followersList.toString())));
		s.append((friendsFollowingIds == null ? "" : new StringBuilder("friendsFollowingIds: " ).append( friendsFollowingIds.toString())));
		s.append((friendsFollowingList == null ? "" : new StringBuilder("friendsFollowingList: " ).append( friendsFollowingList.toString())));
		s.append((friendsIds == null ? "" : new StringBuilder("friendsIds: " ).append( friendsIds.toString())));
		s.append((friendsList == null ? "" : new StringBuilder("friendsList: " ).append( friendsList.toString())));
		s.append((friendshipsIncoming == null ? "" : new StringBuilder("friendshipsIncoming: " ).append( friendshipsIncoming.toString())));
		s.append((friendshipsLookup == null ? "" : new StringBuilder("friendshipsLookup: " ).append( friendshipsLookup.toString())));
		s.append((friendshipsNoRetweetsIds == null ? "" : new StringBuilder("friendshipsNoRetweetsIds: " ).append( friendshipsNoRetweetsIds.toString())));
		s.append((friendshipsOutgoing == null ? "" : new StringBuilder("friendshipsOutgoing: " ).append( friendshipsOutgoing.toString())));
		s.append((friendshipsShow == null ? "" : new StringBuilder("friendshipsShow: " ).append( friendshipsShow.toString())));
		s.append((geoIdPlaceId == null ? "" : new StringBuilder("geoIdPlaceId: " ).append( geoIdPlaceId.toString())));
		s.append((geoReverseGeocode == null ? "" : new StringBuilder("geoReverseGeocode: " ).append( geoReverseGeocode.toString())));
		s.append((geoSearch == null ? "" : new StringBuilder("geoSearch: " ).append( geoSearch.toString())));
		s.append((geoSimilarPlaces == null ? "" : new StringBuilder("geoSimilarPlaces: " ).append( geoSimilarPlaces.toString())));
		s.append((helpConfiguration == null ? "" : new StringBuilder("helpConfiguration: " ).append( helpConfiguration.toString())));
		s.append((helpLanguages == null ? "" : new StringBuilder("helpLanguages: " ).append( helpLanguages.toString())));
		s.append((helpPrivacy == null ? "" : new StringBuilder("helpPrivacy: " ).append( helpPrivacy.toString())));
		s.append((helpSettings == null ? "" : new StringBuilder("helpSettings: " ).append( helpSettings.toString())));
		s.append((helpTos == null ? "" : new StringBuilder("helpTos: " ).append( helpTos.toString())));
		s.append((listsList == null ? "" : new StringBuilder("listsList: " ).append( listsList.toString())));
		s.append((listsMembers == null ? "" : new StringBuilder("listsMembers: " ).append( listsMembers.toString())));
		s.append((listsMembersShow == null ? "" : new StringBuilder("listsMembersShow: " ).append( listsMembersShow.toString())));
		s.append((listsMemberships == null ? "" : new StringBuilder("listsMemberships: " ).append( listsMemberships.toString())));
		s.append((listsOwnerships == null ? "" : new StringBuilder("listsOwnerships: " ).append( listsOwnerships.toString())));
		s.append((listsShow == null ? "" : new StringBuilder("listsShow: " ).append( listsShow.toString())));
		s.append((listsStatuses == null ? "" : new StringBuilder("listsStatuses: " ).append( listsStatuses.toString())));
		s.append((listsSubscribers == null ? "" : new StringBuilder("listsSubscribers: " ).append( listsSubscribers.toString())));
		s.append((listsSubscribersShow == null ? "" : new StringBuilder("listsSubscribersShow: " ).append( listsSubscribersShow.toString())));
		s.append((listsSubscriptions == null ? "" : new StringBuilder("listsSubscriptions: " ).append( listsSubscriptions.toString())));
		s.append((mutesUsersIds == null ? "" : new StringBuilder("mutesUsersIds: " ).append( mutesUsersIds.toString())));
		s.append((mutesUsersList == null ? "" : new StringBuilder("mutesUsersList: " ).append( mutesUsersList.toString())));
		s.append((savedSearchesDestroyId == null ? "" : new StringBuilder("savedSearchesDestroyId: " ).append( savedSearchesDestroyId.toString())));
		s.append((savedSearchesList == null ? "" : new StringBuilder("savedSearchesList: " ).append( savedSearchesList.toString())));
		s.append((savedSearchesShowId == null ? "" : new StringBuilder("savedSearchesShowId: " ).append( savedSearchesShowId.toString())));
		s.append((searchTweets == null ? "" : new StringBuilder("searchTweets: " ).append( searchTweets.toString())));
		s.append((statusesFriends == null ? "" : new StringBuilder("statusesFriends: " ).append( statusesFriends.toString())));
		s.append((statusesHomeTimeline == null ? "" : new StringBuilder("statusesHomeTimeline: " ).append( statusesHomeTimeline.toString())));
		s.append((statusesLookup == null ? "" : new StringBuilder("statusesLookup: " ).append( statusesLookup.toString())));
		s.append((statusesMentionsTimeline == null ? "" : new StringBuilder("statusesMentionsTimeline: " ).append( statusesMentionsTimeline.toString())));
		s.append((statusesOembed == null ? "" : new StringBuilder("statusesOembed: " ).append( statusesOembed.toString())));
		s.append((statusesRetweetersIds == null ? "" : new StringBuilder("statusesRetweetersIds: " ).append( statusesRetweetersIds.toString())));
		s.append((statusesRetweetsId == null ? "" : new StringBuilder("statusesRetweetsId: " ).append( statusesRetweetsId.toString())));
		s.append((statusesRetweetsOfMe == null ? "" : new StringBuilder("statusesRetweetsOfMe: " ).append( statusesRetweetsOfMe.toString())));
		s.append((statusesShowId == null ? "" : new StringBuilder("statusesShowId: " ).append( statusesShowId.toString())));
		s.append((statusesUserTimeline == null ? "" : new StringBuilder("statusesUserTimeline: " ).append( statusesUserTimeline.toString())));
		s.append((trendsAvailable == null ? "" : new StringBuilder("trendsAvailable: " ).append( trendsAvailable.toString())));
		s.append((trendsClosest == null ? "" : new StringBuilder("trendsClosest: " ).append( trendsClosest.toString())));
		s.append((trendsPlace == null ? "" : new StringBuilder("trendsPlace: " ).append( trendsPlace.toString())));
		s.append((usersDerivedInfo == null ? "" : new StringBuilder("usersDerivedInfo: " ).append( usersDerivedInfo.toString())));
		s.append((usersLookup == null ? "" : new StringBuilder("usersLookup: " ).append( usersLookup.toString())));
		s.append((usersProfileBanner == null ? "" : new StringBuilder("usersProfileBanner: " ).append( usersProfileBanner.toString())));
		s.append((usersReportSpam == null ? "" : new StringBuilder("usersReportSpam: " ).append( usersReportSpam.toString())));
		s.append((usersSearch == null ? "" : new StringBuilder("usersSearch: " ).append( usersSearch.toString())));
		s.append((usersShowId == null ? "" : new StringBuilder("usersShowId: " ).append( usersShowId.toString())));
		s.append((usersSuggestions == null ? "" : new StringBuilder("usersSuggestions: " ).append( usersSuggestions.toString())));
		s.append((usersSuggestionsSlug == null ? "" : new StringBuilder("usersSuggestionsSlug: " ).append( usersSuggestionsSlug.toString())));
		s.append((usersSuggestionsSlugMembers == null ? "" : new StringBuilder("usersSuggestionsSlugMembers: " ).append( usersSuggestionsSlugMembers.toString())));
		
		return s.toString();
		/*
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
				*/

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


