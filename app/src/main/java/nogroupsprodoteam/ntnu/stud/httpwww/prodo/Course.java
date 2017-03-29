package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import java.io.Serializable;

/**
 * Created by Christian on 29.03.2017.
 */

public class Course implements Serializable{
    private String courseID;
    private String courseCode;
    private String courseName;

    public Course(String courseID, String courseCode, String courseName) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }
    public String getCourseID() {
        return courseID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
