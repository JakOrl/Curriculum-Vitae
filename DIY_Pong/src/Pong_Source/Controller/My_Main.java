package Pong_Source.Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import Pong_Source.View.*;


/**
 * The main entry point for the DIY Pong application.
 * <p>
 * This class initializes the JavaFX environment, assembles the MVC components
 * (Controller, Canvas, Menu), and manages the primary {@link Scene}.
 * It also handles the global Keyboard event listeners that drive the game's
 * paddle movement and state controls (Pause, Reset, Serve).
 * </p>
 * * @author Jakub Orlowski
 * @version 1.0
 */
public class My_Main extends Application {
    //------------------------------
    // Fields
    //------------------------------
    /** The central logic controller for the game. */
    private Controller controller = new Controller();

    /** The custom canvas where game objects are rendered. */
    private Pong_Canvas Pong_Canvas = new Pong_Canvas(controller);

    /** The handler for menu interactions. */
    private MenuListener menuListener = new MenuListener(controller.getGame(),controller, Pong_Canvas );

    /** The UI component representing the top-level MenuBar. */
    private Pong_Menu pong_menu = new Pong_Menu(menuListener);

    //------------------------------
    // Constructor
    //------------------------------
    /**
     * Standard main method to launch the JavaFX application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the primary stage and sets up the user interface.
     * <p>
     * Specifically, this method:
     * <ul>
     * <li>Sets up the {@link BorderPane} root layout.</li>
     * <li>Binds the Canvas size properties to the window size.</li>
     * <li>Configures KeyPressed and KeyReleased event handlers for game control.</li>
     * <li>Initializes the game's AnimationTimer via the controller.</li>
     * </ul>
     * </p>
     * @param primaryStage The primary stage for this application, onto which
     * the application scene is set.
     */
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #000435");

        controller.startFramerate(Pong_Canvas);

        Pong_Canvas.widthProperty().bind(root.widthProperty());
        Pong_Canvas.heightProperty().bind(root.heightProperty());

        root.setCenter(Pong_Canvas);
        root.setTop(pong_menu.getMenuBar());

        Scene myScene = new Scene(root, 800, 600);


        //------------------------------
        // Key Pressing Logic
        //------------------------------
        myScene.setOnKeyPressed(e ->{
            System.out.println("Key pressed ->" + e.getCode());
            switch(e.getCode()){
                case W -> controller.p1UP = true;
                case S -> controller.p1DOWN = true;
                case UP -> controller.p2UP = true;
                case DOWN -> controller.p2DOWN = true;
                case SPACE -> {
                    if (!controller.isGameOver()){
                        controller.setStatus_Message("");
                        controller.gameStart = true;
                        controller.requestManualRedraw();
                    }
                }
                case N ->{
                    if (controller.isPaused()){
                        controller.setStatus_Message("GAME PAUSED, New game by pressing 'P'");
                        controller.togglePause();
                    }
                        controller.getP1().setScore(0);
                        controller.getP2().setScore(0);
                        controller.setStatus_Message("New Game ! Press SPACE to Serve !");
                        controller.resetBounceCount();
                        controller.setis_game_over(false);
                        controller.gameStart = false;
                        double startX = (Math.random() > 0.5) ? 3.5 : -3.5;
                        controller.getBall().setVelValues(startX,3.5);

                        controller.requestManualRedraw();
                }
                case P ->{
                    controller.togglePause();
                }
            }
        });

        myScene.setOnKeyReleased(e ->{
            switch(e.getCode()){
                case W -> controller.p1UP = false;
                case S -> controller.p1DOWN = false;
                case UP -> controller.p2UP = false;
                case DOWN -> controller.p2DOWN = false;
            }
        });
        //------------------------------
        root.setTop(pong_menu.getMenuBar());
        root.setCenter(Pong_Canvas);

        Pong_Canvas.widthProperty().bind(root.widthProperty());
        Pong_Canvas.heightProperty().bind(
                root.heightProperty().subtract(pong_menu.getMenuBar().heightProperty())
        );


        primaryStage.setScene(myScene);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(400);
        primaryStage.show();

        root.requestFocus();
        Pong_Canvas.setFocusTraversable(true);

        Pong_Canvas.Draw(controller.getP1(), controller.getP2(), controller.getBall());
    }
}