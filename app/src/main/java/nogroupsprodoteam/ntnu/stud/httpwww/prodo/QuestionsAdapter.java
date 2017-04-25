package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 20.04.2017.
 */

class QuestionsAdapter extends RecyclerView.Adapter<QuestionsViewHolder> {
    private List<Question> questionsWithUserID;
    private final LayoutInflater layoutInflater;
    private ArrayList<Course> courseArrayList;
    private ArrayList<Lecture> lectureArrayList;
    private ArrayList<Topic> topicArrayList;

    public QuestionsAdapter(ArrayList<Question> questionsWithUserID, ArrayList<Course> courseArrayList, ArrayList<Lecture> lectureArrayList, ArrayList<Topic> topicArrayList, LayoutInflater layoutInflater) {
        this.questionsWithUserID = questionsWithUserID;
        this.courseArrayList = courseArrayList;
        this.lectureArrayList = lectureArrayList;
        this.topicArrayList = topicArrayList;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public QuestionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.questions_row, parent, false);
        return new QuestionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionsViewHolder holder, int position) {
        holder.questionTextView.setText(questionsWithUserID.get(position).getQuestion());

        holder.questionsOriginTextView.setText(getQuestionOrigin(position));

        String answer = questionsWithUserID.get(position).getAnswer();

        if (answer == null) {
            holder.answerTextView.setText("Not answered yet..");
        } else {
            holder.answerTextView.setText(answer);
        }
    }

    private String getQuestionOrigin(int position) {
        String origin = "Origin not found";
        String topicID = questionsWithUserID.get(position).getTopicID();

        Topic topicWithID = null;
        for (Topic topic : topicArrayList) {
            if (topic.getTopicID().equals(topicID)) {
                topicWithID = topic;
            }
        }
        String topicname = null;
        if (topicWithID != null) {
            topicname = topicWithID.getTopicName();
        }
        String lectureID = null;
        if (topicWithID != null) {
            lectureID = topicWithID.getLectureID();
        }

        Lecture lectureWithID = null;
        for (Lecture lecture : lectureArrayList) {
            if (lecture.getLectureID().equals(lectureID)) {
                lectureWithID = lecture;
            }
        }
        String lecturename = null;
        if (lectureWithID != null) {
            lecturename = lectureWithID.getLectureName();
        }
        String courseID = null;
        if (lectureWithID != null) {
            courseID = lectureWithID.getCourseID();
        }

        Course courseWithID = null;
        for (Course course : courseArrayList) {
            if (course.getCourseID().equals(courseID)) {
                courseWithID = course;
            }
        }
        String coursename = null;
        if (courseWithID != null) {
            coursename = courseWithID.getCourseName();
        }

        if (courseWithID != null && lectureWithID != null && topicWithID != null){
            origin = coursename + " - " + lecturename + " - " + topicname;
        }
        return origin;
    }

    @Override
    public int getItemCount() {
        return questionsWithUserID.size();
    }
}
