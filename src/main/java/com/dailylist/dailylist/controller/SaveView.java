package com.dailylist.dailylist.controller;

import com.dailylist.dailylist.app.DailyList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

class SaveView {
    private static Stage stage;
    private static Scene scene;


    SaveView() {
        configureScene();
        configureStage();
        stage.show();
    }

    private void configureStage(){
        this.stage = new Stage();
        this.stage.setAlwaysOnTop(true);
        this.stage.setResizable(false);
        this.stage.setScene(this.scene);
    }

    private void configureScene(){
        try {
            this.scene = new Scene(FXMLLoader.load(getClass().getResource(View.SAVE.getFileName())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Stage getStage() {
        return stage;
    }

    public static void closeStage(){
        stage.close();
    }
}
