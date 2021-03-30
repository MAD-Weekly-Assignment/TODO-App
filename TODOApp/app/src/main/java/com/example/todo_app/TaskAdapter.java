

package com.example.todo_app;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todo_app.data.Task;

import java.util.List;



public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Task> mTasks;
	private static ClickListener clickListener;

    TaskAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mTasks != null) {
            Task current = mTasks.get(position);
            holder.wordItemView.setText(current.getWord());
        } else {

            holder.wordItemView.setText("");
        }
    }


    void setWords(List<Task> tasks) {
        mTasks = tasks;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mTasks != null)
            return mTasks.size();
        else return 0;
    }


    public Task getWordAtPosition(int position) {
        return mTasks.get(position);
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        TaskAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }

}
