package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    EditText txt_name;
    TextView lbl_name, lbl_welcomemsg, lbl_username, lbl_proceed;
    Button btn_ok, btn_changeuser, btn_proceed;
    String username;
    Integer userID = null;
    ImageButton imgbtn_onwards;

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
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sets the prodo icon in the actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Prodo");
        actionBar.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

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
        imgbtn_onwards = (ImageButton) findViewById(R.id.imgbtn_onwards);

        //Database connection 	
         courseArrayList = Database.getCourses();
         lectureArrayList = Database.getLectures();
         topicArrayList = Database.getTopics();
          questionArrayList = Database.getAllQuestions();

         //create shared preferences editor
         sharedPref = this.getPreferences(Context.MODE_PRIVATE);
         editor = sharedPref.edit();
        
        //createAlert
        createAlert();

        //Listeners:
        buttonListenerOnOkButton();
        buttonListenerOnChangeUserButton();
        buttonListenerOnProceedButton();
        buttonListenerOnOnwardsButton();

        run();
    }

    //send values to and opens SelectionActivity
    public void sendMessage(ArrayList<Course> courseArrayList, ArrayList<Lecture> lectureArrayList, ArrayList<Topic> topicArrayList, ArrayList<Question> questionArrayList) {
        Intent intent = new Intent(this, SelectionActivity.class);
        String nickname = username;
        Bundle extras = new Bundle();
        extras.putString("NickName", nickname);
        extras.putSerializable("CourseList", courseArrayList);
        extras.putSerializable("LectureList", lectureArrayList);
        extras.putSerializable("TopicList", topicArrayList);
        extras.putSerializable("QuestionList", questionArrayList);
        extras.putInt("UserID", userID);
        intent.putExtras(extras);
        startActivity(intent);
        makeOnwardsButtonsVISIBLE();
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
    public void makeOnwardsButtonsVISIBLE(){
        imgbtn_onwards.setVisibility(View.VISIBLE);
    }

    //hide welcome message buttons
    public void makeOnwardsButtonsGONE(){
        imgbtn_onwards.setVisibility(View.GONE);
    }

    // onclicklistener for Yes button
    public void buttonListenerOnProceedButton(){
        btn_proceed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makeWelcomeScreenButtonsGONE();
                sendMessage(courseArrayList, lectureArrayList, topicArrayList,questionArrayList);
              //  makeOnwardsButtonsVISIBLE();
            }
        });
    }

    // onclicklistener for Onwards button
    public void buttonListenerOnOnwardsButton(){
        imgbtn_onwards.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessage(courseArrayList, lectureArrayList, topicArrayList,questionArrayList);
            }
        });
    }


    // onclicklistener for Change User button
    public void buttonListenerOnChangeUserButton(){
        btn_changeuser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editor.clear();
                editor.commit();
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
                if (Database.checkNickname(username)) {
                    lbl_username.setText(username);
                    makeWelcomeScreenVISIBLE();
                    makeLogInMenuGONE();
                    //inserts it if not taken and goes to next view
                    userID = Database.registerNickname(username);
                    editor.putInt(getString(R.string.userID), userID);
                    editor.putString(getString(R.string.username), username);
                    editor.commit();
                    //Hide log in menu
                    lbl_welcomemsg.setText("Welcome");
                    makeWelcomeScreenVISIBLE();
                    makeLogInMenuGONE();
                    sendMessage(courseArrayList, lectureArrayList, topicArrayList, questionArrayList);
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
                lbl_welcomemsg.setText("Welcome");
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
                makeOnwardsButtonsVISIBLE();
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
        makeOnwardsButtonsGONE();
        lbl_welcomemsg.setText("Welcome back");

        //get info for from app preferences files
        userID = sharedPref.getInt(getString(R.string.userID), 0);
        username = sharedPref.getString(getString(R.string.username), "Error");
        Log.e("test", "det " + userID);

        lbl_username.setText(username);

        if (userID == 0) {
            //NO USER ID
            //Show logIn menu
            makeLogInMenuVISIBLE();
        } else {
            //WITH USER ID
            //Showing welcome message and buttons
            makeWelcomeScreenVISIBLE();
            makeWelcomeScreenButtonsVISIBLE();
        }
    }
}
