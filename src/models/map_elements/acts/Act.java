package models.map_elements.acts;

import models.game_settings.GameSettings;
import models.map_elements.Node;
import models.player.player_structure.Player;

import java.util.*;


/**
 * Die abstrakte Klasse Act repräsentiert einen generischen Akt im Spiel.
 * Sie enthält Methoden, die die Bewegung und Aktionen des Spielers innerhalb des Aktes
 * steuern und bietet eine Grundstruktur für spezifische Akte (z. B. ActOne und ActTwo).
 *
 * <p>Ein Akt besteht aus einer Liste von Nodes (Knotenpunkten), die miteinander verbunden sind
 * und zwischen denen sich der Spieler bewegen kann.</p>
 *
 * @author Warawa Alexander
 */
public abstract class Act {
    private int actLevel;

    protected List<Node> nodes;

    public Act(int actLevel){
        this.actLevel = actLevel;
        nodes = new ArrayList<>();
    }

    /**
     * Gibt die Liste der Knotenpunkte zurück, die Teil des aktuellen Aktes sind.
     *
     * @return eine Liste von Nodes, die zu diesem Akt gehören
     */
    public List<Node> getNodes(){
        return this.nodes;
    }

    /**
     * Erlaubt dem Spieler, sich in eine gültige Richtung zu bewegen, basierend auf den
     * vorhandenen Verbindungen des aktuellen Knotens.
     *
     * <p>Es werden mögliche Richtungen angezeigt und die Nutzereingabe wird verwendet,
     * um die Richtung zu wählen. Falls die Eingabe gültig ist, wird der Spieler an die
     * neue Position verschoben.</p>
     *
     * @param player der Spieler, der sich im aktuellen Akt bewegt
     */
    public boolean goToValidDirection(Player player) {
        Node node = getPlayerNode();
        HashMap<String, Node> nextNodes = new HashMap<>();

        if(node.getLeftNode() != null){
            nextNodes.put("left", node.getLeftNode());
        }
        if(node.getMiddleNode() != null){
            nextNodes.put("straight", node.getMiddleNode());
        }
        if(node.getRightNode() != null){
            nextNodes.put("right", node.getRightNode());
        }
        System.out.println("\n(open menu with 'menu')");
        System.out.println("\nPossible direction/s: \n");
        for (Map.Entry<String, Node> entry : nextNodes.entrySet()) {
            String direction = entry.getKey(); // Wert (Richtung als String)
            System.out.println(" - " + direction);
        }
        System.out.print("Choose: ");
        String userInput = new Scanner(System.in).next();

        if(userInput.equals("menu")){
            int menuResult = GameSettings.openGameMenu(player);
            switch (menuResult){
                case 2:
                case 4: return false;
            }
        }

        if(nextNodes.get(userInput) != null){
            node.setPlayer(null);
            nextNodes.get(userInput).setPlayer(player);
            System.out.println("\nYeah!\n");
        }
        return true;
    }

    /**
     * Findet und gibt den Node zurück, auf dem sich der Spieler aktuell befindet.
     * Falls kein Node den Spieler enthält, wird `null` zurückgegeben.
     *
     * @return der Node, auf dem der Spieler steht, oder `null`, falls keiner gefunden wurde
     */
    protected Node getPlayerNode(){
        for(int i = 0; i< nodes.size(); i++){
            if(nodes.get(i).getPlayer() != null){
                return nodes.get(i);
            }
        }
        return null;
    }

    public int getActLevel() {
        return actLevel;
    }

    protected Node getNoteByName(String name){
        for(int i = 0; i< nodes.size(); i++){
            if(nodes.get(i).getFieldName().equals(name)){
                return nodes.get(i);
            }
        }
        return null;
    }

    public String getFirstField(){
        return nodes.get(0).getFieldName();
    }

    public String getLastField(){
        return nodes.get(nodes.size()-1).getFieldName();
    }

    public String getCurrentField(){
        return getPlayerNode().getFieldName();
    }
    /**
     * Führt eine spezialisierte Aktion auf dem aktuellen Feld des Aktes aus.
     * Die Aktion ist in den Felder-Klassen definiert.
     */
    public abstract void doFieldThing();

    /**
     * Gibt eine 2D-Kartenrepräsentation des Aktes zurück.
     * Diese Methode wird in den Unterklassen definiert und bietet eine grafische Darstellung des Aktes.
     *
     * @return ein zweidimensionales Array, das die Karte des Aktes darstellt
     */
    public abstract String[][] getRawMap();
}