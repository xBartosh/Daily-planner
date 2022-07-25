package com.dailylist.dailylist.app;

import com.dailylist.dailylist.controller.MenuView;
import com.dailylist.dailylist.controller.View;
import com.dailylist.dailylist.controller.ViewSwitcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.Locale;

public class DailyList extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        Locale.setDefault(Locale.ENGLISH);
        stage.setTitle("Daily Plan");
        stage.setResizable(false);
        stage.setScene(new MenuView().getScene());
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}