package com.venkateshsuvarna.worklist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.venkateshsuvarna.worklist.model.WorkListItem;
import com.venkateshsuvarna.worklist.presenter.IWorkListPresenter;
import com.venkateshsuvarna.worklist.presenter.WorkListAdapter;
import com.venkateshsuvarna.worklist.presenter.WorkListPresenter;
import com.venkateshsuvarna.worklist.view.IWorkListView;
import com.venkateshsuvarna.worklist.view.RecyclerItemListener;
import com.venkateshsuvarna.worklist.view.VerticalSpacingDecoration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WorkListActivity extends AppCompatActivity implements IWorkListView{

    IWorkListPresenter workListPresenter;
    FloatingActionButton fabAddItem;
    FloatingActionButton fabRefreshList;

    RecyclerView recyclerViewWorkList;
    WorkListAdapter workListAdapter;

    final Context mContext = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worklist);

        workListPresenter = new WorkListPresenter(this);
        fabAddItem = findViewById(R.id.fab_add_item);
        fabRefreshList = findViewById(R.id.fab_refresh_list);
        recyclerViewWorkList = findViewById(R.id.recyclerViewWorkList);
        recyclerViewWorkList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewWorkList.addItemDecoration(new VerticalSpacingDecoration(64));

        recyclerViewWorkList.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(), recyclerViewWorkList,
                new RecyclerItemListener.RecyclerTouchListener() {
                    public void onClickItem(View v, int position) {
                        //Do Something Else
                    }

                    public void onLongClickItem(View v, int position) {
                        TextView tvWorkListItemTitle = v.findViewById(R.id.tv_worklist_item_title);
                        TextView tvWorkListItemDescription = v.findViewById(R.id.tv_worklist_item_description);
                        TextView tvWorkListItemDayTime = v.findViewById(R.id.tv_worklist_item_daytime);

                        Log.d("RemoveTask","WorkListActivity - Title :"+tvWorkListItemTitle.getText().toString());
                        Log.d("RemoveTask","WorkListActivity - Description :"+tvWorkListItemDescription.getText().toString());
                        Log.d("RemoveTask","WorkListActivity - DayTime :"+tvWorkListItemDayTime.getText().toString());


                        //Toast.makeText(getApplicationContext(),tvWorkListItemTitle.getText().toString(),Toast.LENGTH_SHORT).show();

                        workListPresenter.removeTaskFromList(tvWorkListItemTitle.getText().toString(),
                                tvWorkListItemDescription.getText().toString(),tvWorkListItemDayTime.getText().toString());

                    }
                }));

        workListAdapter = new WorkListAdapter(WorkListItem.workListItemArrayList);

        fabRefreshList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayList(WorkListItem.workListItemArrayList);
            }
        });

        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View dialogView = View.inflate(mContext, R.layout.add_task_layout, null);
                final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();

                dialogView.findViewById(R.id.btn_add_task).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EditText etTaskTitle = dialogView.findViewById(R.id.et_task_title);
                        EditText etTaskDescription = dialogView.findViewById(R.id.et_task_description);
                        DatePicker datePicker = dialogView.findViewById(R.id.date_picker);
                        TimePicker timePicker = dialogView.findViewById(R.id.time_picker);

                        Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                                datePicker.getMonth(),
                                datePicker.getDayOfMonth(),
                                timePicker.getCurrentHour(),
                                timePicker.getCurrentMinute());

                        Log.d("AddTask","Title = "+etTaskTitle.getText().toString());
                        Log.d("AddTask","Description = "+etTaskDescription.getText().toString());
                        Log.d("AddTask","DayTime = "+calendar.getTime().toString());


                        workListPresenter.addTaskToList(etTaskTitle.getText().toString(),
                                etTaskDescription.getText().toString(),calendar.getTime().toString());

                        alertDialog.dismiss();
                    }});
                alertDialog.setView(dialogView);
                alertDialog.show();
            }
        });

    }

    @Override
    public void displayList(ArrayList<WorkListItem> workListItem) {
        Log.d("AddTask","Display List Called");
        workListAdapter = null;
        workListAdapter = new WorkListAdapter(workListItem);
        recyclerViewWorkList.setAdapter(workListAdapter);
        workListAdapter.notifyDataSetChanged();

        if(WorkListItem.numberOfItems == 0){
            TextView tvNoItems = findViewById(R.id.tv_noitems);
            tvNoItems.setVisibility(View.VISIBLE);
        }
        else{
            TextView tvNoItems = findViewById(R.id.tv_noitems);
            tvNoItems.setVisibility(View.GONE);
        }

    }
}
