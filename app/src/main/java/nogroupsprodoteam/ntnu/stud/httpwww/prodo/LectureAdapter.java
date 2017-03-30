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

class LectureAdapter extends RecyclerView.Adapter<LectureViewHolder> {
    private List<Lecture> lectures;
    private final LayoutInflater layoutInflater;

    public LectureAdapter(ArrayList<Lecture> lectures, LayoutInflater layoutInflater) {
        this.lectures = lectures;
        this.layoutInflater = layoutInflater;
    }


    @Override
    public LectureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.course_row, parent, false);
        return new LectureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LectureViewHolder holder, int position) {
        holder.lectureCodeTextView.setText(lectures.get(position).getLectureNumber());
        holder.lectureNameTextView.setText(lectures.get(position).getLectureName());
    }

    @Override
    public int getItemCount() {
        return lectures.size();
    }
}
