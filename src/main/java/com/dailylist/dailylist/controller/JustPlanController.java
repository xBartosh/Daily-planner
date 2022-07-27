package com.dailylist.dailylist.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ParameterMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

public class JustPlanController {

    @FXML
    private Label dateLabel;

    @FXML
    private Label dayLabel;

    @FXML
    private Label nameLabel;
    @FXML
    private TableView<Task> planTable;

    @FXML
    private Button prevButton;

    @FXML
    private TableColumn<Task, String> timeColumn;

    @FXML
    private TableColumn<Task, String> todoColumn;

    static String tableName;
    static String date;
    static String day;
    static ObservableList<Task> tasks = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        configureTimeColumn();
        configureTodoColumn();
        configureLabels();
        insertDataIntoTable();
        planTable.setItems(tasks);
    }

    @FXML
    void prevPage(ActionEvent event) throws SQLException {
        ViewSwitcher.switchTo(View.YOURPLANS);
        tasks.clear();
    }

    private void configureTimeColumn() {
        timeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTime()));
    }

    private void configureTodoColumn() {
        todoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
    }

    private void configureLabels(){
        configureDateLabel();
        configureNameLabel();
        configureDayLabel();
    }

    private void configureDateLabel(){
        dateLabel.setText(date);
    }

    private void configureNameLabel(){
        nameLabel.setText(tableName);
    }
    private void configureDayLabel(){
        dayLabel.setText(day);
    }

    public static void insertDataIntoTable() throws SQLException {
        new DatabaseConnection();
        Statement statement = DatabaseConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT time, todo FROM " + tableName + " WHERE date IS NULL");
        while (resultSet.next()){
            String[] split = resultSet.getString(1).split(":");
            int hour = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);
            String desc = resultSet.getString(2);
            tasks.add(new Task(LocalTime.of(hour, minute), desc));
        }
    }
}
