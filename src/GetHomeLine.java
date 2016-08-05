import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by Kannan Kuttalam on 03/23/2016.
 *
 * GetHomeLine - Class to get the list of posts in give user's home
 */

public class GetHomeLine {

	public static void main(String[] args) {

		// gets Twitter instance with default credentials
		// please replace with your credentials
		ConfigurationBuilder cb = new ConfigurationBuilder();
			 cb.setDebugEnabled(true)
			   .setOAuthConsumerKey("*********************")
			   .setOAuthConsumerSecret("*****************")
			   .setOAuthAccessToken("*******************")
			   .setOAuthAccessTokenSecret("**************");

		// create the twitter factory
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		// RateLimitUtility takes twitter handle and request limits per second
		RateLimitUtility ratelimitUtility = new RateLimitUtility(twitter,10);

		ratelimitUtility.rateLimiterUtility();
	}
}
