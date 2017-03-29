package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.DialogInterface;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText txt_name;
    TextView lbl_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //needed for database connection
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        txt_name = (EditText) findViewById(R.id.txt_name);
        lbl_name = (TextView) findViewById(R.id.lbl_name);

        final ArrayList<Course> courseArrayList = Database.getCourses();
        final ArrayList<Lecture> lectureArrayList = Database.getLectures();
        final ArrayList<Topic> topicArrayList = Database.getTopics();

        Button btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //checks if nickname is taken
                if(Database.checkNickname(txt_name.getText().toString())){
                    //inserts it if not taken and goes to next view
                    Database.registerNickname(txt_name.getText().toString());
                    sendMessage(courseArrayList, lectureArrayList, topicArrayList);
                }
                else{
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setMessage("Nickname already taken");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        });

    }
    //opens new activity and passes values
    public void sendMessage(ArrayList<Course> courseArrayList, ArrayList<Lecture> lectureArrayList, ArrayList<Topic> topicArrayList) {
        Intent intent = new Intent(this, SelectionActivity.class);
        EditText editText = (EditText) findViewById(R.id.txt_name);
        String nickname = editText.getText().toString();
        Bundle extras = new Bundle();
        extras.putString("NickName", nickname);
        extras.putSerializable("CourseList", courseArrayList);
        extras.putSerializable("LectureList", lectureArrayList);
        extras.putSerializable("TopicList", topicArrayList);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
