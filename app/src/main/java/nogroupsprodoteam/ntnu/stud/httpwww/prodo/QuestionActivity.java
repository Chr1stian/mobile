package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    private RecyclerView recQuestions;
    private ArrayList<Question> questionsWithUserID;
    private ArrayList<Course> courseArrayList;
    private ArrayList<Lecture> lectureArrayList;
    private ArrayList<Topic> topicArrayList;
    private int userID;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        //gets values from activity that opened this activity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        userID = extras.getInt("UserID");
        nickname = extras.getString("NickName");
        courseArrayList = (ArrayList<Course>) extras.getSerializable("CourseList");
        lectureArrayList = (ArrayList<Lecture>) extras.getSerializable("LectureList");
        topicArrayList = (ArrayList<Topic>) extras.getSerializable("TopicList");

        //sets nickname in the actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(nickname);
        actionBar.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        //gets questions to the current user
        questionsWithUserID = Database.getQuestionsWithUserID(userID);

        //creates and fills a recycleview with the users questions
        recQuestions = (RecyclerView) findViewById(R.id.recQuestions);
        recQuestions.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recQuestions.setAdapter(new QuestionsAdapter(questionsWithUserID, courseArrayList, lectureArrayList, topicArrayList, getLayoutInflater()));
    }
}
