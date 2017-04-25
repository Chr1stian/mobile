package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static nogroupsprodoteam.ntnu.stud.httpwww.prodo.R.string.userID;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {
    TextView textView, ratingDescription;
    Integer staus;
    RatingBar ratingBar;
    View view;

    Integer topicID, position, count;
    Button submitQuestionButton, btn_swipeleft, btn_swiperight;
   // TextView textQuestion, testShowRating;
    EditText question;
    String questionString;
    TextView submitOK;

     ArrayList<Question> questionsAtTopicID;
    ListView showQuestions;
    ArrayAdapter<String> arrayAdapter;
     RecyclerView rec_Questions;
     QuestionAdapter rec_QPF_adapter;
    LinearLayoutManager rec_QPF_manager;
    Boolean hasRated;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;



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
        //gets values from SwipeAdapter
        String topic = bundle.getString("topic");
        topicID = bundle.getInt("topicID");
        position = bundle.getInt("position");
        count = bundle.getInt("count");
        questionsAtTopicID = (ArrayList<Question>) bundle.getSerializable("QuestionList");
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

       // questionUpdate();

        //hide keyboard?
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //hide or show appropriate buttons for changing fragment/swiping
        btn_swipeleft = (Button) view.findViewById(R.id.btn_swipeleft);
        btn_swiperight = (Button) view.findViewById(R.id.btn_swiperight);
        if(position == 0){
            btn_swipeleft.setVisibility(View.INVISIBLE);
        }
        if(position == count - 1){
            btn_swiperight.setVisibility(View.INVISIBLE);
        }

        //change view/fragment when clicking on left/right buttons
        btn_swiperight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LectureActivity)getActivity()).swiperight(position);
            }
        });

        btn_swipeleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LectureActivity)getActivity()).swipeleft(position);
            }
        });

        //testShowRating = (TextView)view.findViewById(R.id.lbl_testRatingView);
        //testShowRating.setText("No rating yet..");
        ratingDescription = (TextView)view.findViewById(R.id.lbl_ratingDescription);
        ratingDescription.setText("How well do you understand the current topic?");
      //  textQuestion = (TextView)view.findViewById(R.id.lbl_askQuestion);
      //  textQuestion.setText("Do you have any questions?");

        hasRated = false;
        submitQuestionButton = (Button)view.findViewById(R.id.btn_SubmitQuestion);
        question = (EditText)view.findViewById(R.id.txt_question);
        question.setHint("Ask a question");
        submitOK = (TextView)view.findViewById(R.id.lbl_submitOK);
        submitOK.setText(null);
        //showQuestions = (ListView)view.findViewById(R.id.list_questions);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar_understanding);

        //Listening for changes in rating
        addListenerOnRatingBar();

        //Listening for buttonClicks
        addOnClickListnerToSubmitButton();

        //Setting up the recycle view showing the list of questions
        rec_Questions = (RecyclerView) view.findViewById(R.id.rec_list_questions);
        rec_QPF_manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rec_Questions.setLayoutManager(rec_QPF_manager);
        rec_QPF_adapter = new QuestionAdapter(questionsAtTopicID, getActivity().getLayoutInflater());
        rec_Questions.setAdapter(rec_QPF_adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rec_Questions.getContext(),
                rec_QPF_manager.getOrientation());
        rec_Questions.addItemDecoration(dividerItemDecoration);


        //Periodical update? WHAT TODO
       /* ScheduledThreadPoolExecutor sch = (ScheduledThreadPoolExecutor)
                Executors.newScheduledThreadPool(5);
        Runnable periodicalUpdate = new Runnable(){
            @Override
            public void run() {
                try{
                    //Thread.sleep(10 * 1000);
                    questionUpdate();
                }catch(Exception e){
                }
            }
        };
        ScheduledFuture<?> delayFuture = sch.scheduleWithFixedDelay(periodicalUpdate, 10, 10, TimeUnit.SECONDS);
        */
       sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
       editor = sharedPref.edit();



        staus = sharedPref.getInt("Rating:"+topicID, 0);

        ratingBar.setRating(staus);

       return view;
    }

    /*
    @Override

    public void onResume()
    {
        super.onResume();
        questionUpdate();
    }
    */
    private void addListenerOnRatingBar() {
        //listening for changes in rating
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //showing rating value in testtext display and sends rating to database
                staus = Math.round(rating);
                String errorMessage;
                editor.putInt("Rating:"+topicID,staus);
                editor.commit();
                if (hasRated==false){
                    errorMessage = Database.setRating(topicID,Math.round(rating),LectureActivity.getUserID());
                } else if (hasRated == true){
                    errorMessage = Database.updateRating(topicID,Math.round(rating),LectureActivity.getUserID());
                }
                hasRated = true;

                //testShowRating.setText(errorMessage);
            }
        });
    }

    private void addOnClickListnerToSubmitButton(){
        submitQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionString = question.getText().toString();
                question.setText("Processing...");
                /// /submitOK.setText("Processing...");
                //String length validation
                if (isQuestionValid(questionString)){
                    final String errorMessage = Database.sendQuestion(topicID,questionString,LectureActivity.getUserID());
                    //delay stopping button from being clickable for 4 seconds while "processing"
                    submitQuestionButton.setEnabled(false);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            submitOK.setText(errorMessage);
                            question.setText(null);
                            submitQuestionButton.setEnabled(true);
                            questionUpdate();

                        }
                    },1000);
                }else{
                    question.setText(null);
                }
            }
        });
    }

    public void questionUpdate() {
        questionsAtTopicID.clear();
        questionsAtTopicID.addAll(SwipeAdapter.getQuestionsAtTopic(Database.getAllQuestions(),topicID));
        rec_QPF_adapter.notifyDataSetChanged();
    }

    private boolean isQuestionValid(String questionString){
        //validates that the string is longer than 2 characters. and displays invalid questions statment if false.
        int lengthOfString = questionString.length();

        if (lengthOfString>2){
            question.setHint("Ask a new question");
            return true;
        } else {
            question.setHint("Write sommething..");
            /*"Invalid question, try again."*/
            submitOK.setText("Question not submitted...");
            return false;
        }
    }

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

/*
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