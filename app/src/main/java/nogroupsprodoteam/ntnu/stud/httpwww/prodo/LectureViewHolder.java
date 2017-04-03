package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import static nogroupsprodoteam.ntnu.stud.httpwww.prodo.LectureAdapter.clickListener;

/**
 * Created by thea_ on 30.03.2017.
 */

class LectureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final TextView lectureNameTextView;
    public final TextView lectureCodeTextView;
    public LectureViewHolder(View itemView) {
        super(itemView);
        this.lectureNameTextView = (TextView) itemView.findViewById(R.id.lectureName);
        this.lectureCodeTextView = (TextView) itemView.findViewById(R.id.lectureCode);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(clickListener!=null){
            clickListener.itemClicked(v, getAdapterPosition());
        }
    }
}
