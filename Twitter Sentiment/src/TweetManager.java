import java.util.ArrayList;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetManager {

	public static ArrayList<String> getTweets(String topic) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey("xaGXgA4U2UEoKPOZkb4wTGImM")
			.setOAuthConsumerSecret("chCIogrXYTKVN6lXOXt2Yxk1UEvIRusTibsoEbflOYlnJCqNGb")
			.setOAuthAccessToken("251660110-KsWIyneYupEOdCLXTLIPzAcItXMwNzIIr2hcFGIC")
			.setOAuthAccessTokenSecret("wyRN0CQ7sv4ijDhhAFMCa5tW8PR7QxEWeShB7ukcL3poF");
	

//		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter4j.Twitter twitter = tf.getInstance();
		ArrayList<String> tweetList = new ArrayList<String>();
		try {
			Query query = new Query(topic);
			QueryResult result;
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					tweetList.add(tweet.getText());
				}
			} while ((query = result.nextQuery()) != null);
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}
		return tweetList;
	}
}