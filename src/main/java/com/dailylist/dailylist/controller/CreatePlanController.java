package com.dailylist.dailylist.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.time.LocalDate;

public class CreatePlanController {

    @FXML
    private Button addButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label dayOfTheWeek;

    @FXML
    private Label infoLabel;

    @FXML
    private AnchorPane menuAnchorPane;

    @FXML
    private VBox menuVBox;

    @FXML
    private AnchorPane planAnchorPane;

    @FXML
    private TableView<?> planTable;

    @FXML
    private Button removeButton;

    @FXML
    private HBox sceneHBox;

    @FXML
    private TableColumn<?, ?> timeColumn;

    @FXML
    private TableColumn<?, ?> todoColumn;

    public void initialize(){
        configureDayOfTheWeek();
    }

    private void configureDayOfTheWeek(){
        dayOfTheWeek.setText(LocalDate.now().getDayOfWeek().toString());
    }

}


