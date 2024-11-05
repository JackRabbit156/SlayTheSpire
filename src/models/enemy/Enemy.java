package models.enemy;

import models.GameContext;

import java.util.Random;

/**
 * Diese abstrakte Klasse repräsentiert einen allgemeinen Gegner im Spiel.
 * Sie enthält gemeinsame Eigenschaften und Methoden, die für alle spezifischen Gegner gelten.
 *
 * @author Warawa Alexander
 */
public abstract class Enemy {
    private String name;
    private int currentHealth;
    private int maxHealth;

    private int block;

    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     *
     * @param name Der Name des Gegners.
     * @param lowestMaxHealthPossible Der niedrigste mögliche Maximalwert für die Gesundheit.
     * @param highestMaxHealthPossible Der höchste mögliche Maximalwert für die Gesundheit.
     */
    public Enemy(String name, int lowestMaxHealthPossible, int highestMaxHealthPossible) {
        this.name = name;

        this.maxHealth = generateMaxHealth(lowestMaxHealthPossible, highestMaxHealthPossible);
        this.currentHealth = maxHealth;
    }

    /**
     * Führt den Angriff des Gegners aus.
     * Diese Methode muss in den spezifischen Unterklassen implementiert werden.
     *
     * @param gameContext Der aktuelle Spielkontext, der weitere Informationen enthält.
     */
    public abstract void attack(GameContext gameContext);

    /**
     * Generiert einen maximalen Gesundheitswert für den Gegner
     * innerhalb des angegebenen Bereichs.
     *
     * @param lowestMaxHealthPossible Der niedrigste mögliche Maximalwert für die Gesundheit.
     * @param highestMaxHealthPossible Der höchste mögliche Maximalwert für die Gesundheit.
     * @return Der generierte maximale Gesundheitswert.
     */
    private int generateMaxHealth(int lowestMaxHealthPossible, int highestMaxHealthPossible){
        int difference = highestMaxHealthPossible - lowestMaxHealthPossible;

        Random randi = new Random();

        int hp = randi.nextInt(difference+1);

        return lowestMaxHealthPossible + hp;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    /**
     * Fügt dem Gegner Schaden zu. Der Schaden wird abhängig von
     * dem Blockwert des Gegners berücksichtigt.
     *
     * @param damage Der zuzu fügende Schaden.
     */
    public void takeDamage(int damage) {
        if(block == 0){
            currentHealth -= damage;
            if (currentHealth < 0) currentHealth = 0;
        } else {
            block -= damage;
            if (block < 0) {
                currentHealth += block;
                block = 0;
            }
        }

        if (currentHealth < 0)
            currentHealth = 0;
    }

    public void setBlock(int block){
        this.block = block;
    }

    public int getBlock(){
        return block;
    }

    public void addBlock(int block){
        this.block += block;
    }
}
