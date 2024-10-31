package models.map_elements.acts;

import models.map_elements.Coordinates;
import models.map_elements.Node;
import models.map_elements.field_types.*;
import models.player.player_structure.Player;

/**
 * Die Klasse ActOne ist eine konkrete Implementierung des ersten Aktes im Spiel.
 * Sie legt die Struktur des Aktes fest, indem sie die Positionen und Typen der Felder (z. B. Kampf, Shop, Elite) definiert.
 *
 * <p>Diese Klasse initialisiert die Knotenpunkte und deren Verbindungen und erlaubt es dem Spieler,
 * sich innerhalb des Aktes zu bewegen und spezifische Feldaktionen auszuführen.</p>
 *
 * @author Warawa Alexander
 */
public class ActOne extends Act {
    /**
     * Konstruktor für die Klasse ActOne.
     * Initialisiert den Akt und platziert den Spieler auf dem Startfeld.
     *
     * @param player der Spieler, der sich im Akt bewegen soll
     */
    public ActOne(Player player){
        //this.player = player;
        initNodes(player);
    }

    private void initNodes(Player player){
        Node start1 = new Node("1", new EnemyField(), new Coordinates(3, 16));
        Node fight2 = new Node("2", new EnemyField(), new Coordinates(3, 14));
        Node unknown3 = new Node("3", new UnknownField(), new Coordinates(1, 12));
        Node fight4 = new Node("4", new EnemyField(), new Coordinates(5, 12));
        Node fight5 = new Node("5", new EnemyField(), new Coordinates(0, 10));
        Node elite6 = new Node("6", new EliteField(), new Coordinates(2, 10));
        Node fight7 = new Node("7", new EnemyField(), new Coordinates(5, 10));
        Node shop8 = new Node("8", new ShopField(), new Coordinates(2, 8));
        Node unknown9 = new Node("9", new UnknownField(), new Coordinates(5, 8));
        Node event10 = new Node("10", new EventField(), new Coordinates(1, 6));
        Node fight11 = new Node("11", new EnemyField(), new Coordinates(4, 6));
        Node fight12 = new Node("12", new EnemyField(), new Coordinates(6, 6));
        Node unknown13 = new Node("13", new UnknownField(), new Coordinates(4, 4));
        Node elite14 = new Node("14", new EliteField(), new Coordinates(6, 4));
        Node rest15 = new Node("15", new RestField(), new Coordinates(4, 2));
        Node boss16 = new Node("16", new BossField(), new Coordinates(4, 0));

        nodes.add(start1);
        nodes.add(fight2);
        nodes.add(unknown3);
        nodes.add(fight4);
        nodes.add(fight5);
        nodes.add(elite6);
        nodes.add(fight7);
        nodes.add(shop8);
        nodes.add(unknown9);
        nodes.add(event10);
        nodes.add(fight11);
        nodes.add(fight12);
        nodes.add(unknown13);
        nodes.add(elite14);
        nodes.add(rest15);
        nodes.add(boss16);

        start1.setMiddleNode(fight2);
        fight2.setLeftNode(unknown3);
        fight2.setRightNode(fight4);
        unknown3.setLeftNode(fight5);
        unknown3.setRightNode(elite6);
        fight4.setMiddleNode(fight7);
        fight5.setRightNode(event10);
        elite6.setMiddleNode(shop8);
        fight7.setMiddleNode(unknown9);
        shop8.setLeftNode(event10);
        unknown9.setLeftNode(fight11);
        unknown9.setRightNode(fight12);
        event10.setRightNode(rest15);
        fight11.setMiddleNode(unknown13);
        fight12.setMiddleNode(elite14);
        unknown13.setMiddleNode(rest15);
        elite14.setLeftNode(rest15);
        rest15.setMiddleNode(boss16);

        // TODO: Marked for Save Func.
        start1.setPlayer(player);
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
                {"  ", "  ", "  ", "  ", "", "  ", "  "},
                {"  ", "  ", "  ", "  ", "| ", "  ", "  "},
                {"  ", "┌ ", "──", "──", "", "  ", "  "},
                {"  ", "| ", "  ", "  ", "| ", "──", "┐ "},
                {"  ", "| ", "  ", "  ", "", "  ", ""},
                {"  ", "| ", "  ", "  ", "| ", "  ", "| "},
                {"┌ ", "", "┐ ", "  ", "", "  ", ""},
                {"| ", "  ", "| ", "  ", "└─", "┬─", "┘ "},
                {"| ", "  ", "", "  ", "  ", "", "  "},
                {"| ", "  ", "| ", "  ", "  ", "| ", "  "},
                {"", "  ", "", "  ", "  ", "", "  "},
                {"└─", "┬─", "┘ ", "  ", "  ", "| ", "  "},
                {"  ", "", "  ", "  ", "  ", "", "  "},
                {"  ", "└─", "──", "┬─", "──", "┘ ", " "},
                {"  ", "  ", "  ", "", "  ", "  ", "  "},
                {"  ", "  ", "  ", "| ", "  ", "  ", "  "},
                {"  ", "  ", "  ", "", "  ", "  ", "  "}, // 16/3
        };

        return rawMap;
    }
}
