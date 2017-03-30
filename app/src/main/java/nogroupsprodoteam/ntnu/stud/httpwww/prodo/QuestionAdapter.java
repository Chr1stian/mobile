package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thea_ on 30.03.2017.
 */

class QuestionAdapter extends RecyclerView.Adapter<QuestionViewHolder> {
    private List<Question> questions;
    private final LayoutInflater layoutInflater;

    public QuestionAdapter(List<Question> questions, LayoutInflater layoutInflater) {
        this.questions = questions;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.question_row, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {
        holder.questionTextView.setText(questions.get(position).getQuestion());
        holder.answerTextView.setText(questions.get(position).getAnswer());
        holder.endorseButton.setText("test");
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
