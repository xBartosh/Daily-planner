package com.dailylist.dailylist.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class YourPlansController {

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<Plan, String> dateColumn;

    @FXML
    private TableColumn<Plan, String> dayColumn;

    @FXML
    private TableColumn<Plan, String> nameColumn;

    @FXML
    private TableView<Plan> plansTableView;

    @FXML
    private Button prevButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    void prevPage(ActionEvent event) {
        ViewSwitcher.switchTo(View.MENU);
    }

    public void initialize() throws SQLException {
        configureNameColumn();
        configureDateColumn();
        configureDayColumn();
        new DatabaseConnection();
        ObservableList<Plan> plans = FXCollections.observableArrayList();

        System.out.println("SELECT create_time FROM INFORMATION_SCHEMA.TABLES" +
                "   WHERE table_schema = 'dailyplanner'" +
                "   AND table_name = 'plannerwtorek';");


        DatabaseMetaData metaData = DatabaseConnection.getConnection().getMetaData();
        String[] types = {"TABLE"};
        ResultSet tables = metaData.getTables(null, null, "%", types);
        Statement statement = DatabaseConnection.getConnection().createStatement();

        List<String> tableNames = new ArrayList();
        List<String> tableDates = new ArrayList();
        int i =0;
        while (tables.next()) {
            tableNames.add(tables.getString("TABLE_NAME"));
            ResultSet resultSet = statement.executeQuery("SELECT create_time FROM INFORMATION_SCHEMA.TABLES" +
                    "   WHERE table_schema = 'dailyplanner'" +
                    "   AND table_name = '"+ tableNames.get(i)+"';");
            while (resultSet.next()){
                tableDates.add(resultSet.getString(1).substring(0,10));
            }
            i++;

            // TO DO
            // SUBSTRING DATE SO YOU CAN GET A DAY FROM IT
            // ADD THIS DATA TO TABLE VIEW
        }
        System.out.println(tableNames);
        System.out.println(tableDates);






    }

    private void configureNameColumn() {
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
    }

    private void configureDateColumn() {
        dateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getData()));
    }
    private void configureDayColumn(){
        dayColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDay()));
    }

}

