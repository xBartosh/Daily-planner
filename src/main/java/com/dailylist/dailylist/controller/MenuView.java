package com.dailylist.dailylist.controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MenuView {
    private static Scene scene;

    public MenuView() {
        this.scene = new Scene(new Pane());
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.MENU);

    }

    public static Scene getScene() {
        return scene;
    }
}
