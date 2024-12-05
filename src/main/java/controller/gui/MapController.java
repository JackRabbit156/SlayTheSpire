package controller.gui;

import controller.listener.DifficultyMenuListener;
import controller.listener.GameMenuListener;
import helper.GuiHelper;
import helper.MusicBoy;
import javafx.stage.Stage;
import models.game_settings.GameSettings;
import models.game_settings.structure.GameMode;
import models.load_save_game_elements.GameSaveManager;
import models.map_elements.Node;
import models.map_elements.acts.Act;
import models.map_elements.acts.ActFour;
import models.map_elements.acts.ActOne;
import models.map_elements.acts.ActTwo;
import models.player.player_structure.Player;
import view.gui.MapView;
import view.gui.layouts.layout_events.MapViewEvents;

/**
 *  Die Klasse MapController ist für die Steuerung der Spielkarte und die Interaktion
 * zwischen dem Spieler und den einzelnen Akten verantwortlich. Sie initiiert die Karte
 * und führt die Spielerbewegung durch.
 *
 * Die Klasse entscheidet basierend auf dem aktuellen Akt des Spielers, welche Akt-Klasse
 * ('ActOne', 'ActTwo' oder 'ActFour') verwendet wird und ruft die passenden Methoden zum
 * Anzeigen und Aktualisieren der Karte auf.
 *
 * @author Warawa Alexander
 */
public class MapController implements MapViewEvents, GameMenuListener, DifficultyMenuListener {
    private MapView mapView;
    private Player player;

    private Act act;

    public MapController (Player player) {
        this.player = player;
        MusicBoy.play("map");

        switch (player.getCurrentAct()){
            case 1: act = new ActOne(player); break;
            case 2: act = new ActTwo(player); break;
            case 3:  break;
            case 4: act = new ActFour(player); break;
            default:
                System.out.println("Weird"); return;
        }

        this.mapView = new MapView(player, act.getNodes(), act.getMapWidth(), act.getMapHeight(), this, this, this);
    }

    /*  MAP VIEW EVENTS */
    @Override
    public void onValidFieldClick(Player player, Node node) {
        node.doFieldThing(player);

        player.setCurrentField(node.getFieldName());
    }

    @Override
    public void onSettingsClick() {
        mapView.openGameMenu();
    }

    @Override
    public void onFullscreenClick() {
        Stage primaryStage = player.getPrimaryStage();

        primaryStage.setFullScreen(!primaryStage.isFullScreen());
    }

    /*  GAME MENU */
    @Override
    public void onSaveClick() {
        GameSaveManager saveManager = new GameSaveManager();
        saveManager.saveGame(player);
        mapView.closeGameMenu();
    }

    @Override
    public void onBackClick() {
        mapView.closeGameMenu();
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
    public void onChangeDifficultyClick() {
        mapView.openDifficultyMenu();
    }

    @Override
    public void onExitClick() {
        System.exit(0);
    }

    /* DIFFICULTY MENU */
    @Override
    public void onNormalClick() {
        GameSettings.setGameMode(GameMode.NORMAL);
    }

    @Override
    public void onHardcoreClick() {
        GameSettings.setGameMode(GameMode.HARDCORE);
    }

    @Override
    public void onDifficultyBackClick() {
        mapView.closeDifficultyMenu();
    }

    private String getCurrentFieldFromAct(){
        return act.getCurrentFieldName();
    }

    public MapView getMapView(){
        return mapView;
    }


}