package view.gui.layouts.map_view_layouts;

import helper.GuiHelper;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.load_save_game_elements.GameSaveManager;
import models.player.player_structure.Player;
import view.gui.MapView;

public class GameMenuLayer extends BorderPane {
    private Stage primaryStage;
    private MapView mapView;
    private Player player;

    public GameMenuLayer(Player player, MapView mapView) {
        this.player = player;
        this.mapView = mapView;

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
