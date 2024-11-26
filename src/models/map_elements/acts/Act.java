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

    private int mapWidth;
    private int mapHeight;

    public Act(int actLevel, int mapWidth, int mapHeight){
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.actLevel = actLevel;
        nodes = new ArrayList<>();
    }

    public int getMapWidth(){
        return mapWidth;
    }
    public int getMapHeight(){
        return mapHeight;
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

    public void setBeatenNode(Player player, Node node){
        getPlayerNode().setPlayer(null);
        node.setPlayer(player);
    }

    public String getFirstField(){
        return nodes.get(0).getFieldName();
    }

    public String getLastField(){
        return nodes.get(nodes.size()-1).getFieldName();
    }

    public String getCurrentFieldName(){
        return getPlayerNode().getFieldName();
    }

    public Node getCurrentField(){
        return getPlayerNode();
    }
    /**
     * Führt eine spezialisierte Aktion auf dem aktuellen Feld des Aktes aus.
     * Die Aktion ist in den Felder-Klassen definiert.
     */
    public abstract void doFieldThing();

}