package models.map_elements.field_types;

import models.player.player_structure.Player;



/**
 * Die abstrakte Klasse Field repräsentiert ein Spielfeld in einem Akt.
 * Jedes Spielfeld hat ein Symbol und einen Status, der angibt, ob das Feld
 * besiegt wurde. Abgeleitete Klassen müssen die Methode `doFieldThing`
 * implementieren, um spezifische Aktionen für das jeweilige Feld durchzuführen.
 *
 * @author Warawa Alexander
 */
public abstract class Field {

    private String imagePath;

    private boolean fieldBeaten;

    /**
     * Konstruktor für die Klasse Field.
     *
     * @param imagePath das Symbol, das dieses Feld repräsentiert
     */
    public Field(String imagePath){
        this.imagePath = imagePath;
        fieldBeaten = false;
    }

    /**
     * Führt die spezifischen Aktionen aus, die mit diesem Feld verknüpft sind.
     * Diese Methode muss in den abgeleiteten Klassen implementiert werden.
     *
     * @param player der Spieler, der auf diesem Feld agiert
     */
    public abstract void doFieldThing(Player player);

    public String getImagePath() {
        return imagePath;
    }

    public boolean isFieldBeaten() {
        return fieldBeaten;
    }

    public void setFieldBeaten() {
        this.fieldBeaten = true;
    }

}