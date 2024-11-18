package models.player.player_structure;

import events.PlayerBlockEvent;
import events.PlayerDamageEvent;
import javafx.stage.Stage;
import controller.listener.*;
import models.card.card_structure.Card;
import models.game_settings.GameSettings;
import models.relic.relic_structure.Relic;

import java.util.List;

/**
 * Abstrakte Klasse, die die grundlegenden Eigenschaften und Methoden eines Spielers im Spiel definiert.
 * Sie enthält Attribute wie Gesundheit, Energie, Gold, Block und Deck.
 * Diese Klasse dient als Basisklasse für spezifische Spieler-Implementierungen.
 *
 * @author F Alexander Warawa
 * @author OF Daniel Willig
 */
public abstract class Player {
    private Stage primaryStage;
    // * Variables *
    private final String name;
    private String username;

    private int maxHealth;
    private int currentHealth;

    private int maxEnergy;
    private int currentEnergy;

    private int gold;

    private int block;

    private List<Card> deck;

    private Relic relic;

    private int currentAct;

    private String currentField;

    private String symbol;

    private PlayerType playerType;

    private PlayerEventListener listener;

    private String imagePath;

    /**
     * Konstruktor für die Player-Klasse.
     *
     * @param name Der Name des Characters.
     * @param maxHealth Die maximale Gesundheit des Spielers.
     * @param maxEnergy Die maximale Energie des Spielers.
     * @param playerType Der Typ des Spielers.
     * @param symbol Das Symbol, das den Spieler darstellt.
     */
    public Player(String name, int maxHealth, int maxEnergy, PlayerType playerType, String symbol) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.maxEnergy = maxEnergy;
        this.currentHealth = this.maxHealth;
        this.currentEnergy = this.maxEnergy;
        this.gold = 0;
        this.currentAct = 1;
        this.currentField = "1";
        this.playerType = playerType;
        this.symbol = symbol;
        listener = null;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // * Methods *
    protected abstract void initDeck();

    protected abstract void initRelic();

    //TODO maybe in takeDamage() if(currentHealth <= 0) {alive = false};
    /**
     * Überprüft, ob der Spieler lebt.
     *
     * @return true, wenn die aktuelle Gesundheit größer als 0 ist, andernfalls false.
     */
    public boolean isAlive() {
        return currentHealth > 0;
    }

    /**
     * Setzt die aktuelle Energie des Spielers auf die maximale Energie zurück.
     */
    public void resetEnergy() {
        currentEnergy = maxEnergy;
    }

    /**
     * Setzt den Blockwert des Spielers auf 0 zurück.
     */
    public void resetBlock() {
        block = 0;
    }

    /**
     * Verringert die aktuelle Energie des Spielers um einen bestimmten Betrag.
     *
     * @param energy Der Betrag, um den die Energie verringert werden soll.
     */
    public void decreaseCurrentEnergy(int energy) {
        currentEnergy -= energy;
        GameSettings.increaseEnergySpentStats(energy);
    }

    /**
     * Erhöht die aktuelle Energie des Spielers um einen bestimmten Betrag.
     *
     * @param energy Der Betrag, um den die Energie erhöht werden soll.
     */
    public void increaseCurrentEnergy(int energy) {
        currentEnergy += energy;
    }

    /**
     * Verringert die aktuelle Gesundheit des Spielers um den angegebenen Schaden.
     *
     * @param dmg Der Schaden, der dem Spieler zugefügt wird.
     * @param damageFromCard Gibt an, ob der Schaden von einer Karte stammt.
     */
    public void decreaseCurrentHealth(int dmg, boolean damageFromCard) {
        GameSettings.increaseReceivedDamageStats(dmg);
        int tmpDmg = dmg;
        if (damageFromCard) {
            notifyDamageReceived(dmg, true);
        }
        else {
            if (getBlock() - dmg >= 0) {
                setBlock(getBlock() - dmg);
                tmpDmg = 0;
            }
            else {
                tmpDmg = Math.abs(getBlock() - dmg);
                setBlock(0);
            }
        }
        currentHealth -= tmpDmg;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    /**
     * Erhöht die aktuelle Gesundheit des Spielers um einen bestimmten Punktwert.
     *
     * @param hp Der Punktwert, um den die Gesundheit erhöht werden soll.
     */
    public void increaseCurrentHealth(int hp) {
        currentHealth += hp;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    /**
     * Erhöht die maximale Gesundheit des Spielers um einen bestimmten Punktwert.
     *
     * @param hp Der Punktwert, um den die maximale Gesundheit erhöht werden soll.
     */
    public void increaseMaxHealth(int hp) {
        maxHealth += hp;
    }

    /**
     * Erhöht das Gold des Spielers um einen bestimmten Betrag.
     *
     * @param gold Der Betrag, um den das Gold erhöht werden soll.
     */
    public void increaseGold(int gold) {
        this.gold += gold;
        GameSettings.increaseGoldStats(gold);
    }

    /**
     * Verringert das Gold des Spielers um einen bestimmten Betrag.
     *
     * @param gold Der Betrag, um den das Gold verringert werden soll.
     */
    public void decreaseGold(int gold) {
        this.gold -= gold;
    }

    /**
     * Erhöht den Blockwert des Spielers um einen bestimmten Betrag.
     *
     * @param block Der Betrag, um den der Blockwert erhöht werden soll.
     */
    public void increaseBlock(int block) {
        this.block += block;
        notifyBlockReceived(block);
    }

    /**
     * Benachrichtigt den Listener über den empfangenen Blockwert.
     *
     * @param blockAmount Der Betrag des Blocks, der empfangen wurde.
     */
    protected void notifyBlockReceived(int blockAmount) {
        PlayerBlockEvent event = new PlayerBlockEvent(this, blockAmount);
        listener.onBlockReceived(event);
    }

    /**
     * Benachrichtigt den Listener über den empfangenen Schaden.
     *
     * @param damageAmount Der Betrag des Schadens, der empfangen wurde.
     * @param damageFromCard Gibt an, ob der Schaden von einer Karte stammt.
     */
    protected void notifyDamageReceived(int damageAmount, boolean damageFromCard) {
        PlayerDamageEvent event = new PlayerDamageEvent(this, damageAmount, damageFromCard);
        listener.onDamageReceived(event);
    }



    // * Getter & Setter *
    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public void addCardToDeck(Card addCard) {
        this.deck.add(addCard);
    }

    public String getName() {
        return name;
    }
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth){
        this.currentHealth = currentHealth;
    }
    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public Relic getRelic() {
        return relic;
    }

    public void setRelic(Relic relic) {
        this.relic = relic;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setCurrentAct(int currentAct){
        this.currentAct = currentAct;
    }

    public int getCurrentAct(){
        return currentAct;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public PlayerEventListener getListener() {
        return listener;
    }

    public void setListener(PlayerEventListener listener){
            this.listener = listener;
    }
    public String getCurrentField() {
        return currentField;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCurrentField(String currentField) {
        this.currentField = currentField;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}