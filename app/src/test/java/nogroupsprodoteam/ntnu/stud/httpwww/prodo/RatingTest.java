package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Christian on 25.04.2017.
 */
public class RatingTest {
    String testRatingID = "3";
    String testTopicID = "5";
    String testUserID = "1";
    String testStars = "5";

    @Test
    public void getRatingID() throws Exception {
        Rating testRating = new Rating(testRatingID, testTopicID, testUserID, testStars);
        testRating.setRatingID(testRatingID);
        String ratingID = testRating.getRatingID();
        assertEquals(ratingID, testRatingID);
    }

    @Test
    public void getTopicID() throws Exception {
        Rating testRating = new Rating(testRatingID, testTopicID, testUserID, testStars);
        testRating.setTopicID(testTopicID);
        String topicID = testRating.getTopicID();
        assertEquals(topicID, testTopicID);
    }

    @Test
    public void getUserID() throws Exception {
        Rating testRating = new Rating(testRatingID, testTopicID, testUserID, testStars);
        testRating.setUserID(testUserID);
        String userID = testRating.getUserID();
        assertEquals(userID, testUserID);
    }

    @Test
    public void getStar() throws Exception {
        Rating testRating = new Rating(testRatingID, testTopicID, testUserID, testStars);
        testRating.setStar(testStars);
        String stars = testRating.getStar();
        assertEquals(stars, testStars);
    }

}