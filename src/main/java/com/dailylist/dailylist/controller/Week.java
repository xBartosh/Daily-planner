package com.dailylist.dailylist.controller;

public enum Week {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    String dayOfTheWeek;

    Week(final String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

}
