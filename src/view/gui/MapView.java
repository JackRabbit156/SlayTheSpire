package view.gui;

import controller.listener.DifficultyMenuListener;
import controller.listener.GameMenuListener;
import helper.GuiHelper;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import models.map_elements.Node;
import models.player.player_structure.Player;
import view.gui.layouts.layout_events.MapViewEvents;
import view.gui.layouts.map_view_layouts.*;

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
public class MapView extends StackPane {
    private MapViewEvents mapViewEvents;
    private GameMenuListener gameMenuListener;
    private DifficultyMenuListener difficultyMenuListener;

    private BorderPane mainMap;
    private MapLayout mapCenter;
    private LegendLayout legendRight;

    private GameMenuLayer gameMenu;
    private DifficultyMenuLayer difficultyMenu;


    private Player player;
    private List<Node> nodes;

    public BorderPane getMainMap(){
        return mainMap;
    }

    /**
     * Konstruktor für 'MapView'.
     *
     * @param player Der Spieler, der auf der Karte dargestellt wird.
     * @param nodes  Eine Liste von Knotenpunkten auf der Karte.
     * @param mapWidth Die Breite der Karte.
     * @param mapHeight Die Höhe der Karte.
     * @param mapViewEvents Ereignislistener für die Karteninteraktion.
     * @param gameMenuListener Listener für das Spielmenü.
     * @param difficultyMenuListener Listener für das Schwierigkeitsmenü.
     */
    public MapView(Player player, List<Node> nodes, int mapWidth, int mapHeight, MapViewEvents mapViewEvents, GameMenuListener gameMenuListener, DifficultyMenuListener difficultyMenuListener) {
        this.nodes = nodes;
        this.mapViewEvents = mapViewEvents;
        this.gameMenuListener = gameMenuListener;
        this.difficultyMenuListener = difficultyMenuListener;
        this.player = player;

        gameMenu = new GameMenuLayer(player, this);
        gameMenu.setVisible(false);

        difficultyMenu = new DifficultyMenuLayer(player, this);
        difficultyMenu.setVisible(false);

        mainMap = new BorderPane();

        setStyle("-fx-background-color: #9a9990;");
        mainMap.setBackground(new Background(GuiHelper.background("/images/map/mapMid.png")));

        mapCenter = new MapLayout(this, nodes, mapWidth, mapHeight, Integer.parseInt(player.getCurrentField()));

        initTopSide();
        initLeftSide();
        initRightSide();

        getChildren().addAll(mainMap, gameMenu, difficultyMenu);
    }

    public void clickedOnValidField(Node node){
        mapViewEvents.onValidFieldClick(player, node);
    }

    public void clickedOnSettings(){
        mapViewEvents.onSettingsClick();
    }

    public void clickedOnFullscreen(){
        mapViewEvents.onFullscreenClick();
    }

    public void clickedOnSaveButton(){
        gameMenuListener.onSaveClick();
    }

    public void clickedOnLoadButton(){
        gameMenuListener.onLoadClick();
    }

    public void clickedOnMainMenuButton(){
        gameMenuListener.onMainMenuClick();
    }

    public void clickedOnChangeDifficultyButton(){
        gameMenuListener.onChangeDifficultyClick();
    }

    public void clickedOnBackButton(){
        gameMenuListener.onBackClick();
    }

    public void clickedOnExitButton(){
        gameMenuListener.onExitClick();
    }

    private void initTopSide() {
        HBox topHBox = new TopBarLayout(player, this);

        mainMap.setTop(topHBox);
    }

    private void initLeftSide() {
        Region placeHolder = new Region();
        placeHolder.setMinWidth(800); // Festlegen der konstanten Höhe
        mainMap.setLeft(placeHolder);
    }

    private void initRightSide() {
        legendRight = new LegendLayout();
        mainMap.setRight(legendRight);
    }

    public void openGameMenu() {
        mainMap.setVisible(false);
        gameMenu.setVisible(true);
    }

    public void closeGameMenu() {
        mainMap.setVisible(true);
        gameMenu.setVisible(false);
    }

    public void openDifficultyMenu(){
        difficultyMenu.setVisible(true);
        gameMenu.setVisible(false);
    }

    public void closeDifficultyMenu(){
        difficultyMenu.setVisible(false);
        gameMenu.setVisible(true);
    }

    public void clickedOnDifficultyBackButton(){
        difficultyMenuListener.onDifficultyBackClick();
    }

    public void clickedOnDifficultyNormalButton(){
        difficultyMenuListener.onNormalClick();
    }

    public void clickedOnDifficultyHardcoreButton(){
        difficultyMenuListener.onHardcoreClick();
    }
}