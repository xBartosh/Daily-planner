package com.dailylist.dailylist.controller;

class Plan {
    private String name;
    private String data;
    private String day;

    Plan(String name, String data, String day) {
        this.name = name;
        this.data = data;
        this.day = day;
    }

    String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    String getData() {
        return data;
    }

    void setData(final String data) {
        this.data = data;
    }

    String getDay() {
        return day;
    }

    void setDay(final String day) {
        this.day = day;
    }
}
