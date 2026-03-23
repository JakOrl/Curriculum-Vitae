package Pong_Source.Controller;

import Pong_Source.View.Pong_Canvas;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Acts as the event handler for all menu-related actions in the Pong game.
 * <p>
 * This class handles user interactions from the MenuBar, such as changing game rules,
 * adjusting racket dimensions, renaming players, and exiting the application.
 * It utilizes JavaFX {@link TextInputDialog} and {@link Alert} to provide a
 * graphical interface for settings configuration.
 * </p>
 * * @author Jakub Orlowski
 * @version 1.0
 */
public class MenuListener {
    //------------------------------
    // Fields
    //------------------------------
    private Game game;
    private int new_target;
    private int new_speed;
    private Controller controller;
    private Pong_Canvas canvas;
    //------------------------------
    // Constructor
    //------------------------------
    /**
     * Constructs a MenuListener with references to the core game components.
     * @param game       The {@link Game} settings model to be modified.
     * @param controller The {@link Controller} responsible for game state.
     * @param canvas     The {@link Pong_Canvas} for triggering visual refreshes.
     */
    public MenuListener(Game game, Controller controller, Pong_Canvas canvas) {
        this.game = game;
        this.canvas = canvas;
        this.controller = controller;
    }

    //------------------------------
    // Getters / Setters
    //------------------------------

    /**
     * Cleanly exits the JavaFX application.
     */
    public void setExit(){
        System.out.println("Exiting...");
        Platform.exit();
    }

    /**
     * Displays an "About" dialog showing project credits and ownership.
     */
    public void setAbout(){
        System.out.println("Showing About...");
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DIY_Pong");
        alert.setHeaderText("Made in MTU");
        alert.setContentText("Made by: Jakub Orlowski\n All rights reserved.");
        alert.showAndWait().ifPresent(btnType -> {});
    }

    /**
     * Opens a dialog to change the winning score threshold.
     * Includes validation to ensure the input is a valid integer.
     */
    public void setGameTarget () {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Game Point Target");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(string -> {
            try {
                new_target = Integer.parseInt(result.get());

                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("DIY_Pong");
                alert.setHeaderText("Game Target Changed...");
                alert.setContentText("Target Changed from "+ game.getGame_target()+" to " + new_target);
                game.setGame_target(new_target);
                alert.showAndWait();

            } catch (NumberFormatException e) {

                // If the user types "apple" instead of "5", the game won't crash
                var error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Please enter a valid whole number!");
                error.showAndWait();

            }
        });
    }

    /**
     * Opens a dialog to adjust how many bounces occur before the ball accelerates.
     */
    public void setBall_Speed_Increase() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Bounces for speed increase");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(string -> {
            try {
                new_speed = Integer.parseInt(result.get());


                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("DIY_Pong");
                alert.setHeaderText("Ball Speed Changed...");
                alert.setContentText("Bounces before speed increase: " + game.getBall_speed_increase() + " -> " + new_speed);
                game.setBall_speed_increase(new_speed);
                alert.showAndWait();

            } catch (NumberFormatException e) {

                // If the user types "apple" instead of "5", the game won't crash... again
                var error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Please enter a valid whole number!");
                error.showAndWait();

            }
        });
    }

    /**
     * Sequentially opens dialogs to rename Player 1 and Player 2.
     * Triggers a canvas redraw to update the HUD immediately.
     */
    public void setPlayerNames() {
        // Dialog for Player 1
        TextInputDialog dialog1 = new TextInputDialog("Player 1");
        dialog1.setTitle("Player Names");
        dialog1.setHeaderText("Enter name for Player 1 (Left):");

        dialog1.showAndWait().ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                controller.getP1().setName(name);
                canvas.Draw(controller.getP1(), controller.getP2(), controller.getBall());
            }
        });

        // Dialog for Player 2
        TextInputDialog dialog2 = new TextInputDialog("Player 2");
        dialog2.setTitle("Player Names");
        dialog2.setHeaderText("Enter name for Player 2 (Right):");

        dialog2.showAndWait().ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                controller.getP2().setName(name);
                canvas.Draw(controller.getP1(), controller.getP2(), controller.getBall());
            }
        });
    }

    /**
     * Opens a dialog to set a new height for both player rackets.
     * @throws NumberFormatException if the input is not a valid double.
     */
    public void setRacketHeight() {
        TextInputDialog dialog = new TextInputDialog("100");
        dialog.setTitle("Racket Settings");
        dialog.setHeaderText("Adjust Racket Height");
        dialog.setContentText("Enter new height (e.g., 80 - 150):");

        dialog.showAndWait().ifPresent(result -> {
            try {
                double newHeight = Double.parseDouble(result);

                // Update both player's rackets in the model
                controller.getP1().getRacket().setRacketHeight(newHeight);
                controller.getP2().getRacket().setRacketHeight(newHeight);


            } catch (NumberFormatException e) {
                var error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Please enter a valid number for the height!");
                error.showAndWait();
            }
        });
    }

    /**
     * Opens a dialog to set a new width for both player rackets.
     * Forces an immediate redraw of the view to reflect size changes.
     */
    public void setRacketWidth() {
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Racket Settings");
        dialog.setHeaderText("Adjust Racket width");
        dialog.setContentText("Enter new width (e.g., 5 - 40):");

        dialog.showAndWait().ifPresent(result -> {
            try {
                double newWidth = Double.parseDouble(result);

                // Update both player's rackets in the model
                controller.getP1().getRacket().setRacketWidth(newWidth);
                controller.getP2().getRacket().setRacketWidth(newWidth);

                // Refresh the View immediately
                canvas.Draw(controller.getP1(), controller.getP2(), controller.getBall());

            } catch (NumberFormatException e) {
                var error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Please enter a valid number for the width!");
                error.showAndWait();
            }
        });
    }
}
