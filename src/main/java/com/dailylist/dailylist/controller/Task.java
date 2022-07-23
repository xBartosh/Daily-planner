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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Task task = (Task) o;

        if (time != null ? !time.equals(task.time) : task.time != null) return false;
        return description != null ? description.equals(task.description) : task.description == null;
    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}




