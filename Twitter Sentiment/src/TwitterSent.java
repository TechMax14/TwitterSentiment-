import java.util.ArrayList;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterSent {
	

	public static ArrayList<String> getTweets(String topic) throws TwitterException {
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey("xaGXgA4U2UEoKPOZkb4wTGImM")
			.setOAuthConsumerSecret("chCIogrXYTKVN6lXOXt2Yxk1UEvIRusTibsoEbflOYlnJCqNGb")
			.setOAuthAccessToken("251660110-KsWIyneYupEOdCLXTLIPzAcItXMwNzIIr2hcFGIC")
			.setOAuthAccessTokenSecret("wyRN0CQ7sv4ijDhhAFMCa5tW8PR7QxEWeShB7ukcL3poF");
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter4j.Twitter twitter = tf.getInstance();
		ArrayList<String> tweetList = new ArrayList<String>();
		//List<Status> feed = twitter.getHomeTimeline();
		
		try {
			Query query = new Query(topic);
			QueryResult result;
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					if(!((tweet.getText().charAt(0) == 'R')&&(tweet.getText().charAt(1) == 'T')))
						tweetList.add("@"+tweet.getUser().getScreenName() +" --> ["+tweet.getText()+"]");
				}
			} while ((query = result.nextQuery()) != null);
		} catch (TwitterException te) {
			//te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}
		
		return tweetList;
	}
	
//	public static ArrayList<String> getTweets() throws TwitterException {
//		
//		ConfigurationBuilder cb = new ConfigurationBuilder();
//		cb.setDebugEnabled(true)
//			.setOAuthConsumerKey("xaGXgA4U2UEoKPOZkb4wTGImM")
//			.setOAuthConsumerSecret("chCIogrXYTKVN6lXOXt2Yxk1UEvIRusTibsoEbflOYlnJCqNGb")
//			.setOAuthAccessToken("251660110-KsWIyneYupEOdCLXTLIPzAcItXMwNzIIr2hcFGIC")
//			.setOAuthAccessTokenSecret("wyRN0CQ7sv4ijDhhAFMCa5tW8PR7QxEWeShB7ukcL3poF");
//		
//		TwitterFactory tf = new TwitterFactory(cb.build());
//		twitter4j.Twitter twitter = tf.getInstance();
//		ArrayList<String> tweetList = new ArrayList<String>();
//		List<Status> feed = twitter.getHomeTimeline();
//
//		for (Status tweet : feed) {
//			tweetList.add("@"+tweet.getUser().getName() +" --> ["+tweet.getText()+"]");
//		}		
//		return tweetList;
//	}
}

