package de.bundeswehr.auf.slaythespire.gui.layouts.map_view_layouts;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import de.bundeswehr.auf.slaythespire.models.game_settings.GameSettings;
import de.bundeswehr.auf.slaythespire.models.game_settings.structure.GameMode;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;
import de.bundeswehr.auf.slaythespire.gui.MapView;

/**
 * Die Klasse 'GameMenuLayer' stellt das Menü für das Spiel in der Benutzeroberfläche dar.
 * Sie erweitert die 'BorderPane'-Klasse und enthält verschiedene Schaltflächen für
 * Aktionen im Spiel, wie das Laden, Speichern und Beenden eines Spiels.
 *
 * <p>
 * Die Klasse ermöglicht die Interaktion des Spielers mit dem Spielmenü, bietet visuelle Effekte
 * für Schaltflächen und passt das Layout an den Spieler und den aktuellen Zustand des Spiels an.
 * </p>
 *
 * @author Warawa Alexander
 */
public class GameMenuLayer extends BorderPane {

    private Button changeDifficultyButton;
    private final MapView mapView;
    private Player player;
    private Button saveGameButton;

    /**
     * Konstruktor für die Klasse 'GameMenuLayer'.
     *
     * @param player  Der Spieler, der das Menü nutzt
     * @param mapView Die MapView, die zur Anzeige der Karte verwendet wird
     */
    public GameMenuLayer(Player player, MapView mapView) {
        this.player = player;
        this.mapView = mapView;

        setBackground(new Background(GuiHelper.background("/images/backgrounds/loadViewBackground.png")));

        Font kreonFont = Font.loadFont(getClass().getResourceAsStream("/font/kreon/static/Kreon-Bold.ttf"), 44);
        // Erstellen eines DropShadow-Effekts
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(9.0); // horizontaler Versatz
        dropShadow.setOffsetY(9.0); // vertikaler Versatz
        dropShadow.setColor(Color.GRAY); // Farbe des Schattens
        dropShadow.setRadius(5.0); // Radius des Schattens

        VBox topBar = new VBox();
        topBar.setAlignment(Pos.TOP_CENTER);
        Label header = new Label("Game Menu");
        header.setEffect(dropShadow);
        header.setFont(kreonFont);
        header.setTextFill(Color.GOLD);
        topBar.getChildren().addAll(header);

        setTop(topBar);

        this.visibleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (GameSettings.getGameMode().equals(GameMode.HARDCORE)) {
                    saveGameButton.setDisable(true);
                    changeDifficultyButton.setDisable(true);
                }
                else {
                    saveGameButton.setDisable(false);
                    changeDifficultyButton.setDisable(false);
                }
            }
        });

        initMenuButtons();
    }

    private void assignButtonDesign(Button button) {
        String highlightPath = "/images/buttons/menu_highlight.png";
        String fontPath = "/resources/font/kreon/static/Kreon-Bold.ttf";

        button.setTextFill(Color.WHITE);
        button.setFont(Font.font(fontPath, 30));
        button.setAlignment(Pos.CENTER);
        button.setBackground(Background.EMPTY);
        button.setMinSize(180, 50);
        button.setOnMouseEntered(event -> button.setBackground(new Background(GuiHelper.background(highlightPath))));
        button.setOnMouseExited(event -> button.setBackground(Background.EMPTY));
    }

    private void initMenuButtons() {
        Button loadGameButton = new Button("Load Game");
        saveGameButton = new Button("Save Game");
        changeDifficultyButton = new Button("Change Difficulty");
        Button mainMenuButton = new Button("End this Game");
        Button backButton = new Button("Back");
        Button exitButton = new Button("Quit");

        assignButtonDesign(loadGameButton);
        assignButtonDesign(saveGameButton);
        assignButtonDesign(changeDifficultyButton);
        assignButtonDesign(mainMenuButton);
        assignButtonDesign(backButton);
        assignButtonDesign(exitButton);

        loadGameButton.setOnAction(event -> mapView.clickedOnLoadButton());
        saveGameButton.setOnAction(event -> mapView.clickedOnSaveButton());
        mainMenuButton.setOnAction(event -> mapView.clickedOnMainMenuButton());
        changeDifficultyButton.setOnAction(event -> mapView.clickedOnChangeDifficultyButton());
        backButton.setOnAction(event -> mapView.clickedOnBackButton());
        exitButton.setOnAction(event -> mapView.clickedOnExitButton());

        VBox menuItems = new VBox();
        menuItems.getChildren().addAll(loadGameButton, saveGameButton, changeDifficultyButton, mainMenuButton, backButton, exitButton);

        menuItems.setAlignment(Pos.TOP_CENTER);
        menuItems.setPadding(new Insets(150, 0, 0, 0));
        setCenter(menuItems);
    }

}
