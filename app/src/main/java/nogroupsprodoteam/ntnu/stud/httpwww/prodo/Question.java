package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import java.io.Serializable;

/**
 * Created by thea_ on 30.03.2017.
 */

public class Question implements Serializable{
    private String questionID;
    private String topicID;
    private String userID;
    private String question;
    private String answer;
    private String rating;

    public Question(String questionID, String topicID, String userID, String question, String answer, String rating) {
        this.questionID = questionID;
        this.topicID = topicID;
        this.userID = userID;
        this.question = question;
        this.answer = answer;
        this.rating = rating;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
