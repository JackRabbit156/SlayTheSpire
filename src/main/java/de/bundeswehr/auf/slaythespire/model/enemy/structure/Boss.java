package de.bundeswehr.auf.slaythespire.model.enemy.structure;

public abstract class Boss extends Enemy {

    /**
     * Konstruktor für die Boss-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     *
     * @param name                     Der Name des Gegners.
     * @param lowestMaxHealthPossible  Der niedrigste mögliche Maximalwert für die Gesundheit.
     * @param highestMaxHealthPossible Der höchste mögliche Maximalwert für die Gesundheit.
     */
    public Boss(String name, int lowestMaxHealthPossible, int highestMaxHealthPossible) {
        super(name, lowestMaxHealthPossible, highestMaxHealthPossible);
    }

}
