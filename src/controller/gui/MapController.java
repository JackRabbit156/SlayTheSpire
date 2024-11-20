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

        if(getCurrentFieldFromAct().equals(act.getLastField())){
            switch (player.getCurrentAct()){
                case 1:
                    act = new ActTwo(player, false); break;
                case 2 :
                case 4:
                    act = new ActFour(player, false); break;
                default:
                    System.out.println("Weird"); return;
            }

            act.getNodes().get(0).doFieldThing(player);
            return;
        }

        this.mapView = new MapView(player, act.getNodes(), act.getMapWidth(), act.getMapHeight(), this);
    }

    @Override
    public void onValidFieldClick(Player player, Node node) {
        node.doFieldThing(player);

        act.getCurrentField().setPlayer(null);
        node.setPlayer(player);
        player.setCurrentField(node.getFieldName());
    }

    private String getCurrentFieldFromAct(){
        return act.getCurrentFieldName();
    }

    public MapView getMapView(){
        return mapView;
    }
}