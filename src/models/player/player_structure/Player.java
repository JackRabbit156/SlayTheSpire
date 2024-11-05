package models.player.player_structure;

import events.PlayerBlockEvent;
import events.PlayerDamageEvent;
import listener.PlayerEventListener;
import models.cards.card_structure.Card;
import models.game_settings.GameSettings;
import models.relics.relic_structure.Relic;

import java.util.List;

public abstract class Player {
    // * Variables *
    private final String name;

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

    // * Constructor *
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
        this.listener = null;
    }

    // * Methods *
    protected abstract void initDeck();

    protected abstract void initRelic();

    //TODO maybe in takeDamage() if(currentHealth <= 0) {alive = false};
    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void resetEnergy() {
        currentEnergy = maxEnergy;
    }

    public void resetBlock() {
        block = 0;
    }

    public void decreaseCurrentEnergy(int energy) {
        currentEnergy -= energy;
        GameSettings.increaseEnergySpentStats(energy);
    }
    public void increaseCurrentEnergy(int energy) {
        currentEnergy += energy;
    }

    public void decreaseCurrentHealth(int dmg, boolean damageFromCard) {
        currentHealth -= dmg;
        if (currentHealth < 0)
            currentHealth = 0;
        GameSettings.increaseReceivedDamageStats(dmg);
        if (damageFromCard) {
            notifyDamageReceived(dmg, true);
        }
    }

    public void increaseCurrentHealth(int hp) {
        currentHealth += hp;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public void increaseMaxHealth(int hp) {
        maxHealth += hp;
    }

    public void increaseGold(int gold) {
        this.gold += gold;
        GameSettings.increaseGoldStats(gold);
    }

    public void decreaseGold(int gold) {
        this.gold -= gold;
    }

    public void increaseBlock(int block) {
        this.block += block;
        notifyBlockReceived(block);
    }

    protected void notifyBlockReceived(int blockAmount) {
        PlayerBlockEvent event = new PlayerBlockEvent(this, blockAmount);
        listener.onBlockReceived(event);
    }

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

    public void setListener(PlayerEventListener listener) {
        this.listener = listener;
    }

    public String getCurrentField() {
        return currentField;
    }

    public void setCurrentField(String currentField) {
        this.currentField = currentField;
    }
}