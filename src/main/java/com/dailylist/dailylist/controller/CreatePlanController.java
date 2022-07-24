package com.dailylist.dailylist.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CreatePlanController {

    @FXML
    private Button addButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label dayOfTheWeek;

    @FXML
    private VBox hourAndTodoVBox;

    @FXML
    private ChoiceBox<LocalTime> hourChoiceBox;

    @FXML
    private Label hourLabel;

    @FXML
    private VBox hourVBox;

    @FXML
    private Label infoLabel;

    @FXML
    private VBox mainVBox;

    @FXML
    private AnchorPane menuAnchorPane;

    @FXML
    private VBox menuVBox;

    @FXML
    private AnchorPane planAnchorPane;

    @FXML
    private TableView<Task> planTable;

    @FXML
    private HBox sceneHBox;

    @FXML
    private TableColumn<Task, String> timeColumn;

    @FXML
    private TableColumn<Task, String> todoColumn;

    @FXML
    private Label todoLabel;

    @FXML
    private TextArea todoTextArea;

    @FXML
    private Button submitButton;

    @FXML
    private Button prevButton;

    @FXML
    private MenuItem hour1MenuItem;

    @FXML
    private MenuItem hour2MenuItem;

    @FXML
    private MenuItem hour3MenuItem;

    @FXML
    private MenuItem hour6MenuItem;

    @FXML
    private MenuButton timeMenuButton;

    @FXML
    StackPane tableStackPane;
    @FXML
    Button saveButton;
    Map<TimeSchema, TaskTable> hours = new EnumMap<>(TimeSchema.class);

    public void initialize() {
        createHours();
        configureHourChoiceBox(TimeSchema.HOUR2);
        configureDayOfTheWeek();
        configureTimeColumn();
        configureTodoColumn();
        setDefaultTime();
    }

    private void configureDayOfTheWeek() {
        dayOfTheWeek.setText(LocalDate.now().getDayOfWeek().toString());
    }

    private void configureTimeColumn() {
        timeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTime()));
    }

    private void configureTodoColumn() {
        todoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
    }

    private void configureHourChoiceBox(TimeSchema timeSchema) {
        if (!hourChoiceBox.getItems().isEmpty())
            hourChoiceBox.getItems().clear();
        for (LocalTime t : timeSchema.getTimeList()) {
            hourChoiceBox.getItems().add(t);
        }
    }

    private void setDefaultTime() {
        TaskTable taskTable = new TaskTable(TimeSchema.HOUR2);
        planTable.setItems(taskTable.getTasks());
    }

    public void getDate() {
        dayOfTheWeek.setText(datePicker.getValue()
                .getDayOfWeek()
                .toString());
    }

    public void addTask() {
        hourAndTodoVBox.setVisible(true);
    }

    public void submitTask() {
        if(!todoTextArea.getText().isEmpty() && !hourChoiceBox.getValue().toString().isEmpty()){
            planTable.getItems()
                    .stream()
                    .filter(task -> task.getTime().equalsIgnoreCase(hourChoiceBox.getValue().toString()))
                    .forEach(task -> task.setDescription(todoTextArea.getText()));
            planTable.refresh();
            resetAddingValues();
        }
    }

    private void resetAddingValues() {
        hourAndTodoVBox.setVisible(false);
        hourChoiceBox.setValue(null);
        todoTextArea.setText("");
    }

    private void createHours() {
        hours.put(TimeSchema.HOUR1, new TaskTable(TimeSchema.HOUR1));
        hours.put(TimeSchema.HOUR2, new TaskTable(TimeSchema.HOUR2));
        hours.put(TimeSchema.HOUR3, new TaskTable(TimeSchema.HOUR3));
        hours.put(TimeSchema.HOUR6, new TaskTable(TimeSchema.HOUR6));
    }

    public void onHour1() {
        setHourDiff(hours.get(TimeSchema.HOUR1));
        configureHourChoiceBox(TimeSchema.HOUR1);
    }

    public void onHour2() {
        setHourDiff(hours.get(TimeSchema.HOUR2));
        configureHourChoiceBox(TimeSchema.HOUR2);
    }

    public void onHour3() {
        setHourDiff(hours.get(TimeSchema.HOUR3));
        configureHourChoiceBox(TimeSchema.HOUR3);
    }

    public void onHour6() {
        setHourDiff(hours.get(TimeSchema.HOUR6));
        configureHourChoiceBox(TimeSchema.HOUR6);
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

    public void onSavePlan(){
       
    }

    public void prevPage() {
        ViewSwitcher.switchTo(View.MENU);
    }
}


