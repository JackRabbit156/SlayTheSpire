package de.bundeswehr.auf.slaythespire.gui.layouts.map_view_layouts;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
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
 * Die Klasse 'DifficultyMenuLayer' stellt das Menü zur Auswahl der Schwierigkeitsstufe dar.
 *
 * ...
 */
public class DifficultyMenuLayer extends BorderPane {
    private MapView mapView;
    private Player player;

    private Label header;

    // Glow-Effekte
    private Glow glowEffect;
    private Button normalButton;
    private Button hardcoreButton;


    /**
     * Konstruktor für 'DifficultyMenuLayer'.
     *
     * @param player Der Spieler, der das Menü verwendet.
     * @param mapView Die zugehörige Kartenansicht.
     */
    public DifficultyMenuLayer(Player player, MapView mapView) {
        this.player = player;
        this.mapView = mapView;

        setBackground(new Background(GuiHelper.background("/images/backgrounds/loadViewBackground.png")));

        Font kreonFont = Font.loadFont(getClass().getResourceAsStream("/font/kreon/static/Kreon-Bold.ttf"), 44);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(9.0);
        dropShadow.setOffsetY(9.0);
        dropShadow.setColor(Color.GRAY);
        dropShadow.setRadius(5.0);

        VBox topBar = new VBox();
        topBar.setAlignment(Pos.TOP_CENTER);
        header = new Label("Difficulty");
        header.setEffect(dropShadow);
        header.setFont(kreonFont);
        header.setTextFill(Color.GOLD);
        topBar.getChildren().addAll(header);

        setTop(topBar);

        glowEffect = new Glow(1.0);  // Glow-Effekt

        initMenuButtons();
    }

    private void assignButtonDesign(Button button){
        String highlightPath = "/images/buttons/menu_highlight.png";
        String fontPath = "/resources/font/kreon/static/Kreon-Bold.ttf";

        button.setTextFill(Color.WHITE);
        button.setFont(Font.font(fontPath, 30));
        button.setAlignment(Pos.CENTER);
        button.setBackground(Background.EMPTY);
        button.setMinSize(180, 50);
        button.setOnMouseEntered(event -> {
            button.setBackground(new Background(GuiHelper.background(highlightPath)));
        });
        button.setOnMouseExited(event -> {
            button.setBackground(Background.EMPTY);
        });
    }

    private void initMenuButtons() {
        normalButton = new Button("Normal");
        hardcoreButton = new Button("Hardcore");
        Button backButton = new Button("Back");

        assignButtonDesign(backButton);
        assignButtonDesign(normalButton);
        assignButtonDesign(hardcoreButton);

        updateButtonGlow(GameSettings.getGameMode());

        normalButton.setOnAction(event -> {
            GameSettings.setGameMode(GameMode.NORMAL); // Sets the game mode
            updateButtonGlow(GameMode.NORMAL); // Updates glow effects
            mapView.clickedOnDifficultyNormalButton();
        });

        hardcoreButton.setOnAction(event -> {
            GameSettings.setGameMode(GameMode.HARDCORE); // Sets the game mode
            updateButtonGlow(GameMode.HARDCORE); // Updates glow effects
            mapView.clickedOnDifficultyHardcoreButton();
        });

        backButton.setOnAction(event -> {
            mapView.clickedOnDifficultyBackButton();
        });

        VBox menuItems = new VBox();
        menuItems.getChildren().addAll(normalButton, hardcoreButton, backButton);
        menuItems.setAlignment(Pos.TOP_CENTER);
        menuItems.setPadding(new Insets(150, 0, 0, 0));
        backButton.setTranslateY(100);

        setCenter(menuItems);
    }

    // Methode zur Aktualisierung des Glow-Effekts basierend auf dem aktuellen Spielmodus
    private void updateButtonGlow(GameMode gameMode) {
        if (gameMode.equals(GameMode.NORMAL)) {
            normalButton.setEffect(glowEffect);
            hardcoreButton.setEffect(null);
        } else if (gameMode.equals(GameMode.HARDCORE)) {
            hardcoreButton.setEffect(glowEffect);
            normalButton.setEffect(null);
        }
    }
}