package de.bundeswehr.auf.slaythespire.model.map.field;

import de.bundeswehr.auf.slaythespire.model.player.structure.Player;



/**
 * Die abstrakte Klasse Field repräsentiert ein Spielfeld in einem Akt.
 * Jedes Spielfeld hat ein Bild. Abgeleitete Klassen müssen die Methode `doFieldThing`
 * implementieren, um spezifische Aktionen für das jeweilige Feld durchzuführen.
 *
 * @author Warawa Alexander
 */
public abstract class Field {

    private String imagePath;

    /**
     * Konstruktor für die Klasse Field.
     *
     * @param imagePath das Symbol, das dieses Feld repräsentiert
     */
    public Field(String imagePath){
        this.imagePath = imagePath;
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

}