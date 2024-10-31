package view;

import models.map_elements.Node;

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

        // Gibt die Karte Zeile für Zeile auf der Konsole aus
        for(int i = 0; i<  rawMap.length; i++){
            String line = "";
            for(int j = 0; j<  rawMap[0].length; j++){
                line += rawMap[i][j];
            }
            System.out.println(line);
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
}
