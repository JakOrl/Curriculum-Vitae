package Pong_Source.Services;


import Pong_Source.Controller.Game;
import Pong_Source.Models.GameState;
import javafx.scene.control.Alert;
import java.io.*;

/**
 * A Singleton service responsible for handling the serialization and
 * deserialization of the game state.
 * <p>
 * This class implements the <b>Singleton Design Pattern</b> to ensure that only
 * one file-access service exists within the application, preventing file
 * corruption and resource conflicts. It provides robust error handling with
 * user-facing feedback via JavaFX Alerts.
 * </p>
 * * @author Jakub Orlowski
 * @version 1.0
 */

public class SaveService {

    /** The single static instance of the SaveService. */
    private static SaveService instance;

    /**
     * Private constructor to enforce the Singleton pattern,
     * preventing external instantiation.
     */
    private SaveService() {}


    /**
     * Provides global access to the single instance of the SaveService.
     * * @return The active {@link SaveService} instance.
     */
    public static SaveService getInstance() {
        if (instance == null) {
            instance = new SaveService();
        }
        return instance;
    }

    /**
     * Serializes the provided GameState object to a local file.
     * <p>
     * Implements robust exception handling to catch {@link IOException}
     * and provides visual feedback to the user upon success or failure.
     * </p>
     * * @param state The {@link GameState} snapshot to be saved.
     */
    public void saveGame(GameState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savegame.ser"))){
            oos.writeObject(state);
            showInfo("Success", "Game saved to savegame.ser");
        } catch (IOException e){
            showError("Save Failed", "Error saving file:" + e.getMessage());
        }
    }

    /**
     * Deserializes the GameState object from the local save file.
     * <p>
     * Handles specific scenarios such as missing files ({@link FileNotFoundException})
     * and corrupted data, returning null if the load operation fails.
     * </p>
     * * @return The loaded {@link GameState} object, or {@code null} if an error occurs.
     */
    public GameState loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savegame.ser"))){
            return (GameState) ois.readObject();
        } catch (FileNotFoundException e){
            showError("Load failed", "No save file found.");
        } catch (IOException | ClassNotFoundException e){
            showError("Load failed", "Error reading save file.");
        }
        return null;
    }



    /**
     * Displays a non-critical information popup to the user.
     * * @param title   The title of the alert window.
     * @param message The content text to display.
     */
    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays an error popup to the user when an operation fails.
     * * @param title   The title of the alert window.
     * @param message The error description to display.
     */
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
