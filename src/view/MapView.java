package view;

import helper.ConsoleAssistent;
import models.map_elements.Node;
import models.player.player_structure.Player;

import java.util.List;

/**
 * Die Klasse MapView dient zur Darstellung der Karte eines Aktes im Spiel.
 * Sie ermöglicht das Platzieren der Knotenpunkte auf der Karte und die Ausgabe
 * der Karte in der Konsole.
 *
 * <p>Die Klasse übernimmt das Layout der Karte und setzt den Spieler sowie
 * die Knoten entsprechend ihrer Positionen im Akt auf die Karte.</p>
 *
 * @author Warawa Alexander
 */
public class MapView {
    private Player player;
    private int gap = 40;

    /**
     * Konstruktor der MapView Klasse.
     * Dient der Darstellung der Karte.
     *
     * @param player der Spieler, um seine Informationen anzuzeigen.
     */
    public MapView(Player player){
        this.player = player;
    }


    /**
     * Gibt die Karte eines Aktes auf der Konsole aus, nachdem alle Knoten
     * und der Spieler auf der Karte platziert wurden.
     *
     * @param actRawMap das zweidimensionale String-Array, das das rohe Layout
     *                  der Karte für den Akt repräsentiert
     * @param nodes eine Liste von Knotenpunkten (Nodes), die im Akt enthalten sind
     */
    public void printMap(String[][] actRawMap, List<Node> nodes){
        String[][] rawMap = actRawMap;

        setNodesOnMap(rawMap, nodes);

        printPlayerInformation();

        printMapLegend();

        // Gibt die Karte Zeile für Zeile auf der Konsole aus
        for(int i = 0; i<  rawMap.length; i++){
            String line = "";
            for(int j = 0; j<  rawMap[0].length; j++){
                line += rawMap[i][j];
            }
            System.out.println(ConsoleAssistent.repeat(gap + 20, " ") +line);
        }
    }

    private void setNodesOnMap(String[][] rawMap, List<Node> nodes) {

        for(int i =0; i< nodes.size(); i++){
            int posX = nodes.get(i).getX();
            int posY = nodes.get(i).getY();

            if(nodes.get(i).getPlayer() != null)
                rawMap[posY][posX]  = nodes.get(i).getPlayer().getSymbol();
            else
                rawMap[posY][posX]  = nodes.get(i).getSymbol();
        }
    }

    private void printPlayerInformation(){
        System.out.printf(ConsoleAssistent.repeat(gap, " ") +"Player \t\t HP \t\tGold \t\t Act \t\t Floor \t\t Deck%n");
        System.out.printf(ConsoleAssistent.repeat(gap, " ") +"%-12s %d/%-7d %-12d %-11d %-11s %d%n%n", player.getPlayerType(), player.getCurrentHealth(), player.getMaxHealth(),player.getGold(), player.getCurrentAct(), player.getCurrentField(), player.getDeck().size());
    }

    private void printMapLegend(){
        System.out.printf(ConsoleAssistent.repeat(gap, " ") +"❓ Unknown%n");
        System.out.printf(ConsoleAssistent.repeat(gap, " ") +"❇ Event%n");
        System.out.printf(ConsoleAssistent.repeat(gap, " ") +"\uD83D\uDC5C Merchant%n");
        System.out.printf(ConsoleAssistent.repeat(gap, " ") +"T Treasure%n");
        System.out.printf(ConsoleAssistent.repeat(gap, " ") +"\uD83D\uDCA4 Rest%n");
        System.out.printf(ConsoleAssistent.repeat(gap, " ") +"👹 Enemy%n");
        System.out.printf(ConsoleAssistent.repeat(gap, " ") +"\uD83D\uDCA2 Elite%n");
        System.out.printf(ConsoleAssistent.repeat(gap, " ") +"\uD83D\uDCAA Boss%n");
        System.out.printf(ConsoleAssistent.repeat(gap, " ") +"⚒ Player%n");
    }
}
