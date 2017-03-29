package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import java.io.Serializable;

/**
 * Created by Christian on 29.03.2017.
 */

public class Lecture implements Serializable{
    private String lectureID;
    private String courseID;
    private String lectureNumber;
    private String lectureName;

    public Lecture(String lectureID, String courseID, String lectureNumber, String lectureName) {
        this.lectureID = lectureID;
        this.courseID = courseID;
        this.lectureNumber = lectureNumber;
        this.lectureName = lectureName;
    }

    public String getLectureID() {
        return lectureID;
    }

    public void setLectureID(String lectureID) {
        this.lectureID = lectureID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getLectureNumber() {
        return lectureNumber;
    }

    public void setLectureNumber(String lectureNumber) {
        this.lectureNumber = lectureNumber;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }
}
