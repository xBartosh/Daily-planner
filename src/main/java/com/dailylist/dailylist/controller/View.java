package com.dailylist.dailylist.controller;

public enum View {
    MENU("/fxml/menu-view.fxml"),
    CREATEPLAN("/fxml/create-plan-view.fxml"),

    YOURPLANS("/fxml/your-plans-view.fxml"),
    SAVE("/fxml/save-view.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
