package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {
    private ArrayList<Course> courseArrayList;
    private ArrayList<Lecture> lectureArrayList;
    private ArrayList<Topic> topicArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);

        //gets values from SelectionActivity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        courseArrayList = (ArrayList<Course>) extras.getSerializable("CourseList");
        lectureArrayList = (ArrayList<Lecture>) extras.getSerializable("LectureList");
        topicArrayList = (ArrayList<Topic>) extras.getSerializable("TopicList");
        int position = extras.getInt("Position");

        TextView lbl_course = (TextView) findViewById(R.id.lbl_course);
        lbl_course.setText(courseArrayList.get(position).getCourseCode());
        TextView lbl_name = (TextView) findViewById(R.id.lbl_name);


    }
    //send values to and opens LectureActivity
    public void sendMessage(String course, String nickname, String lecture, Integer lectureID) {
        Intent intent = new Intent(this, LectureActivity.class);
        Bundle extras = new Bundle();

        extras.putString("CourseName", course);
        extras.putString("NickName", nickname);
        extras.putString("LectureName", lecture);
        extras.putInt("LectureID", lectureID);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
