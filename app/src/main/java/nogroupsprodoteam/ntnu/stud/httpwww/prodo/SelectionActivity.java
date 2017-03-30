package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_activity);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final ArrayList<Course> courseArrayList = (ArrayList<Course>) extras.getSerializable("CourseList");
        final ArrayList<Lecture> lectureArrayList = (ArrayList<Lecture>) extras.getSerializable("LectureList");
        final ArrayList<Topic> topicArrayList = (ArrayList<Topic>) extras.getSerializable("TopicList");
        final String nickname = intent.getStringExtra("NickName");

        final TextView lbl_name = (TextView) findViewById(R.id.lbl_name);
        lbl_name.setText(nickname);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new CourseAdapter(courseArrayList, getLayoutInflater()));
    }

    //Sends values to and opens CourseActivity
    public void sendMessage(String course, String nickname, Integer courseNumber) {
        Intent intent = new Intent(this, CourseActivity.class);
        Bundle extras = new Bundle();

        extras.putString("CourseName", course);
        extras.putString("NickName", nickname);
        extras.putInt("CourseNumber", courseNumber);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
