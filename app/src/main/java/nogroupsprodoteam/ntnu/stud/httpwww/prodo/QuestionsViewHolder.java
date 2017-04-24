package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Christian on 20.04.2017.
 */

class QuestionsViewHolder extends RecyclerView.ViewHolder{
    public final TextView questionTextView;
    public final TextView answerTextView;
    public final TextView questionsOriginTextView;

    public QuestionsViewHolder(View itemView) {
        super(itemView);
        this.questionTextView = (TextView) itemView.findViewById(R.id.Question);
        this.answerTextView = (TextView) itemView.findViewById(R.id.Answer);
        this.questionsOriginTextView = (TextView) itemView.findViewById(R.id.QuestionOrigin);
    }
}
