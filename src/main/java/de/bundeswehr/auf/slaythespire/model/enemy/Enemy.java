package de.bundeswehr.auf.slaythespire.model.enemy;

import de.bundeswehr.auf.slaythespire.controller.listener.EnemyEventListener;
import de.bundeswehr.auf.slaythespire.events.EnemyDamageEvent;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy_card.InsultEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;

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

    private static final Random rnd = new Random();

    private int block;
    private int currentHealth;
    private int enemyCardToBePlayed;
    private List<EnemyCard> enemyDeck;
    private EnemyEventListener enemyEventListener;
    private String imagePath;
    private final EnemyCard insult = new InsultEnemyCard();
    private EnemyCard intent;
    private final int maxHealth;
    private final String name;
    private final List<String> wittyBanterList = new ArrayList<>();

    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     *
     * @param name                     Der Name des Gegners.
     * @param lowestMaxHealthPossible  Der niedrigste mögliche Maximalwert für die Gesundheit.
     * @param highestMaxHealthPossible Der höchste mögliche Maximalwert für die Gesundheit.
     */
    public Enemy(String name, int lowestMaxHealthPossible, int highestMaxHealthPossible) {
        this.name = name;
        maxHealth = generateMaxHealth(lowestMaxHealthPossible, highestMaxHealthPossible);
        currentHealth = maxHealth;
        try (Scanner fileScanner = new Scanner(Enemy.class.getResourceAsStream("/wittybanter.txt"))) {
            while (fileScanner.hasNext()) {
                wittyBanterList.add(fileScanner.nextLine());
            }
        }
    }

    /**
     * Eine Action ist entweder ein ganz normaler angriff oder eine Beleidigung des Spielers (genommen aus wittybanter.txt)
     *
     * @param gameContext der gameContext für den angriff
     * @author OF Daniel Willig
     */
    public void action(GameContext gameContext) {
        if (getIntent().equals(insult)) {
            LoggingAssistant.log(doNothing(), Color.ITALIC, Color.CYAN);
        }
        else {
            LoggingAssistant.log("attacking", Color.ITALIC, Color.BLUE);
            attack(gameContext);
        }
    }

    public void addBlock(int block) {
        this.block += block;
    }

    /**
     * Führt den Angriff des Gegners aus.
     * Diese Methode muss in den spezifischen Unterklassen implementiert werden.
     *
     * @param gameContext Der aktuelle Spielkontext, der weitere Informationen enthält.
     */
    public abstract void attack(GameContext gameContext);

    /**
     * berechnet die Aktion bevor sie ausgeführt wird. Wie bei diesem Film indem die Polizei Bösewichte fangen bevor sie Straftaten begehen.
     *
     * @author OF Daniel Willig
     */
    public void calculateIntent() {
        DifficultyLevel difficulty = GameSettings.getDifficultyLevel();
        int randomNumber = (rnd.nextInt(100) + 1);
        if (difficulty.getAttackPercentage() >= randomNumber) {
            setEnemyCardToBePlayed(rnd.nextInt(getEnemyDeck().size()));
            setIntent(getEnemyDeck().get(getEnemyCardToBePlayed()));
        }
        else {
            setIntent(insult);
        }
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getEnemyCardToBePlayed() {
        return enemyCardToBePlayed;
    }

    public void setEnemyCardToBePlayed(int enemyCardToBePlayed) {
        this.enemyCardToBePlayed = enemyCardToBePlayed;
    }

    public List<EnemyCard> getEnemyDeck() {
        return enemyDeck;
    }

    public void setEnemyDeck(List<EnemyCard> enemyDeck) {
        this.enemyDeck = enemyDeck;
    }

    public int getHealth() {
        return currentHealth;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public EnemyCard getIntent() {
        return intent;
    }

    public void setIntent(EnemyCard intent) {
        this.intent = intent;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void setEnemyEventListener(EnemyEventListener enemyEventListener) {
        this.enemyEventListener = enemyEventListener;
    }

    /**
     * Fügt dem Gegner Schaden zu. Der Schaden wird abhängig von
     * dem Blockwert des Gegners berücksichtigt.
     *
     * @param damage Der zuzu fügende Schaden.
     */
    public void takeDamage(int damage) {
        if (block == 0) {
            currentHealth -= damage;
            if (currentHealth < 0) {
                currentHealth = 0;
            }
        }
        else {
            block -= damage;
            if (block < 0) {
                currentHealth += block;
                block = 0;
            }
        }
        GameSettings.increaseDistributedDamageStats(damage);
        if (currentHealth < 0) {
            currentHealth = 0;
        }
        notifyDamageReceived(damage);
    }

    @Override
    public String toString() {
        return "Enemy{" +
                ", name='" + name + '\'' +
                ", health=" + currentHealth + "/" + maxHealth +
                ", block=" + block +
                ", enemyCardToBePlayed=" + enemyCardToBePlayed +
                ", enemyDeck=" + enemyDeck +
                ", insult=" + insult +
                ", intent=" + intent +
                '}';
    }

    /**
     * eine Beleidigung des Spielers (genommen aus wittybanter.txt)
     *
     * @return call of the enemy
     * @author OF Daniel Willig
     */
    protected String doNothing() {
        return wittyBanterList.get(rnd.nextInt( wittyBanterList.size()));
    }

    protected void notifyDamageReceived(int damageAmount) {
        EnemyDamageEvent event = new EnemyDamageEvent(this, damageAmount);
        enemyEventListener.onDamageReceived(event);
        if (!isAlive()) {
            enemyEventListener.onEnemyDeath(this);
        }
    }

    /**
     * Generiert einen maximalen Gesundheitswert für den Gegner
     * innerhalb des angegebenen Bereichs.
     *
     * @param lowestMaxHealthPossible  Der niedrigste mögliche Maximalwert für die Gesundheit.
     * @param highestMaxHealthPossible Der höchste mögliche Maximalwert für die Gesundheit.
     * @return Der generierte maximale Gesundheitswert.
     */
    private int generateMaxHealth(int lowestMaxHealthPossible, int highestMaxHealthPossible) {
        int difference = highestMaxHealthPossible - lowestMaxHealthPossible;

        Random randi = new Random();

        int hp = randi.nextInt(difference + 1);

        return lowestMaxHealthPossible + hp;
    }
}
