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

/**
 * Die Klasse MapView dient zur Darstellung der Karte eines Aktes im Spiel.
 * Sie ermöglicht das Platzieren der Knotenpunkte auf der Karte und die Ausgabe
 * der Karte in der grafischen Oberfläche.
 *
 * <p>Die Klasse übernimmt das Layout der Karte und setzt den Spieler sowie
 * die Knoten entsprechend ihrer Positionen im Akt auf die Karte.</p>
 *
 * @author Warawa Alexander
 */
public class MapView extends BorderPane {
    private MapViewEvents mapViewEvents;

    private MapLayout map;
    private Player player;
    private List<Node> nodes;


    public MapView(Player player, List<Node> nodes, int mapWidth, int mapHeight, MapViewEvents mapViewEvents) {
        this.nodes = nodes;
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
        topHBox.setStyle("-fx-background-color: rgba(34, 34, 34, 0.8);");
        topHBox.setPrefHeight(50);

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
    }

    private void initLeftSide() {
        Region placeHolder = new Region();
        placeHolder.setMinWidth(800); // Festlegen der konstanten Höhe
        setLeft(placeHolder);
    }
}