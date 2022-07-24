package com.dailylist.dailylist.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;

class TaskTable {
    private TimeSchema timeSchema;
    private ObservableList<Task> tasks;

    TaskTable(TimeSchema timeSchema) {
        this.timeSchema = timeSchema;
        createTimeList();

    }

    TimeSchema getTimeSchema() {
        return timeSchema;
    }

    void setTimeSchema(TimeSchema timeSchema) {
        this.timeSchema = timeSchema;
    }

    ObservableList<Task> getTasks() {
        return tasks;
    }

    void setTasks(ObservableList<Task> tasks) {
        this.tasks = tasks;
    }

    public void createTimeList(){
        this.tasks = FXCollections.observableArrayList();
        for (LocalTime t : this.timeSchema.getTimeList()) {
            this.tasks.add(new Task(t, ""));
        }
    }

    public void changeTimeSchema(TimeSchema timeSchema){
        this.timeSchema = timeSchema;
        this.tasks.removeAll();
        createTimeList();

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TaskTable taskTable = (TaskTable) o;

        return timeSchema == taskTable.timeSchema;
    }

    @Override
    public int hashCode() {
        return timeSchema != null ? timeSchema.hashCode() : 0;
    }
}
