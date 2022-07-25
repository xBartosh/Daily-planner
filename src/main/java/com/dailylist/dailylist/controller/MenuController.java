package com.dailylist.dailylist.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    private Button createPlanButton;

    @FXML
    private Button yourPlansButton;


    @FXML
    public void onCreatePlanButton(){
        ViewSwitcher.switchTo(View.CREATEPLAN);
    }

    @FXML
    public void onYourPlansButton(){
        ViewSwitcher.switchTo(View.YOURPLANS);
    }
}
