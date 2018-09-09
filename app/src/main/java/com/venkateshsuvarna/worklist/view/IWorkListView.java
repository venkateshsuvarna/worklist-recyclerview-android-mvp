package com.venkateshsuvarna.worklist.view;

import com.venkateshsuvarna.worklist.model.WorkListItem;
import com.venkateshsuvarna.worklist.presenter.WorkListAdapter;

import java.util.ArrayList;

public interface IWorkListView {
    void displayList(ArrayList<WorkListItem> workListItem);
}
