package view.gui.layouts.map_view_layouts;

import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.load_save_game_elements.GameSaveManager;
import models.player.player_structure.Player;
import view.gui.MapView;

public class GameMenuLayer extends BorderPane {
    private Stage primaryStage;
    private MapView mapView;
    private Player player;

    private Label header;

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
        header = new Label("Game Menu");
        header.setEffect(dropShadow);
        header.setFont(kreonFont);
        header.setTextFill(Color.GOLD);
        topBar.getChildren().addAll(header);

        setTop(topBar);

        initMenuButtons();
    }

    private void initMenuButtons() {
        Button loadGameButton = new Button("Load Game");
        Button saveGameButton = new Button("Save Game");
        Button backButton = new Button("Back");

        loadGameButton.setOnAction(event -> mapView.clickedOnLoadButton());
        saveGameButton.setOnAction(event -> mapView.clickedOnSaveButton());
        backButton.setOnAction(event -> mapView.clickedOnBackButton());

        VBox menuItems = new VBox();
        menuItems.getChildren().addAll(loadGameButton, saveGameButton, backButton);

        setLeft(menuItems);
    }
}
