package models.map_elements.acts;

import models.map_elements.Node;
import models.player.player_structure.Player;

/**
 * Die Klasse ActTwo ist eine konkrete Implementierung des zweiten Aktes im Spiel.
 * Sie legt die Struktur des Aktes fest, indem sie die Positionen und Typen der Felder (z. B. Kampf, Shop, Elite) definiert.
 *
 * <p>Diese Klasse initialisiert die Knotenpunkte und deren Verbindungen und erlaubt es dem Spieler,
 * sich innerhalb des Aktes zu bewegen und spezifische Feldaktionen auszuführen.</p>
 *
 * @author Warawa Alexander
 */
public class ActTwo extends Act {
    private static final int MAP_WIDTH = 7;
    private static final int MAP_HEIGHT= 16;
    /**
     * Konstruktor für die Klasse ActTwo.
     * Initialisiert den Akt und platziert den Spieler auf dem Startfeld, mit der Option einen Spieler auf einem bestimmten Floor zu spawnen.
     *
     * @param player der Spieler, der sich im Akt bewegen soll
     * @param loadingFromFile ob der Spieler von einer Datei geladen wurde.
     */
    public ActTwo(Player player, boolean loadingFromFile){
        super(2, MAP_WIDTH, MAP_HEIGHT);
        initNodes();

        Node playerNode = getNoteByName(player.getCurrentField());
        if(playerNode == null){
            System.out.println("ERROR");
            return;
        }

        playerNode.setPlayer(player);
        if(loadingFromFile)
            playerNode.setFieldBeaten();
    }

    private void initNodes(){

    }

    @Override
    public void doFieldThing() {

    }

    @Override
    public String[][] getRawMap() {
        return new String[0][];
    }
}
