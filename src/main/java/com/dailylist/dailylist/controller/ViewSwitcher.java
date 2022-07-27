package com.dailylist.dailylist.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewSwitcher {

    private static Map<View, Parent> cache = new HashMap<>();

    private static Scene scene;

    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    public static void switchTo(View view) {
        if (scene == null) {
            System.out.println("No scene was set");
            return;
        }

        try {
            Parent root;

            if (cache.containsKey(view)) {
                root = cache.get(view);
            } else {
                root = FXMLLoader.load(
                        ViewSwitcher.class.getResource(view.getFileName())
                );

                cache.put(view, root);
            }

            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
