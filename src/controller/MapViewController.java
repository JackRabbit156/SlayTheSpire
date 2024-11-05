package controller;

import models.game_settings.GameCounter;
import models.game_settings.GameSettings;
import models.load_save_game_elements.GameSaveManager;
import models.map_elements.acts.Act;
import models.map_elements.acts.ActFour;
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

    private MapView mapView;

    private Act act;

    /**
     * Konstruktor der Klasse MapViewController.
     * Initialisiert die Karte und die Spielsteuerung basierend auf dem aktuellen Akt des Spielers.
     *
     * @param player der Spieler, der sich auf der Karte bewegt und mit den Akten interagiert
     * @param loadingFromFile ob das Spiel aus einem Spielstand geladen wird, oder nicht.
     */
    public MapViewController (Player player, boolean loadingFromFile) {
        this.player = player;
        this.mapView = new MapView(player);

        switch (player.getCurrentAct()){
            case 1: act = new ActOne(player, loadingFromFile); break;
            case 2: act = new ActTwo(player, loadingFromFile); break; // TODO: Für GUI
            case 3:  break; // TODO: Act 3, Für GUI
            case 4: act = new ActFour(player, loadingFromFile); break;
            default:
                System.out.println("Weird"); return;
        }

        /*GameSaveManager gameSaveManager = new GameSaveManager();
        gameSaveManager.saveGame(player);*/
        startLoop();
    }

    private void startLoop(){
        // Startet den Timer, der die Spielzeit aufzeichnet
        GameSettings.startTimer();

        while(player.isAlive()){
            // Feldaktion wie "Kampf", "Shop", etc..
            act.doFieldThing();

            // Wenn der Spieler nach "act.doFieldThing()" keine HP mehr hat, dann Game Over
            if(!player.isAlive())
                break;

            player.setCurrentField(getCurrentFieldFromAct()); // Speichert den aktuellen Floor, damit es nach dem Speichern wieder geladen werden kann.

            // Wenn der Spieler den Boss besiegt hat, dann muss er zum neuen Akt und wieder kämpfen(act.doFieldThing), deshalb continue;.
            if(player.getCurrentField().equals(getLastField())) {
                act = nextAct(player.getCurrentAct());
                continue;
            }

            mapView.printMap(act.getRawMap(), act.getNodes());
            boolean validWay =  act.goToValidDirection(player);
            if(!validWay)
                return;
        }

        GameSettings.stopTimer();
    }

    private Act nextAct(int currentAct){
        Act actToReturn = null;
        switch (currentAct){
            case 1: actToReturn = new ActFour(player, false); //return new ActTwo(player);
            case 2: actToReturn = new ActFour(player, false);
            case 3: break;
            case 4: actToReturn = new ActFour(player, false);
        }
        return actToReturn;
    }

    public String getLastField(){
        return act.getLastField();
    }

    public String getCurrentFieldFromAct(){
        return act.getCurrentField();
    }
}