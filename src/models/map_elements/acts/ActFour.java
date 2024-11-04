package models.map_elements.acts;


import models.enemy.Enemy;
import models.map_elements.Coordinates;
import models.map_elements.Node;
import models.map_elements.field_types.*;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse ActFour ist eine konkrete Implementierung des vierten Aktes im Spiel.
 * Sie legt die Struktur des Aktes fest, indem sie die Positionen und Typen der Felder (z. B. Kampf, Shop, Elite) definiert.
 *
 * <p>Diese Klasse initialisiert die Knotenpunkte und deren Verbindungen und erlaubt es dem Spieler,
 * sich innerhalb des Aktes zu bewegen und spezifische Feldaktionen auszuführen.</p>
 *
 * @author Warawa Alexander
 */
public class ActFour extends Act{

    /**
     * Konstruktor für die Klasse ActFour.
     * Initialisiert den Akt und platziert den Spieler auf dem Startfeld.
     *
     * @param player der Spieler, der sich im Akt bewegen soll
     */
    public ActFour(Player player){
        initNodes(player);
    }
    private void initNodes(Player player){
        Node rest51 = new Node("51", new RestField(), new Coordinates(0, 6));
        Node shop52 = new Node("52", new ShopField(), new Coordinates(0, 4));
        Node elite53 = new Node("53", new EliteField(), new Coordinates(0, 2));
        // TODO Generate Boss - Enemy existiert noch nicht
        Node boss54 = new Node("54", new BossField(new ArrayList<>()), new Coordinates(0, 0));

        nodes.add(rest51);
        nodes.add(shop52);
        nodes.add(elite53);
        nodes.add(boss54);

        rest51.setMiddleNode(shop52);
        shop52.setMiddleNode(elite53);
        elite53.setMiddleNode(boss54);

        // TODO: Marked for Save Func.
        rest51.setPlayer(player);
    }

    /**
     * Führt die spezifizierte Aktion auf dem aktuellen Feld des Spielers aus.
     * Ruft die Methode `doFieldThing()` für das Feld auf, auf dem sich der Spieler befindet.
     */
    @Override
    public void doFieldThing(){
        Node currentNode = getPlayerNode();
        currentNode.doFieldThing();
    }

    @Override
    public String[][] getRawMap(){
        String[][] rawMap= {
                {"  "},
                {"|  "},
                {"  "},
                {"|  "},
                {"  "},
                {"|  "},
                {"  "},

        };

        return rawMap;
    }
}
