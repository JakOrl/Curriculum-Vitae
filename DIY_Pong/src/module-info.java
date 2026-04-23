/**
 * The core module for the DIY Pong Game.
 * This module handles the MVC architecture, including physics models,
 * JavaFX views, and input controllers.
 */

module com.example.Pong_Source {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.junit.jupiter.api;
    requires java.sql;
    requires mysql.connector.j;

    opens Pong_Source.Controller to javafx.graphics, javafx.fxml;

    exports Pong_Source.Controller;
    exports Pong_Source.View;
    exports Pong_Source.Models;
    // Add this for the new package we are creating
    // exports Pong_Source.Services;
}