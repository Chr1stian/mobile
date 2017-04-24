package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Christian on 21.02.2017.
 */

public class SwipeAdapter extends FragmentStatePagerAdapter {
    Button swipeleft;


    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //creates fragment
        Fragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        //gets topicID and topic that corresponds to the current number of swipable fragment
        ArrayList<Topic> topicsFromSelectedLecture = LectureActivity.getTopicsFromSelectedLecture();
        ArrayList<Question> questionArrayList = LectureActivity.getQuestionArrayList();


        ArrayList<Topic> topicAtNumber = new ArrayList<>();
        for( Topic topic : topicsFromSelectedLecture) {
            if (topic.getTopicNumber().equals(Integer.toString(position+1))) {
                topicAtNumber.add(topic);
            }
        }
        Integer topicID = Integer.parseInt(topicAtNumber.get(0).getTopicID());

        ArrayList<Question> questionsAtTopicID = new ArrayList<>();
        for( Question question : questionArrayList) {
            if (question.getTopicID().equals(Integer.toString(topicID))) {
                questionsAtTopicID.add(question);
            }
        }
        String topic = topicAtNumber.get(0).getTopicName();
        bundle.putInt("position", position);
        bundle.putString("topic",topic);
        bundle.putInt("topicID", topicID);
        bundle.putInt("count", getCount());
        bundle.putSerializable("QuestionList", questionsAtTopicID);
        //sends bundle/values to fragment for display
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        //gets number of topics for selected lecture and sets swipeable fragments to its count
        Integer dataFromActivity = LectureActivity.getNumberOfTopics();
        return dataFromActivity;
    }
}
