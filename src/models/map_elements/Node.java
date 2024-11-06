package models.map_elements;

import models.map_elements.field_types.Field;
import models.player.player_structure.Player;
// https://gist.github.com/suntong/3d411a7e7e737efb2502a17d4ce01d62

/**
 * Die Klasse Node repräsentiert einen Knotenpunkt auf der Karte eines Aktes.
 * Jeder Knoten kann mit einem Feldtyp verknüpft sein, auf dem bestimmte
 * Aktivitäten stattfinden können, und kann Links-, Mittel- und Rechtsknoten
 * haben, die zu anderen Knoten führen.
 *
 * <p>Ein Knoten kann auch einen Spieler enthalten, der sich an dieser Stelle
 * befindet, sowie Koordinaten, die die Position des Knotens auf der Karte
 * bestimmen.</p>
 *
 * @author Warawa Alexander
 */
public class Node {

    private String name;

    private Field field;

    private Node leftNode;
    private Node middleNode;
    private Node rightNode;

    private Player player = null;

    private Coordinates coordinates;

    /**
     * Konstruktor für die Klasse Node.
     *
     * @param name der Name des Knotens
     * @param field das Feld, das mit diesem Knoten verknüpft ist
     * @param coordinates die Koordinaten des Knotens auf der Karte
     */
    public Node(String name, Field field, Coordinates coordinates) {
        this.name = name;
        this.field = field;

        this.coordinates = coordinates;
    }

    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public String getSymbol(){
        return field.getSymbol();
    }

    public int getX(){
        return coordinates.getPosX();
    }

    public int getY(){
        return coordinates.getPosY();
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getMiddleNode() {
        return middleNode;
    }

    public void setMiddleNode(Node moddleNode) {
        this.middleNode = moddleNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public boolean isFieldBeaten() {
        return field.isFieldBeaten();
    }

    public void setFieldBeaten() {
        field.setFieldBeaten();
    }

    public String getFieldName() {
        return name;
    }
    /**
     * Führt die Aktionen aus, die mit dem Feld verknüpft sind, an dem sich der Spieler
     * befindet.
     */
    public void doFieldThing(){
        field.doFieldThing(player);
    }
}
