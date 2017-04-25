package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Christian on 25.04.2017.
 */
public class LectureTest {
    String testLectureID = "6";
    String testCourseID = "7";
    String testLectureNumber = "8";
    String testLectureName = "testLecture";

    @Test
    public void testLectureID() {
        Lecture test = new Lecture(testLectureID, testCourseID, testLectureNumber, testLectureName);
        test.setLectureID(testLectureID);
        String lectureID = test.getLectureID();
        assertEquals(testLectureID, lectureID);
    }
    @Test
    public void testCourseID() {
        Lecture test = new Lecture(testLectureID, testCourseID, testLectureNumber, testLectureName);
        test.setCourseID(testCourseID);
        String courseID = test.getCourseID();
        assertEquals(testCourseID, courseID);
    }
    @Test
    public void testLectureNumber() {
        Lecture test = new Lecture(testLectureID, testCourseID, testLectureNumber, testLectureName);
        test.setLectureNumber(testLectureNumber);
        String lectureNumber = test.getLectureNumber();
        assertEquals(testLectureNumber, lectureNumber);
    }
    @Test
    public void testLectureName() {
        Lecture test = new Lecture(testLectureID, testCourseID, testLectureNumber, testLectureName);
        test.setLectureName(testLectureName);
        String lectureName = test.getLectureName();
        assertEquals(testLectureName, lectureName);
    }

}