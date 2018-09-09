package com.venkateshsuvarna.worklist.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.venkateshsuvarna.worklist.R;
import com.venkateshsuvarna.worklist.model.WorkListItem;

import java.util.List;

public class WorkListAdapter extends
        RecyclerView.Adapter<WorkListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvWorkListItemTitle;
        public TextView tvWorkListItemDescription;
        public TextView tvWorkListItemDayTime;

        public ViewHolder(View itemView) {
            super(itemView);

            tvWorkListItemTitle = itemView.findViewById(R.id.tv_worklist_item_title);
            tvWorkListItemDescription = itemView.findViewById(R.id.tv_worklist_item_description);
            tvWorkListItemDayTime = itemView.findViewById(R.id.tv_worklist_item_daytime);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    String taskTitle = tvWorkListItemTitle.getText().toString();
                    String taskDescription = tvWorkListItemDescription.getText().toString();
                    String taskDayTime = tvWorkListItemDayTime.getText().toString();

                    WorkListItem.removeItemInToDoList(taskTitle,taskDescription,taskDayTime);

                    return true;
                }
            });

        }
    }

    private List<WorkListItem> mWorkListItem;

    public WorkListAdapter(List<WorkListItem> workListItem) {
        mWorkListItem = workListItem;
    }

    @Override
    public WorkListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.worklist_item_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WorkListAdapter.ViewHolder viewHolder, int position) {
        WorkListItem workListItem = mWorkListItem.get(position);

        TextView tvWorkListItemTitle = viewHolder.tvWorkListItemTitle;
        tvWorkListItemTitle.setText(workListItem.getTaskTitle());
        TextView tvWorkListItemDescription = viewHolder.tvWorkListItemDescription;
        tvWorkListItemDescription.setText(workListItem.getTaskDescription());
        TextView tvWorkListItemDayTime = viewHolder.tvWorkListItemDayTime;
        tvWorkListItemDayTime.setText(workListItem.getTaskDayTime());
    }

    @Override
    public int getItemCount() {
        if(mWorkListItem == null){
            return -1;
        }
        else{
            return mWorkListItem.size();
        }
    }
}