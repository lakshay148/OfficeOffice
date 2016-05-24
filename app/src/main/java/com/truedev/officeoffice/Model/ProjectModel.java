package com.truedev.officeoffice.Model;

import java.util.ArrayList;

/**
 * Created by dipanshugarg on 23/5/16.
 */
public class ProjectModel {
    String project;
    String date;
    ArrayList<String> task;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getTask() {
        return task;
    }

    public void setTask(ArrayList<String> task) {
        this.task = task;
    }
}