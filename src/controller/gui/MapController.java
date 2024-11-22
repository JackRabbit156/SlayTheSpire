package controller.gui;

import controller.listener.GameMenuListener;
import helper.GuiHelper;
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
public class MapController implements MapViewEvents, GameMenuListener {
    private MapView mapView;
    private Player player;

    private Act act;

    public MapController (Player player, boolean firstMapEntrance) {
        this.player = player;

        switch (player.getCurrentAct()){
            case 1: act = new ActOne(player, firstMapEntrance); break;
            case 2: act = new ActTwo(player, firstMapEntrance); break;
            case 3:  break;
            case 4: act = new ActFour(player, firstMapEntrance); break;
            default:
                System.out.println("Weird"); return;
        }

        this.mapView = new MapView(player, act.getNodes(), act.getMapWidth(), act.getMapHeight(), this, this);
    }

    @Override
    public void onValidFieldClick(Player player, Node node) {
        node.doFieldThing(player);

        act.getCurrentField().setPlayer(null);
        node.setPlayer(player);
        player.setCurrentField(node.getFieldName());
    }

    @Override
    public void onSettingsClick() {
        mapView.openGameMenu();
    }

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

    private String getCurrentFieldFromAct(){
        return act.getCurrentFieldName();
    }

    public MapView getMapView(){
        return mapView;
    }


}