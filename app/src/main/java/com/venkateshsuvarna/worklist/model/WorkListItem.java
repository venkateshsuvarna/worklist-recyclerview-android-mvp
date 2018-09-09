package com.venkateshsuvarna.worklist.model;

import android.util.Log;

import java.util.ArrayList;

public class WorkListItem implements IWorkListItem{

    public String taskTitle;
    public String taskDescription;
    public String taskDayTime;

    public static ArrayList<WorkListItem> workListItemArrayList = new ArrayList<>();

    public static int getNumberOfItems() {
        return numberOfItems;
    }

    public static void setNumberOfItems(int numberOfItems) {
        WorkListItem.numberOfItems = numberOfItems;
    }

    public static int numberOfItems = 0;

    public WorkListItem(String taskTitle,String taskDescription,String taskDayTime){
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskDayTime = taskDayTime;
    }

    public String getTaskTitle(){
        return this.taskTitle;
    }
    public String getTaskDescription(){
        return this.taskDescription;
    }
    public String getTaskDayTime(){
        return this.taskDayTime;
    }

    public static void displayList() {
        Log.d("PrintList","Start of Printing List");
        for(WorkListItem workItem : workListItemArrayList){
            Log.d("PrintList","Work Item Title"+workItem.taskTitle);
            Log.d("PrintList","Work Item Description"+workItem.taskDescription);
            Log.d("PrintList","Work Item DayTime"+workItem.taskDayTime);
        }
        Log.d("PrintList","End of Printing List");
    }

    public static ArrayList<WorkListItem> addItemInTodoList(String taskTitle,String taskDescription,String taskDayTime){

        Log.d("AddTask","Title in Model = "+taskTitle);
        Log.d("AddTask","Description in Model = "+taskDescription);
        Log.d("AddTask","Task DayTime in Model = "+taskDayTime);

        workListItemArrayList.add(new WorkListItem(taskTitle,taskDescription,taskDayTime));
        Log.d("AddTask","No of items = "+numberOfItems);
        numberOfItems++;
        Log.d("AddTask","No of items = "+numberOfItems);
        displayList();
        return workListItemArrayList;
    }

    public static ArrayList<WorkListItem> removeItemInToDoList(String taskTitle,String taskDescription,String taskDayTime){

        Log.d("RemoveTask","WorkListItem - Title :"+taskTitle);
        Log.d("RemoveTask","WorkListItem - Description :"+taskDescription);
        Log.d("RemoveTask","WorkListItem - DayTime :"+taskDayTime);


        ArrayList<WorkListItem> foundItem = new ArrayList<>();
        for(WorkListItem item : workListItemArrayList){
            if(item.getTaskTitle().equals(taskTitle) && item.getTaskDescription().equals(taskDescription)
                    && item.getTaskDayTime().equals(taskDayTime)){
                foundItem.add(item);
            }
        }
        workListItemArrayList.removeAll(foundItem);

        displayList();
        return workListItemArrayList;
    }

}
