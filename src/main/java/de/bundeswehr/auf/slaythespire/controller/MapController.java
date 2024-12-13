package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.controller.listener.DifficultyMenuListener;
import de.bundeswehr.auf.slaythespire.controller.listener.GameMenuListener;
import de.bundeswehr.auf.slaythespire.gui.MapView;
import de.bundeswehr.auf.slaythespire.gui.events.MapViewEvents;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.helper.MusicBoy;
import de.bundeswehr.auf.slaythespire.model.load_save.GameSaveManager;
import de.bundeswehr.auf.slaythespire.model.map.Node;
import de.bundeswehr.auf.slaythespire.model.map.act.Act;
import de.bundeswehr.auf.slaythespire.model.map.act.ActFour;
import de.bundeswehr.auf.slaythespire.model.map.act.ActOne;
import de.bundeswehr.auf.slaythespire.model.map.act.ActTwo;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.GameMode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Die Klasse MapController ist für die Steuerung der Spielkarte und die Interaktion
 * zwischen dem Spieler und den einzelnen Akten verantwortlich. Sie initiiert die Karte
 * und führt die Spielerbewegung durch.
 * <p>
 * Die Klasse entscheidet basierend auf dem aktuellen Akt des Spielers, welche Akt-Klasse
 * ('ActOne', 'ActTwo' oder 'ActFour') verwendet wird und ruft die passenden Methoden zum
 * Anzeigen und Aktualisieren der Karte auf.
 *
 * @author Warawa Alexander
 */
public class MapController implements Controller, MapViewEvents, GameMenuListener, DifficultyMenuListener {

    private Act act;
    private MapView mapView;
    private final Player player;

    public MapController(Player player) {
        this.player = player;
        MusicBoy.play("map");
        switch (player.getCurrentAct()) {
            case 1:
                act = new ActOne(player);
                break;
            case 2:
                act = new ActTwo(player);
                break;
            case 4:
                act = new ActFour(player);
                break;
            case 3:
            default:
                LoggingAssistant.log("Unknown act: " + player.getCurrentAct(), Color.RED);
                return;
        }
        this.mapView = new MapView(player, act.getNodes(), act.getMapWidth(), act.getMapHeight(), this, this, this);
    }

    @Override
    public void discard() {
        mapView.discard();
    }

    public MapView getMapView() {
        return mapView;
    }

    @Override
    public void onBackClick() {
        mapView.closeGameMenu();
    }

    @Override
    public void onChangeDifficultyClick() {
        mapView.openDifficultyMenu();
    }

    @Override
    public void onDifficultyBackClick() {
        mapView.closeDifficultyMenu();
    }

    @Override
    public void onExitClick() {
        GuiHelper.Scenes.close((Stage) mapView.getScene().getWindow());
    }

    @Override
    public void onFullscreenClick() {
        Stage primaryStage = player.getPrimaryStage();

        primaryStage.setFullScreen(!primaryStage.isFullScreen());
    }

    @Override
    public void onHardcoreClick() {
        GameSettings.setGameMode(GameMode.HARDCORE);
    }

    @Override
    public void onLoadClick() {
        GuiHelper.Scenes.startLoadGameFromMapScene(player);
    }

    @Override
    public void onMainMenuClick() {
        GuiHelper.Scenes.startMainMenuScene(player.getPrimaryStage());
    }

    @Override
    public void onNormalClick() {
        GameSettings.setGameMode(GameMode.NORMAL);
    }

    @Override
    public void onSaveClick() {
        GameSaveManager saveManager = new GameSaveManager();
        saveManager.saveGame(player);
        mapView.closeGameMenu();
    }

    @Override
    public void onSettingsClick() {
        mapView.openGameMenu();
    }

    @Override
    public void onValidFieldClick(Player player, Node node) {
        node.doFieldThing(player);

        player.setCurrentField(node.getFieldName());
    }

    private String getCurrentFieldFromAct() {
        return act.getCurrentFieldName();
    }

}