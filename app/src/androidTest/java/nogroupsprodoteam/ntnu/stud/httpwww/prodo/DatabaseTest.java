package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.provider.ContactsContract;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Christian on 22.03.2017.
 */
public class DatabaseTest {
    int testCourseID = 3;
    int testLectureID = 12;
    int testLectureNumber = 3;

    String testmySQLAdr = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11165164?allowMultiQueries=true";
    String testmySQLUser = "sql11165164";
    String testmySQLPass = "FPJxTYsVA3";

    Database db = null;

    @Before
    public void changeDatabase(){
        this.db = new Database(testmySQLAdr, testmySQLUser, testmySQLPass);
    }

    @Test
    public void getCourses() throws Exception {
        ArrayList<String> testCourse = new ArrayList<String>();
        testCourse.add("TDT4140 Software Engineering");
        ArrayList<String> course = db.getCourses();
        assertEquals(course.get(0), testCourse.get(0));
    }

    @Test
    public void getLectures() throws Exception {
        ArrayList<String> testLectures = new ArrayList<String>();
        testLectures.add("1 Fagintroduksjon");
        ArrayList<String> lectures = db.getLectures(testCourseID);
        assertEquals(lectures.get(0), testLectures.get(0));
    }

    @Test
    public void getLectureID() throws Exception {
        int lectureID = db.getLectureID(testCourseID,testLectureNumber);
        assertEquals(lectureID, testLectureID);
    }

    @Test
    public void countTopics() throws Exception {
        int numberOfTopics = Database.countTopics(testLectureID);
        assertEquals(numberOfTopics, 4);
    }

    @Test
    public void getTopic() throws Exception {

    }

    @Test
    public void getTopicID() throws Exception {

    }

    @Test
    public void setRating() throws Exception {

    }

    @Test
    public void checkNickname() throws Exception {

    }

    @Test
    public void registerNickname() throws Exception {

    }

    @Test
    public void sendQuestion() throws Exception {

    }

    @Test
    public void getQuestions() throws Exception {

    }

}