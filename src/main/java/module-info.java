module com.dailylist.dailylist {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;

    exports com.dailylist.dailylist.controller;
    opens com.dailylist.dailylist.controller to javafx.fxml;
    exports com.dailylist.dailylist.app;
    opens com.dailylist.dailylist.app to javafx.fxml;
}