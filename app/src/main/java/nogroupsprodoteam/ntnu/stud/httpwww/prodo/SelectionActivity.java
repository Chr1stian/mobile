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
    private RecyclerView recyclerView;
    private ArrayList<Course> courseArrayList;
    private ArrayList<Lecture> lectureArrayList;
    private ArrayList<Topic> topicArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_activity);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        courseArrayList = (ArrayList<Course>) extras.getSerializable("CourseList");
        lectureArrayList = (ArrayList<Lecture>) extras.getSerializable("LectureList");
        topicArrayList = (ArrayList<Topic>) extras.getSerializable("TopicList");
        final String nickname = intent.getStringExtra("NickName");

        final TextView lbl_name = (TextView) findViewById(R.id.lbl_name);
        lbl_name.setText(nickname);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        CourseAdapter adapter = new CourseAdapter(courseArrayList, getLayoutInflater());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    //Sends values to and opens CourseActivity
    public void sendMessage(int position) {
        Intent intent = new Intent(this, CourseActivity.class);
        Bundle extras = new Bundle();
        extras.putSerializable("CourseList", courseArrayList);
        extras.putSerializable("LectureList", lectureArrayList);
        extras.putSerializable("TopicList", topicArrayList);
        extras.putInt("Position", position);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void itemClicked(View view, int position) {
        sendMessage(position);
    }
}
