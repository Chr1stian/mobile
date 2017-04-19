package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;

import java.util.ArrayList;


//TA VEKK LOGGING?

public class MainActivity extends AppCompatActivity {
    EditText txt_name;
    TextView lbl_name;
    Button btn_ok;
    TextView lbl_welcomemsg;
    TextView lbl_username;
    String username;
    Integer userID = null;
    Button btn_changeuser;
    Button btn_proceed;
    TextView lbl_proceed;

    ArrayList<Course> courseArrayList;
    ArrayList<Lecture> lectureArrayList;
    ArrayList<Topic> topicArrayList;
    ArrayList<Question> questionArrayList;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    AlertDialog.Builder builder1;
    AlertDialog alert11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //needed for database connection
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        txt_name = (EditText) findViewById(R.id.txt_name);
        lbl_name = (TextView) findViewById(R.id.lbl_name);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        lbl_welcomemsg = (TextView) findViewById(R.id.lbl_welcomemsg);
        lbl_username = (TextView) findViewById(R.id.lbl_username);
        btn_changeuser = (Button) findViewById(R.id.btn_changeuser);
        btn_proceed = (Button) findViewById(R.id.btn_staysameuser);
        lbl_proceed = (TextView) findViewById(R.id.lbl_proceed);

        //createAlert
        createAlert();

        //Listeners:
        buttonListenerOnOkButton();
        buttonListenerOnChangeUserButton();
        buttonListenerOnProceedButton();

        run();
    }

    //opens new activity and passes values
    public void sendMessage(ArrayList<Course> courseArrayList, ArrayList<Lecture> lectureArrayList, ArrayList<Topic> topicArrayList, ArrayList<Question> questionArrayList) {
        Intent intent = new Intent(this, SelectionActivity.class);
      //  EditText editText = (EditText) findViewById(R.id.txt_name);

        Log.e("test","switching actvities");
        String nickname = username;
        Bundle extras = new Bundle();
        extras.putString("NickName", nickname);
        extras.putSerializable("CourseList", courseArrayList);
        extras.putSerializable("LectureList", lectureArrayList);
        extras.putSerializable("TopicList", topicArrayList);
        extras.putSerializable("QuestionList", questionArrayList);
        intent.putExtras(extras);
        startActivity(intent);
    }

    //shows log in elements
    public void makeLogInMenuVISIBLE(){
        lbl_name.setVisibility(View.VISIBLE);
        txt_name.setVisibility(View.VISIBLE);
        txt_name.setEnabled(true);
        btn_ok.setVisibility(View.VISIBLE);
        btn_ok.setEnabled(true);
    }

    //hides log in elements
    public void makeLogInMenuGONE(){
        lbl_name.setVisibility(View.GONE);
        txt_name.setVisibility(View.GONE);
        txt_name.setEnabled(false);
        btn_ok.setVisibility(View.GONE);
        btn_ok.setEnabled(false);
    }

    //show welcome text
    public void makeWelcomeScreenVISIBLE(){
        lbl_username.setVisibility(View.VISIBLE);
        lbl_welcomemsg.setVisibility(View.VISIBLE);
    }

    //hide welcome message
    public void makeWelcomeScreenGONE(){
        lbl_username.setVisibility(View.GONE);
        lbl_welcomemsg.setVisibility(View.GONE);
    }

    //show welcom message buttons
    public void makeWelcomeScreenButtonsVISIBLE(){
        lbl_proceed.setVisibility(View.VISIBLE);
        btn_proceed.setVisibility(View.VISIBLE);
        btn_changeuser.setVisibility(View.VISIBLE);
    }

    //hide welcome message buttons
    public void makeWelcomeScreenButtonsGONE(){
        lbl_proceed.setVisibility(View.GONE);
        btn_proceed.setVisibility(View.GONE);
        btn_changeuser.setVisibility(View.GONE);
    }

    // onclicklistener for Yes button
    public void buttonListenerOnProceedButton(){
        btn_proceed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makeWelcomeScreenButtonsGONE();
                sendMessage(courseArrayList, lectureArrayList, topicArrayList);
            }
        });
    }

    // onclicklistener for Change User button
    public void buttonListenerOnChangeUserButton(){
        btn_changeuser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              //  Log.e("test","before the clear");
               // Log.e("test","before the clear" + sharedPref.getInt(getString(R.string.userID), 0));

                editor.clear();
                editor.commit();
            //    Log.e("test","before the clear");
              //  Log.e("test","before the clear");

                run();
            }
        });
    }

    //onclicklistener for Ok Button
    public void buttonListenerOnOkButton(){
        btn_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //checks if nickname is taken
                username = txt_name.getText().toString();
                Log.e("test","e det før eller etter validering?");
                if (Database.checkNickname(username)) {
                    lbl_username.setText(username);
                    makeWelcomeScreenVISIBLE();
                    makeLogInMenuGONE();
                    Log.e("test","e det før eller etter validering? 2");
                    //inserts it if not taken and goes to next view
                    userID = Database.registerNickname(username);
                    Log.e("test","foryimeinh");
                    editor.putInt(getString(R.string.userID), userID);
                    editor.putString(getString(R.string.username), username);
                    editor.commit();
                    Log.e("test", "return from database: " + userID);
                    Log.e("test", "return from preferences: " + sharedPref.getInt(getString(R.string.userID), 0));
                    //Hide log in meu¨'
                    makeWelcomeScreenVISIBLE();
                    makeLogInMenuGONE();
                    sendMessage(courseArrayList, lectureArrayList, topicArrayList);
                } else {
                    alert11.show();
                }
            }
        });
    }

    //creat popup for for after pressing OK button
    public void createAlert(){
        builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage("Nickname already exist, is this you?");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Hiding login menu and show welcome message


                makeLogInMenuGONE();
                makeWelcomeScreenVISIBLE();

                Log.e("alert","Trykking på yes knappen");
                userID = Database.getUserID(username);
                editor.putInt(getString(R.string.userID), userID);
                editor.putString(getString(R.string.username), username);
                editor.commit();
                Log.e("try",""+sharedPref.getInt(getString(R.string.userID), 0));
                Log.e("try",sharedPref.getString(getString(R.string.username), "Error"));
                lbl_username.setText(username);
               // sendMessage(courseArrayList,lectureArrayList,topicArrayList);


                Log.e("test", "1 alert");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("test","2 alert");
                        sendMessage(courseArrayList, lectureArrayList, topicArrayList);
                    }
                },1500);

                Log.e("test", "3 alert");
/*
                Log.e("try","sharedPref.getInt(getString(R.string.userID), 0)");
                Log.e("try","sharedPref.getString(getString(R.string.username), "Error")");
                Log.e("test", "alertinn d" + userID);
                Log.e("test", "alertinn d" + username);


                */


            }
        });
        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        alert11 = builder1.create();
    }

    //running the process
    public void run(){
        makeLogInMenuGONE();
        makeWelcomeScreenButtonsGONE();
        makeWelcomeScreenGONE();

        //get info for from app preferences files
        userID = sharedPref.getInt(getString(R.string.userID), 0);
        username = sharedPref.getString(getString(R.string.username), "Error");
        Log.e("test", "det " + userID);

        lbl_username.setText(username);


        if (userID == 0) {
            //NO USER ID
            //Show logIn menu
            makeLogInMenuVISIBLE();
            Log.e("test", "lol");
        } else {
            //WITH USER ID
            Log.e("test", "allerede innlogga");
            //Showing welcome message and buttons
            makeWelcomeScreenVISIBLE();
            makeWelcomeScreenButtonsVISIBLE();
          /*  KAN FJERNE?
            //Database connection for no user idea
            final ArrayList<Course> courseArrayList = Database.getCourses();
            final ArrayList<Lecture> lectureArrayList = Database.getLectures();
            final ArrayList<Topic> topicArrayList = Database.getTopics();
            */
        }
    }

}