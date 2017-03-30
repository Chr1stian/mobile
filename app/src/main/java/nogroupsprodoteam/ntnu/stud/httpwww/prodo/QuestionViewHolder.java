package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by thea_ on 30.03.2017.
 */

class QuestionViewHolder extends RecyclerView.ViewHolder {


    public final TextView questionTextView;
    public final TextView answerTextView;
    public final Button endorseButton;

    public QuestionViewHolder(View itemView) {
        super(itemView);
        this.questionTextView = (TextView) itemView.findViewById(R.id.Question);
        this.answerTextView = (TextView) itemView.findViewById(R.id.Answer);
        this.endorseButton = (Button) itemView.findViewById(R.id.btn_qr);
    }
}