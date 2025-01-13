package de.bundeswehr.auf.slaythespire.gui;

import de.bundeswehr.auf.slaythespire.controller.listener.DifficultyMenuListener;
import de.bundeswehr.auf.slaythespire.controller.listener.GameMenuListener;
import de.bundeswehr.auf.slaythespire.gui.events.MapViewEvents;
import de.bundeswehr.auf.slaythespire.gui.layouts.map.menu.DifficultyMenuLayer;
import de.bundeswehr.auf.slaythespire.gui.layouts.map.menu.GameMenuLayer;
import de.bundeswehr.auf.slaythespire.gui.layouts.map.LegendLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.map.MapLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.top_bar.TopBarLayout;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.map.Node;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.scene.layout.*;

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
public class MapView extends StackPane implements View, WithTopBar {

    private final DifficultyMenuLayer difficultyMenu;
    private final DifficultyMenuListener difficultyMenuListener;
    private final GameMenuLayer gameMenu;
    private final GameMenuListener gameMenuListener;
    private final BorderPane mainMap;
    private final MapLayout mapCenter;
    private final MapViewEvents mapViewEvents;
    private final Player player;
    private TopBarLayout top;

    /**
     * Konstruktor für 'MapView'.
     *
     * @param player                 Der Spieler, der auf der Karte dargestellt wird.
     * @param nodes                  Eine Liste von Knotenpunkten auf der Karte.
     * @param mapWidth               Die Breite der Karte.
     * @param mapHeight              Die Höhe der Karte.
     * @param mapViewEvents          Ereignislistener für die Karteninteraktion.
     * @param gameMenuListener       Listener für das Spielmenü.
     * @param difficultyMenuListener Listener für das Schwierigkeitsmenü.
     */
    public MapView(Player player, List<Node> nodes, int mapWidth, int mapHeight, MapViewEvents mapViewEvents, GameMenuListener gameMenuListener, DifficultyMenuListener difficultyMenuListener) {
        this.mapViewEvents = mapViewEvents;
        this.gameMenuListener = gameMenuListener;
        this.difficultyMenuListener = difficultyMenuListener;
        this.player = player;

        gameMenu = new GameMenuLayer(player, this);
        gameMenu.setVisible(false);

        difficultyMenu = new DifficultyMenuLayer(player, this);
        difficultyMenu.setVisible(false);

        mainMap = new BorderPane();
        mainMap.setBackground(new Background(GuiHelper.backgroundInHD("/images/map/mapMid.png")));
        mapCenter = new MapLayout(this, nodes, mapWidth, mapHeight, player);
        mainMap.setCenter(mapCenter);

        initTopSide();
        initLeftSide();
        initRightSide();

        getChildren().addAll(mainMap, gameMenu, difficultyMenu);
        setStyle("-fx-background-color: #9a9990;");
    }

    public void clickedOnBackButton() {
        gameMenuListener.onBackClick();
    }

    public void clickedOnChangeDifficultyButton() {
        gameMenuListener.onChangeDifficultyClick();
    }

    public void clickedOnDifficultyBackButton() {
        difficultyMenuListener.onDifficultyBackClick();
    }

    public void clickedOnDifficultyHardcoreButton() {
        difficultyMenuListener.onHardcoreClick();
    }

    public void clickedOnDifficultyNormalButton() {
        difficultyMenuListener.onNormalClick();
    }

    public void clickedOnExitButton() {
        gameMenuListener.onExitClick();
    }

    public void clickedOnLoadButton() {
        gameMenuListener.onLoadClick();
    }

    public void clickedOnMainMenuButton() {
        gameMenuListener.onMainMenuClick();
    }

    public void clickedOnSaveButton() {
        gameMenuListener.onSaveClick();
    }

    public void clickedOnValidField(Node node) {
        mapViewEvents.onValidFieldClick(player, node);
    }

    public void closeDifficultyMenu() {
        difficultyMenu.setVisible(false);
        gameMenu.setVisible(true);
    }

    public void closeGameMenu() {
        mainMap.setVisible(true);
        gameMenu.setVisible(false);
    }

    @Override
    public void discard() {
        mapCenter.discard();
        top.discard();
    }

    @Override
    public void onFullScreen() {
        mapViewEvents.onFullScreenClick();
    }

    @Override
    public void onSettings() {
        mapViewEvents.onSettingsClick();
    }

    @Override
    public boolean showSettings() {
        return true;
    }

    public void openDifficultyMenu() {
        difficultyMenu.setVisible(true);
        gameMenu.setVisible(false);
    }

    public void openGameMenu() {
        mainMap.setVisible(false);
        gameMenu.setVisible(true);
    }

    private void initLeftSide() {
        Region placeHolder = new Region();
        placeHolder.setMinWidth(800);
        mainMap.setLeft(placeHolder);
    }

    private void initRightSide() {
        LegendLayout legend = new LegendLayout();
        mainMap.setRight(legend);
    }

    private void initTopSide() {
        top = new TopBarLayout(this, player);
        mainMap.setTop(top);
    }

}