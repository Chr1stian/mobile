package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity implements LectureAdapter.ClickListener{
    private RecyclerView recLectures;
    private ArrayList<Course> courseArrayList;
    private ArrayList<Lecture> lectureArrayList;
    private ArrayList<Topic> topicArrayList;
    private ArrayList<Question> questionArrayList;
    private int selectedCourseID;
    private List<Lecture> lecturesFromSelectedCourse = new ArrayList<>();
    private int userID;
    private String nickname;

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
        questionArrayList = (ArrayList<Question>) extras.getSerializable("QuestionList");
        selectedCourseID = extras.getInt("SelectedCourseID");
        userID = extras.getInt("UserID");
        nickname = extras.getString("NickName");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(nickname);
        actionBar.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        TextView lbl_course = (TextView) findViewById(R.id.lbl_course);
        lbl_course.setText(courseArrayList.get(selectedCourseID - 1).getCourseCode() + " - " + courseArrayList.get(selectedCourseID - 1).getCourseName());



        for( Lecture lecture : lectureArrayList) {
            if (lecture.getCourseID().equals(Integer.toString(selectedCourseID))) {
                lecturesFromSelectedCourse.add(lecture);
            }
        }
        recLectures = (RecyclerView) findViewById(R.id.rec_list_lectures);
        recLectures.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        LectureAdapter adapter = new LectureAdapter((ArrayList<Lecture>) lecturesFromSelectedCourse, getLayoutInflater());
        adapter.setClickListener(this);
        recLectures.setAdapter(adapter);
    }

    //send values to and opens LectureActivity
    public void sendMessage(int position) {
        int selectedLectureID = Integer.parseInt(lecturesFromSelectedCourse.get(position).getLectureID());
        Lecture selectedLecture = lecturesFromSelectedCourse.get(position);
        Intent intent = new Intent(this, LectureActivity.class);
        Bundle extras = new Bundle();
        extras.putSerializable("CourseList", courseArrayList);
        extras.putSerializable("LectureList", lectureArrayList);
        extras.putSerializable("TopicList", topicArrayList);
        extras.putSerializable("QuestionList", questionArrayList);
        extras.putSerializable("LecturesFromSelectedCourseList", (Serializable) lecturesFromSelectedCourse);
        extras.putSerializable("SelectedLecture", selectedLecture);
        extras.putInt("SelectedCourseID", selectedCourseID);
        extras.putInt("SelectedLectureID", selectedLectureID);
        extras.putInt("UserID", userID);
        extras.putString("NickName", nickname);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void itemClicked(View view, int position) {
        sendMessage(position);
    }

    //Creates icon in actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Handles button/icon click activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            openUsersQuestions();
        }
        return super.onOptionsItemSelected(item);
    }

    //opens activity displaying the users asked questions
    public void openUsersQuestions(){
        Intent intent = new Intent(this, QuestionActivity.class);
        Bundle extras = new Bundle();
        extras.putInt("UserID", userID);
        extras.putString("NickName", nickname);
        extras.putSerializable("CourseList", courseArrayList);
        extras.putSerializable("LectureList", lectureArrayList);
        extras.putSerializable("TopicList", topicArrayList);
        intent.putExtras(extras);
        startActivity(intent);
    }
}

