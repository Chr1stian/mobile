package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

/**
 * Created by thea_ on 30.03.2017.
 */

public class Rating {
    private String ratingID;
    private String topicID;
    private String userID;
    private String star;

    public Rating(String ratingID, String topicID, String userID, String star) {
        this.ratingID = ratingID;
        this.topicID = topicID;
        this.userID = userID;
        this.star = star;
    }

    public String getRatingID() {
        return ratingID;
    }

    public void setRatingID(String ratingID) {
        this.ratingID = ratingID;
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
