package de.bundeswehr.auf.slaythespire.model.enemy.structure;

public abstract class Elite extends Enemy {

    /**
     * Konstruktor für die Elite-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     *
     * @param name                     Der Name des Gegners.
     * @param lowestMaxHealthPossible  Der niedrigste mögliche Maximalwert für die Gesundheit.
     * @param highestMaxHealthPossible Der höchste mögliche Maximalwert für die Gesundheit.
     */
    public Elite(String name, int lowestMaxHealthPossible, int highestMaxHealthPossible) {
        super(name, lowestMaxHealthPossible, highestMaxHealthPossible);
    }

}
