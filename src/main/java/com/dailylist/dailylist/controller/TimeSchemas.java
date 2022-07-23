package com.dailylist.dailylist.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;
import java.util.ArrayList;

enum TimeSchemas {
    HOUR1(1),
    HOUR2(2),
    HOUR3(3),
    HOUR6(6);

    private ObservableList<LocalTime> timeList;
    private long tick;

    TimeSchemas(int tick) {
        this.tick = tick;
        generateTimeSchemaList();

    }

    private void generateTimeSchemaList() {
        LocalTime startingTime = LocalTime.of(6, 0);
        LocalTime actualTime = startingTime;
        this.timeList = FXCollections.observableArrayList();
        int zeroCounter = 0;
        while (zeroCounter!=1){
            if (actualTime.getHour() == 0){
                zeroCounter++;
            }
            this.timeList.add(actualTime);
            actualTime = actualTime.plusHours(this.tick);

        }

    }

    ObservableList<LocalTime> getTimeList() {
        return timeList;
    }

    void setTimeList(final ObservableList<LocalTime> timeList) {
        this.timeList = timeList;
    }

    long getTick() {
        return tick;
    }

    void setTick(final long tick) {
        this.tick = tick;
    }
}
