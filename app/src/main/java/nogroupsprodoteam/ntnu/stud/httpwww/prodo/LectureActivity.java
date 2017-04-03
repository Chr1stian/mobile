package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LectureActivity extends FragmentActivity {
    ViewPager viewPager;
    private ArrayList<Course> courseArrayList;
    private ArrayList<Lecture> lectureArrayList;
    private ArrayList<Topic> topicArrayList;
    private static ArrayList<Question> questionArrayList;
    private int selectedCourseID;
    private static int selectedLectureID;
    private static List<Topic> topicsFromSelectedLecture = new ArrayList<>();
    static Integer numberOfTopics;

    public static Integer getNumberOfTopics() {
        return numberOfTopics;
    }

    public static Integer getLectureID(){
        return selectedLectureID;
    }

    public static ArrayList<Topic> getTopicsFromSelectedLecture(){
        return (ArrayList<Topic>) topicsFromSelectedLecture;
    }

    public static ArrayList<Question> getQuestionArrayList() {
        return questionArrayList;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecture_activity);

        //gets values from CourseActivity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        courseArrayList = (ArrayList<Course>) extras.getSerializable("CourseList");
        lectureArrayList = (ArrayList<Lecture>) extras.getSerializable("LectureList");
        topicArrayList = (ArrayList<Topic>) extras.getSerializable("TopicList");
        questionArrayList = (ArrayList<Question>) extras.getSerializable("QuestionList");
        selectedCourseID = extras.getInt("SelectedCourseID");
        selectedLectureID = extras.getInt("SelectedLectureID");

        TextView lbl_name = (TextView) findViewById(R.id.lbl_name);
        TextView lbl_course = (TextView) findViewById(R.id.lbl_course);
        TextView lbl_lecture = (TextView) findViewById(R.id.lbl_lecture);
        lbl_name.setText("DittNick");
        lbl_course.setText("CourseID: " + selectedCourseID);
        lbl_lecture.setText("LectureID: " + selectedLectureID);

        topicsFromSelectedLecture.clear();
        for( Topic topic : topicArrayList) {
            if (topic.getLectureID().equals(Integer.toString(selectedLectureID))) {
                topicsFromSelectedLecture.add(topic);
            }
        }
        numberOfTopics = topicsFromSelectedLecture.size();

        //sets the fragment to be swiped between and its adapter/controller
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
    }
    //unused method to send values to fragment
    public void sendMessage(String course, String nickname, String lecturename, Integer lectureID, Integer numberOfLectures) {
        Intent intent = new Intent(this, PageFragment.class);
        Bundle extras = new Bundle();

        extras.putString("CourseName", course);
        extras.putString("NickName", nickname);
        extras.putString("LectureName", lecturename);
        extras.putInt("LectureID", lectureID);
        extras.putInt("NumberOfLectures", numberOfLectures);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
