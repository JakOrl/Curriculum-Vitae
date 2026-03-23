/**
 * The core module for the DIY Pong Game.
 * This module handles the MVC architecture, including physics models,
 * JavaFX views, and input controllers.
 */

module com.example.Pong_Source {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.almasb.fxgl.all;
    requires java.desktop;


    requires org.junit.jupiter.api;
    requires org.junit.platform.commons;


    opens Pong_Source.Models to org.junit.platform.commons;

    exports Pong_Source.Controller;
    exports Pong_Source.Models;
    exports Pong_Source.View;
}