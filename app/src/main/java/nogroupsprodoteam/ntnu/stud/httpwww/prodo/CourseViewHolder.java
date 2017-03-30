package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Christian on 29.03.2017.
 */

class CourseViewHolder extends RecyclerView.ViewHolder {
    public final TextView courseNameTextView;
    public final TextView courseCodeTextView;
    public CourseViewHolder(View itemView) {
        super(itemView);
        this.courseNameTextView = (TextView) itemView.findViewById(R.id.courseName);
        this.courseCodeTextView = (TextView) itemView.findViewById(R.id.courseCode);
    }
}
