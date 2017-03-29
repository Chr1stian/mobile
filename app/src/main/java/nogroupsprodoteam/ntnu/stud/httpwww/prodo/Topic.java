package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import java.io.Serializable;

/**
 * Created by Christian on 29.03.2017.
 */

public class Topic implements Serializable{
    private String topicID;
    private String lectureID;
    private String topicNumber;
    private String topicName;

    public Topic(String topicID, String lectureID, String topicNumber, String topicName) {
        this.topicID = topicID;
        this.lectureID = lectureID;
        this.topicNumber = topicNumber;
        this.topicName = topicName;
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getLectureID() {
        return lectureID;
    }

    public void setLectureID(String lectureID) {
        this.lectureID = lectureID;
    }

    public String getTopicNumber() {
        return topicNumber;
    }

    public void setTopicNumber(String topicNumber) {
        this.topicNumber = topicNumber;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
