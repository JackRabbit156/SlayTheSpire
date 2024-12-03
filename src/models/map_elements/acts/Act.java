package models.map_elements.acts;

import models.map_elements.Node;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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

    protected static final Random rnd = new Random();

    protected List<Node> nodes;

    private final int actLevel;
    private final int mapHeight;
    private final int mapWidth;

    public Act(int actLevel, int mapWidth, int mapHeight) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.actLevel = actLevel;
        nodes = new ArrayList<>();
    }

    /**
     * Führt eine spezialisierte Aktion auf dem aktuellen Feld des Aktes aus.
     * Die Aktion ist in den Felder-Klassen definiert.
     */
    public abstract void doFieldThing();

    public int getActLevel() {
        return actLevel;
    }

    public Node getCurrentField() {
        return getPlayerNode();
    }

    public String getCurrentFieldName() {
        return getPlayerNode().getFieldName();
    }

    public String getFirstField() {
        return nodes.get(0).getFieldName();
    }

    public String getLastField() {
        return nodes.get(nodes.size() - 1).getFieldName();
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    /**
     * Gibt die Liste der Knotenpunkte zurück, die Teil des aktuellen Aktes sind.
     *
     * @return eine Liste von Nodes, die zu diesem Akt gehören
     */
    public List<Node> getNodes() {
        return this.nodes;
    }

    public void setBeatenNode(Player player, Node node) {
        getPlayerNode().setPlayer(null);
        node.setPlayer(player);
    }

    protected Node getNoteByName(String name) {
        for (Node node : nodes) {
            if (node.getFieldName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    /**
     * Findet und gibt den Node zurück, auf dem sich der Spieler aktuell befindet.
     * Falls kein Node den Spieler enthält, wird `null` zurückgegeben.
     *
     * @return der Node, auf dem der Spieler steht, oder `null`, falls keiner gefunden wurde
     */
    protected Node getPlayerNode() {
        for (Node node : nodes) {
            if (node.getPlayer() != null) {
                return node;
            }
        }
        return null;
    }

}