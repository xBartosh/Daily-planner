package com.dailylist.dailylist.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CreatePlanController {

    @FXML
    private HBox sceneHBox;

    @FXML
    private AnchorPane planAnchorPane;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label dayOfTheWeek;

    @FXML
    private Button prevButton;

    @FXML
    StackPane tableStackPane;

    @FXML
    private TableView<Task> planTable;

    @FXML
    private TableColumn<Task, String> timeColumn;

    @FXML
    private TableColumn<Task, String> todoColumn;

    @FXML
    private MenuButton timeMenuButton;

    @FXML
    private MenuItem hour1MenuItem;

    @FXML
    private MenuItem hour2MenuItem;

    @FXML
    private MenuItem hour3MenuItem;

    @FXML
    private MenuItem hour6MenuItem;

    @FXML
    Button saveButton;

    @FXML
    Button clearButton;

    TableView<Task> getPlanTable() {
        return planTable;
    }

    static Map<TimeSchema, TaskTable> hours = new EnumMap<>(TimeSchema.class);
    static TimeSchema acutalTimeSchema;
    static LocalDate actualDate;

    public void initialize() {
        createHours();
        configureDayOfTheWeek();
        configureTimeColumn();
        configureTodoColumn();
        setDefaultTime();
    }

    private void configureDayOfTheWeek() {
        dayOfTheWeek.setText(LocalDate.now().getDayOfWeek().toString());
        String[] split = LocalDate.now().toString().split("-");
        actualDate = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }

    private void configureTimeColumn() {
        timeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTime()));
    }

    private void configureTodoColumn() {
        todoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
        todoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        todoColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, String>>() {
            @Override
            public void handle(final TableColumn.CellEditEvent<Task, String> taskStringCellEditEvent) {
                planTable.getItems()
                        .stream()
                        .filter(task -> task.getTime().equals(taskStringCellEditEvent.getRowValue().getTime()))
                        .forEach(task -> {
                            task.setDescription(taskStringCellEditEvent.getNewValue());
                            hours.get(acutalTimeSchema)
                                    .getTasks().stream()
                                    .filter(task1 -> task1.getTime().equals(task.getTime()))
                                    .forEach(task2->task2.setDescription(taskStringCellEditEvent.getNewValue()));
                        });

                planTable.refresh();
            }
        });
    }


    private void setDefaultTime() {
        TaskTable taskTable = new TaskTable(TimeSchema.HOUR2);
        planTable.setItems(taskTable.getTasks());
        acutalTimeSchema = TimeSchema.HOUR2;
    }

    public void getDate() {
        dayOfTheWeek.setText(datePicker.getValue()
                .getDayOfWeek()
                .toString());
        String[] split = datePicker.getValue().toString().split("-");
        actualDate = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        System.out.println(actualDate);
    }

    private void createHours() {
        hours.put(TimeSchema.HOUR1, new TaskTable(TimeSchema.HOUR1));
        hours.put(TimeSchema.HOUR2, new TaskTable(TimeSchema.HOUR2));
        hours.put(TimeSchema.HOUR3, new TaskTable(TimeSchema.HOUR3));
        hours.put(TimeSchema.HOUR6, new TaskTable(TimeSchema.HOUR6));
    }

    public void onHour1() {
        setHourDiff(hours.get(TimeSchema.HOUR1));
        acutalTimeSchema = TimeSchema.HOUR1;
    }

    public void onHour2() {
        setHourDiff(hours.get(TimeSchema.HOUR2));
        acutalTimeSchema = TimeSchema.HOUR2;
    }

    public void onHour3() {
        setHourDiff(hours.get(TimeSchema.HOUR3));
        acutalTimeSchema = TimeSchema.HOUR3;

    }

    public void onHour6() {
        setHourDiff(hours.get(TimeSchema.HOUR6));
        acutalTimeSchema = TimeSchema.HOUR6;
    }

    public void setHourDiff(TaskTable taskTable) {

        planTable.getItems()
                .stream()
                .filter(task -> !task.getDescription().isEmpty())
                .forEach(task ->
                        taskTable.getTasks().stream()
                                .filter(t -> t.getTime().equals(task.getTime()))
                                .forEach(nt -> nt.setDescription(task.getDescription()))
                );
        planTable.setItems(taskTable.getTasks());
        planTable.refresh();
    }

    public void onSavePlan() {
        new SaveView();
    }

    public void prevPage() {
        ViewSwitcher.switchTo(View.MENU);

    }

    public void onClearPlan(){
        planTable.getItems().stream()
                .filter(task->!task.getDescription().isEmpty())
                .forEach(task -> {
                    task.setDescription("");
                });
        hours.get(acutalTimeSchema).getTasks().stream().forEach(task -> task.setDescription(""));
        planTable.refresh();
    }
}


