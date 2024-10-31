package controller;

import models.map_elements.acts.Act;
import models.map_elements.acts.ActOne;
import models.map_elements.acts.ActTwo;
import models.player.player_structure.Player;
import view.MapView;


/**
 *  Die Klasse MapViewController ist für die Steuerung der Spielkarte und die Interaktion
 * zwischen dem Spieler und den einzelnen Akten verantwortlich. Sie initiiert die Karte
 * und führt die Schleife für die Spielerbewegung durch.
 *
 * @brief Diese Klasse entscheidet basierend auf dem aktuellen Akt des Spielers, welche Akt-Klasse
 * (`ActOne` oder `ActTwo`) verwendet wird und ruft die passenden Methoden zum
 * Anzeigen und Aktualisieren der Karte auf.</p>
 *
 * @author Warawa Alexander
 */
public class MapViewController {
    private Player player;

    //List<Node> nodes = new ArrayList<>();
    private MapView mapView;

    private Act act;

    /**
     * Konstruktor der Klasse MapViewController.
     * Initialisiert die Karte und die Spielsteuerung basierend auf dem aktuellen Akt des Spielers.
     *
     * @param player der Spieler, der sich auf der Karte bewegt und mit den Akten interagiert
     */
    public MapViewController (Player player) {
        this.player = player;
        this.mapView = new MapView();

        switch (player.getCurrentAct()){
            case 1: act = new ActOne(player); break;
            case 2: act = new ActTwo(player); break;
            default:
                System.out.println("Weird"); return;
        }
        //initNodes();
        mapView.printMap(act.getRawMap(), act.getNodes());

        startLoop();
    }

    private void startLoop(){
        while(player.isAlive()){
            act.doFieldThing();
            mapView.printMap(act.getRawMap(), act.getNodes());

            act.goToValidDirection(player);
        }
    }
}