package com.dailylist.dailylist.controller;

import javafx.beans.Observable;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    ObservableList<Task> defaultTasks = FXCollections.observableArrayList();
    ;
    ObservableList<Task> taskList = FXCollections.observableArrayList();
    ;

    public void initialize() {
        configureHourChoiceBox();
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

    private void configureHourChoiceBox() {
        for (LocalTime t : TimeSchemas.HOUR2.getTimeList()) {
            hourChoiceBox.getItems().add(t);
        }
    }

    private void setDefaultTime() {
        for (LocalTime t : TimeSchemas.HOUR2.getTimeList()) {
            defaultTasks.add(new Task(t, ""));
            taskList.add(new Task(t, ""));
        }
        planTable.setItems(defaultTasks);
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

        planTable.getItems()
                .stream()
                .filter(task -> task.getTime().equalsIgnoreCase(hourChoiceBox.getValue().toString()))
                .forEach(task -> task.setDescription(todoTextArea.getText()));
        planTable.refresh();
        hourAndTodoVBox.setVisible(false);
    }

    public void prevPage(){
        ViewSwitcher.switchTo(View.MENU);
    }

}


