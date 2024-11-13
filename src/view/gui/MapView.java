package view.gui;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import models.map_elements.Node;
import models.player.player_structure.Player;
import view.gui.layouts.layout_events.MapViewEvents;
import view.gui.layouts.map_view_layouts.MapLayout;

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

        setStyle("-fx-background-color: #9a9990;");
        //setBackground(new Background(background()));
        map = new MapLayout(this, nodes, mapWidth, mapHeight);

        initTopSide();
        initLeftSide();
    }

    public void clickedOnValidField(Node node){
        mapViewEvents.onValidFieldClick(player, node);
    }

    private void initTopSide() {
        Region topPlaceholder = new Region();
        topPlaceholder.setMinHeight(200); // Festlegen der konstanten Höhe

        setTop(topPlaceholder);
    }

    private void initLeftSide() {
        Region bottomPlaceholder = new Region();
        bottomPlaceholder.setMinWidth(800); // Festlegen der konstanten Höhe
        setLeft(bottomPlaceholder);
    }

    private BackgroundImage background(){
        Image backgroundImage = new Image(getClass().getResource("/images/act1.png").toExternalForm());
        // Erstelle das Hintergrundbild mit den gewünschten Eigenschaften
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, // Option: NO_REPEAT, REPEAT, REPEAT_X, REPEAT_Y
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, true)
        );
        return background;
    }
}