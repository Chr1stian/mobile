package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Christian on 25.04.2017.
 */
public class CourseTest {
    String testCourseID = "4";
    String testCourseCode = "5";
    String testCourseName = "testCourse";

    @Test
    public void getCourseID() throws Exception {
        Course test = new Course(testCourseID, testCourseCode, testCourseName);
        test.setCourseID(testCourseID);
        String courseID = test.getCourseID();
        assertEquals(testCourseID, courseID);
    }

    @Test
    public void getCourseCode() throws Exception {
        Course test = new Course(testCourseID, testCourseCode, testCourseName);
        test.setCourseCode(testCourseCode);
        String courseCode = test.getCourseCode();
        assertEquals(testCourseCode, courseCode);
    }

    @Test
    public void getCourseName() throws Exception {
        Course test = new Course(testCourseID, testCourseCode, testCourseName);
        test.setCourseName(testCourseName);
        String courseName = test.getCourseName();
        assertEquals(testCourseName, courseName);
    }

}