package models.enemy;

import models.battle.GameContext;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

    private String imagePath;
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


    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    public String getImagePath(){
        return imagePath;
    }

    /**
     * Eine Action ist entweder ein ganz normaler angriff oder eine Beleidigung des Spielers (genommen aus wittybanter.txt)
     * @param gameContext der gameContext für den angriff
     * @author OF Daniel Willig
     */
    public void action(GameContext gameContext) {
        DifficultyLevel difficulty = GameSettings.getDifficultyLevel();
        int randomNumber = (new Random().nextInt(100) + 1);
        int attackPercentage = 100; //should be normal

        if (difficulty.equals(DifficultyLevel.SUPEREASY)) {
            attackPercentage = 50;
        }
        else if (difficulty.equals(DifficultyLevel.EASY)) {
            attackPercentage = 75;
        }
        else if (difficulty.equals(DifficultyLevel.HARD)) {
            System.out.println("hard not yet implemented");
            //TODO hard
        }
        else if (difficulty.equals(DifficultyLevel.IMPOSSIBLE)) {
            System.out.println("impossible not yet implemented");
            //TODO impossible
        }

        if (attackPercentage >= randomNumber) {
            attack(gameContext);
        }
        else {
            System.out.println(doNothing());
        }
    }

    /**
     * eine Beleidigung des Spielers (genommen aus wittybanter.txt)
     * @author OF Daniel Willig
     * @return call of the enemy
     */
    protected String doNothing(){
        ArrayList<String> wittyBanterList = new ArrayList<>();
        Scanner fileScanner = new Scanner(Enemy.class.getResourceAsStream("wittybanter.txt"));
        while (fileScanner.hasNext())
        {
            wittyBanterList.add(fileScanner.nextLine());
        }

        fileScanner.close();


        return "\u001B[3m" + "\u001B[36m" + wittyBanterList.get(Math.abs(new Random().nextInt() % wittyBanterList.size())) + "\u001B[0m";
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
        GameSettings.increaseDistributedDamageStats(damage);
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
