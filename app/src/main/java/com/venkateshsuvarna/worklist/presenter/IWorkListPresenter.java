package com.venkateshsuvarna.worklist.presenter;

import com.venkateshsuvarna.worklist.model.WorkListItem;

import java.util.ArrayList;

public interface IWorkListPresenter {
    void addTaskToList(String taskTitle, String taskDescription, String taskDayTime);
    void removeTaskFromList(String taskTitle, String taskDescription, String taskDayTime);
    void getList();
}
