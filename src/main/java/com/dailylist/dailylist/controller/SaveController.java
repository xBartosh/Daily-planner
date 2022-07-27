package com.dailylist.dailylist.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.sql.Statement;

public class SaveController {

    @FXML
    private Button cancelButton;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button saveButton;

    @FXML
    void onCancel(ActionEvent event) {
        SaveView.closeStage();
    }

    @FXML
    void onSave(ActionEvent event) {
        new DatabaseConnection();
        if (createTableQuery()) {
            createInsertIntoQuery();
            SaveView.closeStage();
        }
    }

    private boolean createTableQuery() {
        if (nameTextField.getText().isEmpty() || nameTextField.getText().equalsIgnoreCase("table")) {
            nameTextField.setText("");
            createAndShowAlertEmptyOrKeyWord();
            return false;
        } else {
            String createTable = "CREATE TABLE " + nameTextField.getText() + " (time VARCHAR(5), todo VARCHAR(45), date VARCHAR(10));";
            return createTableStatement(createTable);
        }

    }

    private boolean createTableStatement(String createTable) {
        try {
            Statement statement = DatabaseConnection.getConnection().createStatement();
            statement.execute(createTable);
            return true;
        } catch (SQLException e) {
            createAndShowAlertBusyName();
            return false;
        }
    }

    private void createInsertIntoQuery() {
        try {

            Statement statement = DatabaseConnection.getConnection().createStatement();
            statement.execute("INSERT INTO " + nameTextField.getText() + " (date) VALUES('" + CreatePlanController.actualDate +"');");
            for (int i = 0; i < CreatePlanController.hours.get(CreatePlanController.actualTimeSchema).getTasks().size(); i++) {
                String timeValue = createTimeValueQuery(i);
                String todoValue = createTodoValueQuery(i);
                todoValue = correctStrings(todoValue);

                statement.execute("INSERT INTO " + nameTextField.getText() + " (time, todo) "
                        + "VALUES (" + timeValue + ", " + todoValue + ");");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private String correctStrings(String string) {
        string = string.trim();
        string = string.replace('.', ' ');
        string = string.replace('\\', ' ');

        return string;
    }

    private String createTimeValueQuery(int index) {
        return "'" + CreatePlanController.hours.get(CreatePlanController.actualTimeSchema).getTasks().get(index).getTime() + "'";
    }

    private String createTodoValueQuery(int index) {
        return "'" + CreatePlanController.hours.get(CreatePlanController.actualTimeSchema).getTasks().get(index).getDescription() + "'";
    }

    private void createAndShowAlertEmptyOrKeyWord() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        configureAlertEmptyOrKeyWord(alert);
        alert.show();
    }

    private void configureAlertEmptyOrKeyWord(Alert alert) {
        alert.setHeaderText("Wrong name!");
        alert.setContentText("Cannot be EMPTY or named TABLE");
        alert.setX(MenuView.getScene().getWindow().getX());
    }

    private void createAndShowAlertBusyName() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        configureAlertBusyName(alert);
        alert.show();
    }


    private void configureAlertBusyName(Alert alert) {
        alert.setHeaderText("Wrong name!");
        alert.setContentText("Name of the table already taken!");
        alert.setX(MenuView.getScene().getWindow().getX());
    }


}

