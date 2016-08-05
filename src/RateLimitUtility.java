import java.util.Calendar;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;


/**
 * Created by Kannan Kuttalam on 03/23/2016.
 *
 * RateLimitUtility:  Limits 10 HTTP requests/second to Twitter api
 */
public class RateLimitUtility {

	private Twitter twitter;
	private int limit;

	// creating object with the given twitter object and request limit/second
	RateLimitUtility(Twitter twitter, int limit)
	{
		this.twitter = twitter;
		this.limit = limit;
	}

	// validates and control the number of HTTP requests to twitter api
	public void rateLimiterUtility()
	{
		try{
			User user = twitter.verifyCredentials();
            Calendar cal = Calendar.getInstance();
            int s = cal.get(Calendar.SECOND);
            int count = 0;
            
			do{
				
				System.out.println();
				List<Status> statuses = twitter.getHomeTimeline();
				count++;
				System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");

				for (Status status : statuses) {
					System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
				}
				
				if(count > this.limit)
				{
					System.out.println("HTTP calls cannot be made more than 10 requests/second");
					break;
				}
			}
			while((s == cal.get(Calendar.SECOND)));
		}
		catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
	}
}
