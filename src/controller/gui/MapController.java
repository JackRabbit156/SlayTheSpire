package controller.gui;

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
public class MapController implements MapViewEvents {
    private MapView mapView;
    private Player player;

    private Act act;

    public MapController (Player player, boolean loadingFromFile) {
        this.player = player;

        switch (player.getCurrentAct()){
            case 1: act = new ActOne(player, loadingFromFile); break;
            case 2: act = new ActTwo(player, loadingFromFile); break; // TODO: Für GUI
            case 3:  break; // TODO: Act 3, Für GUI
            case 4: act = new ActFour(player, loadingFromFile); break;
            default:
                System.out.println("Weird"); return;
        }

        this.mapView = new MapView(player, act.getNodes(), act.getMapWidth(), act.getMapHeight(), this);

    }

    public MapView getMapView(){
        return mapView;
    }

    @Override
    public void onValidFieldClick(Player player, Node node) {
        System.out.println("clicked on valid Field! " + node.getFieldName());

        node.doFieldThing(player);

        act.setBeatenNode(player, node); // TODO: Evtl. nach Sieg eines Feldes setzen.
        player.setCurrentField(getCurrentFieldFromAct());
    }

    private String getCurrentFieldFromAct(){
        return act.getCurrentField();
    }
}
