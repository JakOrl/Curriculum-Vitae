package Pong_Source.View;

import Pong_Source.Controller.*;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * High-level UI component that assembles and manages the game's MenuBar.
 * <p>
 * This class creates the visual hierarchy of the menus (Settings, Game Menu, Racket Settings)
 * and maps each {@link MenuItem} to its corresponding logic in the {@link MenuListener}.
 * </p>
 * @author Jakub Orlowski
 * @version 1.0
 */
public class Pong_Menu {

    // ----------------------------------
    // Fields for items and dropdowns etc
    // ----------------------------------
    private MenuBar menuBar;
    private Menu menuSettings;
    private Menu menuGame;
    private Menu menuRacket;
    private MenuItem menuItemExit;
    private MenuItem menuItemAbout;
    private MenuItem menuGameLimit;
    private MenuItem menuBallSpeed;
    private MenuItem menuRacketWidth;
    private MenuItem menuRacketHeight;
    private MenuItem menuPlayerName;

    /** The listener that defines the behavior for menu actions. */
    private MenuListener menuListener;

    /**
     * Constructs the menu system and attaches it to the provided listener.
     * <p>
     * This constructor performs the following initialization steps:
     * <ol>
     * <li>Instantiates the {@link MenuBar} and category {@link Menu}s.</li>
     * <li>Creates specific {@link MenuItem}s for each game setting.</li>
     * <li>Nests items within their respective category menus.</li>
     * <li>Calls {@link #handleClicking()} to bind actions to the items.</li>
     * </ol>
     * </p>
     * @param menuListener The handler for menu events.
     */
    public Pong_Menu(MenuListener menuListener) {

        this.menuListener = menuListener;

        menuBar = new MenuBar();
        // ---------------------
        // Menu bar drop downs
        // ---------------------
        menuSettings = new Menu("Settings");
        menuGame = new Menu("Game Menu");
        menuRacket = new Menu("Racket Settings");

        // ---------------------
        // Menu drop down items
        // ---------------------
        menuItemExit = new MenuItem("Exit");
        menuItemAbout = new MenuItem("About");

        menuGameLimit = new MenuItem("Game Target");
        menuBallSpeed = new MenuItem("bounces for speed increase");

        menuRacketWidth = new MenuItem("RacketWidth");
        menuRacketHeight = new MenuItem("RacketHeight");

        menuPlayerName = new MenuItem("Change Player Names");

        // ----------------------------------
        // Adding the items to the dropdowns
        // ----------------------------------
        menuSettings.getItems().add(menuItemExit);
        menuSettings.getItems().add(menuItemAbout);

        menuGame.getItems().add(menuGameLimit);
        menuGame.getItems().add(menuBallSpeed);
        menuGame.getItems().add(menuPlayerName);

        menuRacket.getItems().add(menuRacketWidth);
        menuRacket.getItems().add(menuRacketHeight);

        // ------------------------------
        // Adding everything to menu bar
        // ------------------------------
        menuBar.getMenus().addAll(menuSettings, menuGame, menuRacket);

        handleClicking();
    }

    // ---------------------
    // When clicked -> do..
    // ---------------------

    /**
     * Binds each MenuItem's {@code onAction} event to a method in the {@link MenuListener}.
     * This separates the UI structure from the functional logic.
     */
    private void handleClicking() {
        menuItemExit.setOnAction(e -> menuListener.setExit());
        menuItemAbout.setOnAction(e -> menuListener.setAbout());
        menuGameLimit.setOnAction(e -> menuListener.setGameTarget());
        menuBallSpeed.setOnAction(e -> menuListener.setBall_Speed_Increase());
        menuRacketHeight.setOnAction(e -> menuListener.setRacketHeight());
        menuRacketWidth.setOnAction(e -> menuListener.setRacketWidth());
        menuPlayerName.setOnAction(e -> menuListener.setPlayerNames());
    }

    /**
     * @return The fully assembled {@link MenuBar} to be placed in the layout root (e.g., BorderPane Top).
     */
    public MenuBar getMenuBar() {
        return menuBar;
    }

}
