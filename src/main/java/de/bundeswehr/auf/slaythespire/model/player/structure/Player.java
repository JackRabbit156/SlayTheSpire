package de.bundeswehr.auf.slaythespire.model.player.structure;

import de.bundeswehr.auf.slaythespire.controller.listener.PlayerEventListener;
import de.bundeswehr.auf.slaythespire.events.PlayerBlockEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerDamageEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerEnergyEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerHealthEvent;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.map.act.ActFour;
import de.bundeswehr.auf.slaythespire.model.map.act.ActOne;
import de.bundeswehr.auf.slaythespire.model.map.act.ActTwo;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import javafx.stage.Stage;

import java.util.ArrayList;
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

    private String altImagePath;
    private int block;
    private int currentAct = 1;
    private int currentEnergy;
    private String currentField = "0";
    private int currentHealth;
    private List<Card> deck;
    private int gold;
    private String imagePath;
    private int maxEnergy;
    private int maxHealth;
    private final String name;
    private PlayerEventListener playerEventListener;
    private final PlayerType playerType;
    private List<Potion> potions;
    private Stage primaryStage;
    private Relic relic;
    private String username;

    /**
     * Konstruktor für die Player-Klasse.
     *
     * @param name         Der Name des Characters.
     * @param maxHealth    Die maximale Gesundheit des Spielers.
     * @param maxEnergy    Die maximale Energie des Spielers.
     * @param playerType   Der Typ des Spielers.
     * @param primaryStage die aktuelle Stage
     */
    public Player(String name, int maxHealth, int maxEnergy, PlayerType playerType, Stage primaryStage) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
        this.maxEnergy = maxEnergy;
        this.currentEnergy = this.maxEnergy;
        this.playerType = playerType;
        this.primaryStage = primaryStage;
        this.potions = new ArrayList<>();
    }

    public void addCardToDeck(Card addCard) {
        this.deck.add(addCard);
    }

    public void addPotion(Potion potion) {
        this.potions.add(potion);
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
     * Verringert die aktuelle Gesundheit des Spielers um den angegebenen Schaden.
     *
     * @param dmg            Der Schaden, der dem Spieler zugefügt wird.
     * @param damageFromCard Gibt an, ob der Schaden von einer Karte stammt.
     */
    public void decreaseCurrentHealth(int dmg, boolean damageFromCard) {
        GameSettings.increaseReceivedDamageStats(dmg);
        int tmpDmg;

        notifyDamageReceived(dmg, damageFromCard);

        if (getBlock() - dmg >= 0) {
            setBlock(getBlock() - dmg);
            tmpDmg = 0;
        }
        else {
            tmpDmg = Math.abs(getBlock() - dmg);
            setBlock(0);
        }

        currentHealth -= tmpDmg;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    /**
     * Verringert das Gold des Spielers um einen bestimmten Betrag.
     *
     * @param gold Der Betrag, um den das Gold verringert werden soll.
     */
    public void decreaseGold(int gold) {
        this.gold -= gold;
    }

    public String getActImage() {
        switch (currentAct) {
            case 1:
                return ActOne.IMAGE;
            case 2:
                return ActTwo.IMAGE;
            case 3:
            case 4:
            default:
                return ActFour.IMAGE;
        }
    }

    public String getAltImagePath() {
        return altImagePath;
    }

    public void setAltImagePath(String altImagePath) {
        this.altImagePath = altImagePath;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getCurrentAct() {
        return currentAct;
    }

    public void setCurrentAct(int currentAct) {
        this.currentAct = currentAct;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public String getCurrentField() {
        return currentField;
    }

    public void setCurrentField(String currentField) {
        this.currentField = currentField;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public String getName() {
        return name;
    }

    public PlayerEventListener getPlayerEventListener() {
        return playerEventListener;
    }

    public void setPlayerEventListener(PlayerEventListener playerEventListener) {
        this.playerEventListener = playerEventListener;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public List<Potion> getPotions() {
        return potions;
    }

    public void setPotions(List<Potion> potions) {
        this.potions = potions;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Relic getRelic() {
        return relic;
    }

    public void setRelic(Relic relic) {
        this.relic = relic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
     * Erhöht die aktuelle Energie des Spielers um einen bestimmten Betrag.
     *
     * @param energy Der Betrag, um den die Energie erhöht werden soll.
     */
    public void increaseCurrentEnergy(int energy) {
        currentEnergy += energy;
        notifyEnergyReceived(energy);
    }

    /**
     * Erhöht die aktuelle Gesundheit des Spielers um einen bestimmten Punktwert.
     *
     * @param hp Der Punktwert, um den die Gesundheit erhöht werden soll.
     */
    public void increaseCurrentHealth(int hp) {
        int oldHealth = currentHealth;
        currentHealth += hp;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
        notifyHealthReceived(currentHealth - oldHealth);
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
     * Erhöht die maximale Gesundheit des Spielers um einen bestimmten Punktwert.
     *
     * @param hp Der Punktwert, um den die maximale Gesundheit erhöht werden soll.
     */
    public void increaseMaxHealth(int hp) {
        maxHealth += hp;
    }

    /**
     * Überprüft, ob der Spieler lebt.
     *
     * @return true, wenn die aktuelle Gesundheit größer als 0 ist, andernfalls false.
     */
    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void removeCardFromDeck(Card addCard) {
        this.deck.remove(addCard);
    }

    /**
     * Setzt den Blockwert des Spielers auf 0 zurück.
     */
    public void resetBlock() {
        block = 0;
    }

    /**
     * Setzt die aktuelle Energie des Spielers auf die maximale Energie zurück.
     */
    public void resetEnergy() {
        currentEnergy = maxEnergy;
    }

    protected abstract void initDeck();

    protected abstract void initRelic();

    /**
     * Benachrichtigt den Listener über den empfangenen Blockwert.
     *
     * @param blockAmount Der Betrag des Blocks, der empfangen wurde.
     */
    protected void notifyBlockReceived(int blockAmount) {
        PlayerBlockEvent event = new PlayerBlockEvent(this, blockAmount);
        playerEventListener.onBlockReceived(event);
    }

    /**
     * Benachrichtigt den Listener über den erlittenen Schaden.
     *
     * @param damageAmount   Der Betrag des Schadens, der erlitten wurde.
     * @param damageFromCard Gibt an, ob der Schaden von einer Karte stammt.
     */
    protected void notifyDamageReceived(int damageAmount, boolean damageFromCard) {
        PlayerDamageEvent event = new PlayerDamageEvent(this, damageAmount, damageFromCard);
        playerEventListener.onDamageReceived(event);
    }

    /**
     * Benachrichtigt den Listener über die empfangene Energie.
     *
     * @param energyAmount Der Betrag der Energie, die empfangen wurde.
     */
    protected void notifyEnergyReceived(int energyAmount) {
        PlayerEnergyEvent event = new PlayerEnergyEvent(this, energyAmount);
        playerEventListener.onEnergyReceived(event);
    }

    /**
     * Benachrichtigt den Listener über die empfangene Lebenskraft.
     *
     * @param hpAmount Der Betrag der Lebenskraft, die empfangen wurde.
     */
    protected void notifyHealthReceived(int hpAmount) {
        PlayerHealthEvent event = new PlayerHealthEvent(this, hpAmount);
        playerEventListener.onHealthReceived(event);
    }

}