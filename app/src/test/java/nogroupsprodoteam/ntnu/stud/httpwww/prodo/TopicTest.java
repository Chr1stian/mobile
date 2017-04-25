package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Christian on 25.04.2017.
 */
public class TopicTest {
    String testTopicID = "11";
    String testLectureID = "12";
    String testTopicNumber = "13";
    String testTopicName = "testTopic";

    @Test
    public void testTopicID() {
        Topic test = new Topic(testTopicID, testLectureID, testTopicNumber, testTopicName);
        test.setTopicID(testTopicID);
        String topicID = test.getTopicID();
        assertEquals(testTopicID, topicID);
    }
    @Test
    public void testLectureID() {
        Topic test = new Topic(testTopicID, testLectureID, testTopicNumber, testTopicName);
        test.setLectureID(testLectureID);
        String LectureID = test.getLectureID();
        assertEquals(testLectureID, LectureID);
    }
    @Test
    public void testTopicNumber() {
        Topic test = new Topic(testTopicID, testLectureID, testTopicNumber, testTopicName);
        test.setTopicNumber(testTopicNumber);
        String topicNumber = test.getTopicNumber();
        assertEquals(testTopicNumber, topicNumber);
    }
    @Test
    public void testTopicName() {
        Topic test = new Topic(testTopicID, testLectureID, testTopicNumber, testTopicName);
        test.setTopicName(testTopicName);
        String topicName = test.getTopicName();
        assertEquals(testTopicName, topicName);
    }
}