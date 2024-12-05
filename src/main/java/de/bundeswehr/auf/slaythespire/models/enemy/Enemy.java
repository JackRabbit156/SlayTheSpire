package de.bundeswehr.auf.slaythespire.models.enemy;

import de.bundeswehr.auf.slaythespire.controller.listener.EnemyEventListener;
import de.bundeswehr.auf.slaythespire.events.EnemyDamageEvent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy_card.InsultEnemyCard;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.models.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.models.settings.structure.DifficultyLevel;

import java.util.ArrayList;
import java.util.List;
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
    private EnemyCard intent;

    private String imagePath;
    private int block;

    private List<EnemyCard> enemyDeck;
    private int enemyCardToBePlayed;

    private EnemyCard insult = new InsultEnemyCard();


    private EnemyEventListener enemyEventListener;

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
        enemyEventListener = null;
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
        if (getIntent().equals(insult)) {
            System.out.println(doNothing());
        }
        else {
            attack(gameContext);
        }
    }

    /**
     * berechnet die Aktion bevor sie ausgeführt wird. Wie bei diesem Film indem die Polizei Bösewichte fangen bevor sie Straftaten begehen.
     * @author OF Daniel Willig
     */
    public void calcIntent() {
        DifficultyLevel difficulty = GameSettings.getDifficultyLevel();
        int randomNumber = (new Random().nextInt(100) + 1);
        if (difficulty.getAttackPercentage() >= randomNumber) {
            setEnemyCardToBePlayed(new Random().nextInt(getEnemyDeck().size()));
            setIntent(getEnemyDeck().get(getEnemyCardToBePlayed()));
        }
        else {
            setIntent(insult);
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

    protected void notifyDamageReceived(int damageAmount) {
        EnemyDamageEvent event = new EnemyDamageEvent(this, damageAmount);
        enemyEventListener.onDamageReceived(event);

        if (!isAlive()) {
            enemyEventListener.onEnemyDeath(this);
        }
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

        notifyDamageReceived(damage);
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

    public void setEnemyEventListener(EnemyEventListener enemyEventListener) {
        this.enemyEventListener = enemyEventListener;
    }

    public EnemyCard getIntent() {
        return intent;
    }

    public void setIntent(EnemyCard intent) {
        this.intent = intent;
    }

    public List<EnemyCard> getEnemyDeck() {
        return enemyDeck;
    }

    public void setEnemyDeck(List<EnemyCard> enemyDeck) {
        this.enemyDeck = enemyDeck;
    }

    public int getEnemyCardToBePlayed() {
        return enemyCardToBePlayed;
    }

    public void setEnemyCardToBePlayed(int enemyCardToBePlayed) {
        this.enemyCardToBePlayed = enemyCardToBePlayed;
    }
}
