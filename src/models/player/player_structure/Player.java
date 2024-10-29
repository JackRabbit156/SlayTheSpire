package models.player.player_structure;

import models.cards.card_structure.Card;
import models.relics.relic_structure.Relic;

import java.util.List;

public abstract class Player {
    // * Variables *
    private final String name;

    private int maxHealth;
    private int currentHealth;

    private int maxEnergy;
    private int currentEnergy;

    private int block;

    private List<Card> deck;

    private Relic relic;


    // * Constructor *
    public Player(String name, int maxHealth, int maxEnergy) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.maxEnergy = maxEnergy;
        this.currentHealth = this.maxHealth;
        this.currentEnergy = this.maxEnergy;
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

    public void loseEnergy(int energy) {
        currentEnergy -= energy;
    }

    public void decreaseCurrentHealth(int dmg) {
        currentHealth -= dmg;
    }

    public void increaseCurrentHealth(int hp) {
        currentHealth += hp;
    }


    // * Getter & Setter *
    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
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
    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
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
}