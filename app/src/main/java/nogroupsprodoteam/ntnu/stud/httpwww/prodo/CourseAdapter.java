package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Christian on 29.03.2017.
 */

class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {
    private List<Course> courses;
    private final LayoutInflater layoutInflater;

    static ClickListener clickListener;

    CourseAdapter(List<Course> courses, LayoutInflater layoutInflater) {
        this.courses = courses;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.course_row, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        holder.courseNameTextView.setText(courses.get(position).getCourseName());
        holder.courseCodeTextView.setText(courses.get(position).getCourseCode());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setClickListener(CourseAdapter.ClickListener clickListener){
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
}
