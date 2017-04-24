package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by thea_ on 30.03.2017.
 */

class QuestionViewHolder extends RecyclerView.ViewHolder {


    public final TextView questionTextView;
    public final TextView answerTextView;
    public final TextView scoreTextView;
    public final ImageButton endorseButtonUp;
    public final ImageButton endorseButtonDown;

    public QuestionViewHolder(View itemView) {
        super(itemView);
        this.questionTextView = (TextView) itemView.findViewById(R.id.lbl_question);
        this.answerTextView = (TextView) itemView.findViewById(R.id.lbl_answer);
        this.scoreTextView = (TextView) itemView.findViewById(R.id.lbl_score);
        this.endorseButtonDown = (ImageButton) itemView.findViewById(R.id.imgbtn_down);
        this.endorseButtonUp = (ImageButton) itemView.findViewById(R.id.imgbtn_up);
    }
}