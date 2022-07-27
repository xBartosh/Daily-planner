package com.dailylist.dailylist.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    List<String> tableNames = new ArrayList();
    List<String> tableDates = new ArrayList();

    List<String> tableDays = new ArrayList<>();

    ObservableList<Plan> plans = FXCollections.observableArrayList();
    FilteredList<Plan> filteredPlans;

    public void initialize() throws SQLException {
        new DatabaseConnection();
        configureNameColumn();
        configureDateColumn();
        configureDayColumn();
        selectDataFromDatabaseAndAddToLists();
        addItemsToPlans();
        searchListener();
        filteredPlans = new FilteredList<>(FXCollections.observableList(plans));
        plansTableView.setItems(filteredPlans);
        plansTableView.refresh();
    }

    @FXML
    void onTableClicked(MouseEvent event) throws SQLException {
        if (event.getClickCount() == 2){
            if(plansTableView.getSelectionModel().getSelectedItem() != null) {
                JustPlanController.tableName = plansTableView.getSelectionModel().getSelectedItem().getName();
                JustPlanController.date = plansTableView.getSelectionModel().getSelectedItem().getDate();
                JustPlanController.day = plansTableView.getSelectionModel().getSelectedItem().getDay();
                ViewSwitcher.switchTo(View.JUSTPLAN);
                if (JustPlanController.tasks.isEmpty())
                    JustPlanController.insertDataIntoTable();
            }
        }
    }

    private void selectDataFromDatabaseAndAddToLists() throws SQLException {
        DatabaseMetaData metaData = DatabaseConnection.getConnection().getMetaData();
        String[] types = {"TABLE"};
        ResultSet tables = metaData.getTables(null, null, "%", types);
        Statement statement = DatabaseConnection.getConnection().createStatement();

        while (tables.next()) {
            if (!tables.getString("TABLE_NAME").equals("sys_config")) {
                tableNames.add(tables.getString("TABLE_NAME"));
                ResultSet resultSet = statement.executeQuery("SELECT date FROM " + tables.getString("TABLE_NAME") + " WHERE date IS NOT NULL;");
                while (resultSet.next()) {
                    tableDates.add(resultSet.getString(1));
                }
            }
        }

        getDayFromDateAndAddToList();
    }

    private void getDayFromDateAndAddToList(){
        String[] sub;
        LocalDate localDate;
        for (String s: tableDates) {
            sub = s.split("-");
            localDate = LocalDate.of(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]), Integer.parseInt(sub[2]));
            tableDays.add(localDate.getDayOfWeek().toString());
        }
    }

    private void addItemsToPlans(){
        for (int i = 0; i < tableNames.size(); i++) {
            plans.add(new Plan(tableNames.get(i), tableDates.get(i), tableDays.get(i)));
        }
    }

    private void configureNameColumn() {
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
    }

    private void configureDateColumn() {
        dateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
    }
    private void configureDayColumn(){
        dayColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDay()));
    }

    private void searchListener(){
        searchTextField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredPlans.setPredicate(createPredicate(newValue)));
    }

    private Predicate<Plan> createPredicate(String searchText) {
        return plan -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsPlan(plan, searchText);
        };
    }

    private boolean searchFindsPlan(Plan plan, String searchText){
        return (plan.getName().toLowerCase().contains(searchText.toLowerCase())) ||
                (plan.getDate().toLowerCase().contains(searchText.toLowerCase()));
    }

}

