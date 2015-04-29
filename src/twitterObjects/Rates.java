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
			StringBuilder s = new StringBuilder("Limit=");
			s.append(limit);
			s.append(", Remaining=");
			s.append(", Reset=");
			s.append(reset);
			s.append("\n");
			
			return s.toString();
		}
		
		public StringBuilder toStringBuilder()
		{
			StringBuilder s = new StringBuilder("Limit=");
			s.append(limit);
			s.append(", Remaining=");
			s.append(", Reset=");
			s.append(reset);
			s.append("\n");
			
			return s;
		}

	}

	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		
		if(accountLoginVerificationEnrollment != null){s.append("accountLoginVerificationEnrollment: " ); s.append( accountLoginVerificationEnrollment.toStringBuilder());}
		if(accountSettings != null){s.append("accountSettings: " ); s.append( accountSettings.toStringBuilder());}
		if(accountUpdateProfile != null){s.append("accountUpdateProfile: " ); s.append( accountUpdateProfile.toStringBuilder());}
		if(accountVerifyCredentials != null){s.append("accountVerifyCredentials: " ); s.append( accountVerifyCredentials.toStringBuilder());}
		if(applicationRateLimitStatus != null){s.append("applicationRateLimitStatus: " ); s.append( applicationRateLimitStatus.toStringBuilder());}
		if(blocksIds != null){s.append("blocksIds: " ); s.append( blocksIds.toStringBuilder());}
		if(blocksList != null){s.append("blocksList: " ); s.append( blocksList.toStringBuilder());}
		if(contactsAddressbook != null){s.append("contactsAddressbook: " ); s.append( contactsAddressbook.toStringBuilder());}
		if(contactsDeleteStatus != null){s.append("contactsDeleteStatus: " ); s.append( contactsDeleteStatus.toStringBuilder());}
		if(contactsUploadedBy != null){s.append("contactsUploadedBy: " ); s.append( contactsUploadedBy.toStringBuilder());}
		if(contactsUsers != null){s.append("contactsUsers: " ); s.append( contactsUsers.toStringBuilder());}
		if(contactsUsersAndUploadedBy != null){s.append("contactsUsersAndUploadedBy: " ); s.append( contactsUsersAndUploadedBy.toStringBuilder());}
		if(deviceToken != null){s.append("deviceToken: " ); s.append( deviceToken.toStringBuilder());}
		if(directMessages != null){s.append("directMessages: " ); s.append( directMessages.toStringBuilder());}
		if(directMessagesSent != null){s.append("directMessagesSent: " ); s.append( directMessagesSent.toStringBuilder());}
		if(directMessagesSentAndReceived != null){s.append("directMessagesSentAndReceived: " ); s.append( directMessagesSentAndReceived.toStringBuilder());}
		if(directMessagesShow != null){s.append("directMessagesShow: " ); s.append( directMessagesShow.toStringBuilder());}
		if(favoritesList != null){s.append("favoritesList: " ); s.append( favoritesList.toStringBuilder());}
		if(followersIds != null){s.append("followersIds: " ); s.append( followersIds.toStringBuilder());}
		if(followersList != null){s.append("followersList: " ); s.append( followersList.toStringBuilder());}
		if(friendsFollowingIds != null){s.append("friendsFollowingIds: " ); s.append( friendsFollowingIds.toStringBuilder());}
		if(friendsFollowingList != null){s.append("friendsFollowingList: " ); s.append( friendsFollowingList.toStringBuilder());}
		if(friendsIds != null){s.append("friendsIds: " ); s.append( friendsIds.toStringBuilder());}
		if(friendsList != null){s.append("friendsList: " ); s.append( friendsList.toStringBuilder());}
		if(friendshipsIncoming != null){s.append("friendshipsIncoming: " ); s.append( friendshipsIncoming.toStringBuilder());}
		if(friendshipsLookup != null){s.append("friendshipsLookup: " ); s.append( friendshipsLookup.toStringBuilder());}
		if(friendshipsNoRetweetsIds != null){s.append("friendshipsNoRetweetsIds: " ); s.append( friendshipsNoRetweetsIds.toStringBuilder());}
		if(friendshipsOutgoing != null){s.append("friendshipsOutgoing: " ); s.append( friendshipsOutgoing.toStringBuilder());}
		if(friendshipsShow != null){s.append("friendshipsShow: " ); s.append( friendshipsShow.toStringBuilder());}
		if(geoIdPlaceId != null){s.append("geoIdPlaceId: " ); s.append( geoIdPlaceId.toStringBuilder());}
		if(geoReverseGeocode != null){s.append("geoReverseGeocode: " ); s.append( geoReverseGeocode.toStringBuilder());}
		if(geoSearch != null){s.append("geoSearch: " ); s.append( geoSearch.toStringBuilder());}
		if(geoSimilarPlaces != null){s.append("geoSimilarPlaces: " ); s.append( geoSimilarPlaces.toStringBuilder());}
		if(helpConfiguration != null){s.append("helpConfiguration: " ); s.append( helpConfiguration.toStringBuilder());}
		if(helpLanguages != null){s.append("helpLanguages: " ); s.append( helpLanguages.toStringBuilder());}
		if(helpPrivacy != null){s.append("helpPrivacy: " ); s.append( helpPrivacy.toStringBuilder());}
		if(helpSettings != null){s.append("helpSettings: " ); s.append( helpSettings.toStringBuilder());}
		if(helpTos != null){s.append("helpTos: " ); s.append( helpTos.toStringBuilder());}
		if(listsList != null){s.append("listsList: " ); s.append( listsList.toStringBuilder());}
		if(listsMembers != null){s.append("listsMembers: " ); s.append( listsMembers.toStringBuilder());}
		if(listsMembersShow != null){s.append("listsMembersShow: " ); s.append( listsMembersShow.toStringBuilder());}
		if(listsMemberships != null){s.append("listsMemberships: " ); s.append( listsMemberships.toStringBuilder());}
		if(listsOwnerships != null){s.append("listsOwnerships: " ); s.append( listsOwnerships.toStringBuilder());}
		if(listsShow != null){s.append("listsShow: " ); s.append( listsShow.toStringBuilder());}
		if(listsStatuses != null){s.append("listsStatuses: " ); s.append( listsStatuses.toStringBuilder());}
		if(listsSubscribers != null){s.append("listsSubscribers: " ); s.append( listsSubscribers.toStringBuilder());}
		if(listsSubscribersShow != null){s.append("listsSubscribersShow: " ); s.append( listsSubscribersShow.toStringBuilder());}
		if(listsSubscriptions != null){s.append("listsSubscriptions: " ); s.append( listsSubscriptions.toStringBuilder());}
		if(mutesUsersIds != null){s.append("mutesUsersIds: " ); s.append( mutesUsersIds.toStringBuilder());}
		if(mutesUsersList != null){s.append("mutesUsersList: " ); s.append( mutesUsersList.toStringBuilder());}
		if(savedSearchesDestroyId != null){s.append("savedSearchesDestroyId: " ); s.append( savedSearchesDestroyId.toStringBuilder());}
		if(savedSearchesList != null){s.append("savedSearchesList: " ); s.append( savedSearchesList.toStringBuilder());}
		if(savedSearchesShowId != null){s.append("savedSearchesShowId: " ); s.append( savedSearchesShowId.toStringBuilder());}
		if(searchTweets != null){s.append("searchTweets: " ); s.append( searchTweets.toStringBuilder());}
		if(statusesFriends != null){s.append("statusesFriends: " ); s.append( statusesFriends.toStringBuilder());}
		if(statusesHomeTimeline != null){s.append("statusesHomeTimeline: " ); s.append( statusesHomeTimeline.toStringBuilder());}
		if(statusesLookup != null){s.append("statusesLookup: " ); s.append( statusesLookup.toStringBuilder());}
		if(statusesMentionsTimeline != null){s.append("statusesMentionsTimeline: " ); s.append( statusesMentionsTimeline.toStringBuilder());}
		if(statusesOembed != null){s.append("statusesOembed: " ); s.append( statusesOembed.toStringBuilder());}
		if(statusesRetweetersIds != null){s.append("statusesRetweetersIds: " ); s.append( statusesRetweetersIds.toStringBuilder());}
		if(statusesRetweetsId != null){s.append("statusesRetweetsId: " ); s.append( statusesRetweetsId.toStringBuilder());}
		if(statusesRetweetsOfMe != null){s.append("statusesRetweetsOfMe: " ); s.append( statusesRetweetsOfMe.toStringBuilder());}
		if(statusesShowId != null){s.append("statusesShowId: " ); s.append( statusesShowId.toStringBuilder());}
		if(statusesUserTimeline != null){s.append("statusesUserTimeline: " ); s.append( statusesUserTimeline.toStringBuilder());}
		if(trendsAvailable != null){s.append("trendsAvailable: " ); s.append( trendsAvailable.toStringBuilder());}
		if(trendsClosest != null){s.append("trendsClosest: " ); s.append( trendsClosest.toStringBuilder());}
		if(trendsPlace != null){s.append("trendsPlace: " ); s.append( trendsPlace.toStringBuilder());}
		if(usersDerivedInfo != null){s.append("usersDerivedInfo: " ); s.append( usersDerivedInfo.toStringBuilder());}
		if(usersLookup != null){s.append("usersLookup: " ); s.append( usersLookup.toStringBuilder());}
		if(usersProfileBanner != null){s.append("usersProfileBanner: " ); s.append( usersProfileBanner.toStringBuilder());}
		if(usersReportSpam != null){s.append("usersReportSpam: " ); s.append( usersReportSpam.toStringBuilder());}
		if(usersSearch != null){s.append("usersSearch: " ); s.append( usersSearch.toStringBuilder());}
		if(usersShowId != null){s.append("usersShowId: " ); s.append( usersShowId.toStringBuilder());}
		if(usersSuggestions != null){s.append("usersSuggestions: " ); s.append( usersSuggestions.toStringBuilder());}
		if(usersSuggestionsSlug != null){s.append("usersSuggestionsSlug: " ); s.append( usersSuggestionsSlug.toStringBuilder());}
		if(usersSuggestionsSlugMembers != null){s.append("usersSuggestionsSlugMembers: " ); s.append( usersSuggestionsSlugMembers.toStringBuilder());}
		
		return s.toString();
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


