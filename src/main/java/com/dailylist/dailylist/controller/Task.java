package com.dailylist.dailylist.controller;

import java.time.LocalTime;

public class Task {
    private String time;
    private String description;

    Task(LocalTime time, String description) {
        this.time = time.toString();
        this.description = description;
    }

    String getTime() {
        return time;
    }

    void setTime(final String time) {
        this.time = time;
    }

    String getDescription() {
        return description;
    }

    void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "time='" + time + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}




