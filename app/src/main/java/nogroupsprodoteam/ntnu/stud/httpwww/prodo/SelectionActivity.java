package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_activity);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        courseArrayList = (ArrayList<Course>) extras.getSerializable("CourseList");
        lectureArrayList = (ArrayList<Lecture>) extras.getSerializable("LectureList");
        topicArrayList = (ArrayList<Topic>) extras.getSerializable("TopicList");
        questionArrayList = (ArrayList<Question>) extras.getSerializable("QuestionList");
        final String nickname = intent.getStringExtra("NickName");

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
        extras.putInt("SelectedCourseID", selectedCourseID);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void itemClicked(View view, int position) {
        sendMessage(position);
    }
}
