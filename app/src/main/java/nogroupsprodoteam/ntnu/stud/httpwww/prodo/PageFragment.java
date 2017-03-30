package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {
    TextView textView;
    TextView testShowRating;
    TextView ratingDescription;
    String staus;
    RatingBar ratingBar;
    View view;
    Integer topicID;
    Button submitQuestionButton;
    TextView textQuestion;
    EditText question;
    String questionString;
    TextView submitOK;
    ListView showQuestions;
    ArrayAdapter<String> arrayAdapter;
    private RecyclerView rec_Questions;


    final static DateFormat fmt = DateFormat.getTimeInstance(DateFormat.LONG);


    public PageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.page_fragment, container, false);
        setupUI(view.findViewById(R.id.rellay_parentforPageFragment));

        textView = (TextView)view.findViewById(R.id.lbl_page);
        Bundle bundle = getArguments();
        //gets values from adapter
        String topic = bundle.getString("topic");
        topicID = bundle.getInt("topicID");
        textView.setText(topic);


        //currently unused and unreachable code for getting values from LectureActivity
        //use SendMessage in LectureActivity first
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        String coursename = extras.getString("CourseName");
        String nickname = extras.getString("NickName");
        String lecturename = extras.getString("LectureName");
        Integer lectureID = extras.getInt("LectureID");
        Integer numberOfLectures = extras.getInt("NumberOfLectures");

        //hide keyboard?
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        testShowRating = (TextView)view.findViewById(R.id.lbl_testRatingView);
        testShowRating.setText("No rating yet..");

        ratingDescription = (TextView)view.findViewById(R.id.lbl_ratingDescription);
        ratingDescription.setText("How well do you understand the current topic?");

        textQuestion = (TextView)view.findViewById(R.id.lbl_askQuestion);
        textQuestion.setText("Do you have any questions?");
        submitQuestionButton = (Button)view.findViewById(R.id.btn_SubmitQuestion);
        question = (EditText)view.findViewById(R.id.txt_question);
        question.setHint("Ask a question");
        submitOK = (TextView)view.findViewById(R.id.lbl_submitOK);
        submitOK.setText(null);
        //showQuestions = (ListView)view.findViewById(R.id.list_questions);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar_understanding);

        ArrayList<Question> questionArrayList = Database.getQuestions(topicID);


        //Show list of questions already asked
        //showListOfQuestions();

        //Listening for changes in rating
        addListenerOnRatingBar();

        //Listening for buttonClicks
        addOnClickListnerToSubmitButton();
     //-------------------------------------------------------------------
        rec_Questions = (RecyclerView) view.findViewById(R.id.rec_list_questions);
        rec_Questions.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rec_Questions.setAdapter(new QuestionAdapter(questionArrayList, getActivity().getLayoutInflater()));


    //-------------------------------------------------------------------

        ScheduledThreadPoolExecutor sch = (ScheduledThreadPoolExecutor)
                Executors.newScheduledThreadPool(5);
        Runnable periodicalUpdate = new Runnable(){
            @Override
            public void run() {
                try{
                    //Thread.sleep(10 * 1000);
                    //updateListOfQuestions();
                }catch(Exception e){
                  }
            }
        };
        ScheduledFuture<?> delayFuture = sch.scheduleWithFixedDelay(periodicalUpdate, 30, 30, TimeUnit.SECONDS);
        return view;
    }

    private void addListenerOnRatingBar() {


        //listening for changes in rating
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //showing rating value in testtext display and sends rating to database
                staus = Integer.toString(Math.round(rating));

                String errorMessage = Database.setRating(topicID,Math.round(rating));
                testShowRating.setText(errorMessage);
            }
        });
    }

    private void addOnClickListnerToSubmitButton(){
        submitQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionString = question.getText().toString();

                submitOK.setText("Processing...");
                //String length validation
                if (isQuestionValid(questionString)){

                    final String errorMessage = Database.sendQuestion(topicID,questionString);

                    //delay stopping button from being clickable for 4 seconds while "processing"
                    submitQuestionButton.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            submitOK.setText(errorMessage);
                            question.setText(null);
                            submitQuestionButton.setEnabled(true);
                           // updateListOfQuestions();
                        }
                    },1750);
                }else{
                    question.setText(null);
                }
            }
        });
    }

   /* private void showListOfQuestions(){
        //creates ArrayList with Lectures on selected course from Database
        ArrayList<String> ListViewArray  = Database.getQuestions(topicID);

        // Create an ArrayAdapter using the ArrayList
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_selectable_list_item, ListViewArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        showQuestions.setAdapter(arrayAdapter);
    } */

    private boolean isQuestionValid(String questionString){
        //validates that the string is longer than 2 characters. and displays invalid questions statment if false.
        int lengthOfString = questionString.length();

        if (lengthOfString>2){
            question.setHint("Ask a new question");

            return true;
        } else {
            question.setHint(""+lengthOfString);
            /*"Invalid question, try again."*/
            submitOK.setText("Question not submitted...");
            return false;
        }
    }

  /*  private void updateListOfQuestions(){
        //update questions list
        ArrayList<String> ListViewArrayUpdated  = Database.getQuestions(topicID);
        arrayAdapter.clear();
        arrayAdapter.addAll(ListViewArrayUpdated);

    }
*/

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

}

//For å hente ut autogenerert value
/*  public static int insertResult() {
    	try {
    		Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
    		PreparedStatement stmt = conn.prepareStatement("insert into RESULTAT () values ()", Statement.RETURN_GENERATED_KEYS);
        	stmt.executeUpdate();
        	ResultSet rs = stmt.getGeneratedKeys();
        	if (rs.next()) {
        		return rs.getInt(1);
        	} else {
        		System.out.println("rs empty");
        	}
    	} catch(SQLException e) {
        	System.out.println(e);
        }
    	return -1;
    }
    //Anna eksemplkode for detecting top and bottom
      listview.setOnScrollListener(new OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (firstVisibleItem == 0) {
                // check if we reached the top or bottom of the list
                View v = listview.getChildAt(0);
                int offset = (v == null) ? 0 : v.getTop();
                if (offset == 0) {
                    // reached the top:
                    return;
                }
            } else if (totalItemCount - visibleItemCount == firstVisibleItem){
                View v =  listview.getChildAt(totalItemCount-1);
                int offset = (v == null) ? 0 : v.getTop();
                if (offset == 0) {
                    // reached the top:
                    return;
                }
            }
        }
    });
    */

   /*  //function to run when lecture is selected from list
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object lecture = list.getItemAtPosition(position);
                //gets lectureID from database based on selected lecture in list
                Integer lectureID = Database.getLectureID(courseNumber, position + 1);
                sendMessage(coursename, nickname, lecture.toString(), lectureID);
            }
        });*/