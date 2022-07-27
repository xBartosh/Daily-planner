package com.dailylist.dailylist.controller;

class Plan {
    private String name;
    private String date;
    private String day;

    Plan(String name, String date, String day) {
        this.name = name;
        this.date = date;
        this.day = day;
    }

    String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    String getDate() {
        return date;
    }

    void setDate(final String date) {
        this.date = date;
    }

    String getDay() {
        return day;
    }

    void setDay(final String day) {
        this.day = day;
    }
}
