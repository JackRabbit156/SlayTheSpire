package view.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import models.map_elements.Node;
import models.map_elements.acts.ActOne;
import models.player.Ironclad;
import models.player.player_structure.Player;
import view.gui.layouts.layout_events.MapViewEvents;
import view.gui.layouts.map_view_layouts.MapLayout;

import java.util.ArrayList;
import java.util.List;

public class MapView extends BorderPane {
    private MapViewEvents mapViewEvents;

    private MapLayout map;
    private Player player;
    private List<Node> nodes;
    private int mapWidth;
    private int mapHeight;

    public MapView(Player player,  List<Node> nodes, int mapWidth, int mapHeight, MapViewEvents mapViewEvents) {
        this.nodes = nodes;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.mapViewEvents = mapViewEvents;
        this.player = player;

        setStyle("-fx-background-color: #969286;");
        //setBackground(new Background(background()));
        map = new MapLayout(this, nodes, mapWidth, mapHeight);

        initTopSide();
        initLeftSide();
    }

    public void clickedOnValidField(Node node){
        mapViewEvents.onValidFieldClick(player, node);
    }

    private void initTopSide() {
        HBox topHBox = new HBox(30);
        topHBox.setPadding(new Insets(0,0,0,300));
        topHBox.setBackground(new Background(background("/images/map/mapTopSec.png")));

        Label playerNameLabel = new Label(player.getName());
        playerNameLabel.getStyleClass().add("name-label");
        Label playerFloorLabel = new Label("Floor " +player.getCurrentField());
        playerFloorLabel.getStyleClass().add("floor-label");
        Label playerMoneyLabel = new Label("Gold "+player.getGold());
        playerMoneyLabel.getStyleClass().add("gold-label");
        Label playerHealthLabel = new Label("Health " + player.getCurrentHealth() +"/" + player.getMaxHealth());
        playerHealthLabel.getStyleClass().add("health-label");

        topHBox.getChildren().addAll(playerNameLabel, playerHealthLabel, playerMoneyLabel, playerFloorLabel);

        setTop(topHBox);

        /*Region topPlaceholder = new Region();
        topPlaceholder.setMinHeight(200); // Festlegen der konstanten Höhe

        setTop(topPlaceholder);*/
    }

    private void initLeftSide() {
        Region bottomPlaceholder = new Region();
        bottomPlaceholder.setMinWidth(800); // Festlegen der konstanten Höhe
        setLeft(bottomPlaceholder);
    }

    private BackgroundImage background(String backgroundPath){
        Image backgroundImage = new Image(getClass().getResource(backgroundPath).toExternalForm());
        // Erstelle das Hintergrundbild mit den gewünschten Eigenschaften
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, // Option: NO_REPEAT, REPEAT, REPEAT_X, REPEAT_Y
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 50, false, false, false, false)
        );
        return background;
    }
}