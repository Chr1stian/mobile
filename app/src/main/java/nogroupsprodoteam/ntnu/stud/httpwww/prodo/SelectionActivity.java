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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectionActivity extends AppCompatActivity implements CourseAdapter.ClickListener{
    private RecyclerView recCourses;
    private ArrayList<Course> courseArrayList;
    private ArrayList<Lecture> lectureArrayList;
    private ArrayList<Topic> topicArrayList;
    private ArrayList<Question> questionArrayList;
    private int userID;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_activity);

        //gets values from MainActivity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        courseArrayList = (ArrayList<Course>) extras.getSerializable("CourseList");
        lectureArrayList = (ArrayList<Lecture>) extras.getSerializable("LectureList");
        topicArrayList = (ArrayList<Topic>) extras.getSerializable("TopicList");
        questionArrayList = (ArrayList<Question>) extras.getSerializable("QuestionList");
        userID = extras.getInt("UserID");
        nickname = extras.getString("NickName");

        //sets nickname in actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(nickname);
        actionBar.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        //creates and fills a recycleview with the list of courses
        recCourses = (RecyclerView) findViewById(R.id.recycler_view);
        recCourses.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        CourseAdapter adapter = new CourseAdapter(courseArrayList, getLayoutInflater());
        adapter.setClickListener(this);
        recCourses.setAdapter(adapter);
    }

    //Sends values to and opens CourseActivity
    public void sendMessage(int position) {
        int selectedCourseID = Integer.parseInt(courseArrayList.get(position).getCourseID());
        Intent intent = new Intent(this, CourseActivity.class);
        Bundle extras = new Bundle();
        extras.putSerializable("CourseList", courseArrayList);
        extras.putSerializable("LectureList", lectureArrayList);
        extras.putSerializable("TopicList", topicArrayList);
        extras.putSerializable("QuestionList", questionArrayList);
        extras.putInt("UserID", userID);
        extras.putString("NickName", nickname);
        extras.putInt("SelectedCourseID", selectedCourseID);
        intent.putExtras(extras);
        startActivity(intent);
    }
    //Passes the clicked element/list item position
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
