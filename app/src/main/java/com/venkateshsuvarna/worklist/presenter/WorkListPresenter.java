package com.venkateshsuvarna.worklist.presenter;

import android.util.Log;

import com.venkateshsuvarna.worklist.model.WorkListItem;
import com.venkateshsuvarna.worklist.view.IWorkListView;

import java.util.ArrayList;

public class WorkListPresenter implements IWorkListPresenter{

    IWorkListView workListView;
    WorkListAdapter workListAdapter;

    public WorkListPresenter(IWorkListView workListView){
        this.workListView = workListView;
        workListAdapter = new WorkListAdapter(WorkListItem.workListItemArrayList);
    }

    @Override
    public void addTaskToList(String taskTitle, String taskDescription, String taskDayTime){
        WorkListItem.addItemInTodoList(taskTitle,taskDescription,taskDayTime);
        getList();
    }

    @Override
    public void removeTaskFromList(String taskTitle, String taskDescription, String taskDayTime){

        Log.d("RemoveTask","WorkListPresenter - Title :"+taskTitle);
        Log.d("RemoveTask","WorkListPresenter - Description :"+taskDescription);
        Log.d("RemoveTask","WorkListPresenter - DayTime :"+taskDayTime);

        WorkListItem.removeItemInToDoList(taskTitle,taskDescription,taskDayTime);
        getList();
    }

    @Override
    public void getList() {
        workListView.displayList(WorkListItem.workListItemArrayList);
    }

}
