package de.bundeswehr.auf.slaythespire.model.player.structure;

import de.bundeswehr.auf.slaythespire.controller.listener.InventoryEventListener;
import de.bundeswehr.auf.slaythespire.controller.listener.PlayerEventListener;
import de.bundeswehr.auf.slaythespire.events.*;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;
import de.bundeswehr.auf.slaythespire.model.map.act.ActFour;
import de.bundeswehr.auf.slaythespire.model.map.act.ActOne;
import de.bundeswehr.auf.slaythespire.model.map.act.ActTwo;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstrakte Klasse, die die grundlegenden Eigenschaften und Methoden eines Spielers im Spiel definiert.
 * Sie enthält Attribute wie Gesundheit, Energie, Gold, Block und Deck.
 * Diese Klasse dient als Basisklasse für spezifische Spieler-Implementierungen.
 *
 * @author F Alexander Warawa
 * @author OF Daniel Willig
 */
public abstract class Player implements Entity {

    private String altImagePath;
    private int block;
    private int currentAct = 1;
    private int currentEnergy;
    private String currentField = "0";
    private int currentHealth;
    private double damageFactor;
    private int damageModifier;
    private List<Card> deck;
    private final Map<Effect, Integer> effects = new HashMap<>();
    private int gold;
    private String imagePath;
    private final List<InventoryEventListener> inventoryEventListeners = new ArrayList<>();
    private int maxEnergy;
    private int maxHealth;
    private final String name;
    private final List<PlayerEventListener> playerEventListeners = new ArrayList<>();
    private final PlayerType playerType;
    private List<Potion> potions = new ArrayList<>();
    private Stage primaryStage;
    private List<Relic> relics = new ArrayList<>();
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
    }

    public void addCardToDeck(Card card) {
        deck.add(card);
        notifyCardEvent(new InventoryEvent(this, InventoryEvent.Direction.GAIN, InventoryEvent.Type.CARD, card));
    }

    @Override
    public void addDamageFactor(double factor) {
        this.damageFactor *= factor;
    }

    @Override
    public void addDamageModifier(int modifier) {
        this.damageModifier += modifier;
    }

    public void addEffect(Effect effect, int value) {
        notifyEffect(effect, value);
        effects.put(effect, effects.getOrDefault(effect, 0) + value);
    }

    public void addInventoryEventListener(InventoryEventListener inventoryEventListener) {
        inventoryEventListeners.add(inventoryEventListener);
    }

    public void addPlayerEventListener(PlayerEventListener playerEventListener) {
        playerEventListeners.add(playerEventListener);
    }

    public void addPotion(Potion potion) {
        potions.add(potion);
        notifyPotionEvent(new InventoryEvent(this, InventoryEvent.Direction.GAIN, InventoryEvent.Type.POTION, potion));
    }

    public void addRelic(Relic relic) {
        relics.add(relic);
        notifyRelicEvent(new InventoryEvent(this, InventoryEvent.Direction.GAIN, InventoryEvent.Type.RELIC, relic));
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
     * @param damage         Der Schaden, der dem Spieler zugefügt wird.
     * @param damageFromCard Gibt an, ob der Schaden von einer eigenen Karte stammt.
     */
    public void decreaseCurrentHealth(int damage, boolean damageFromCard) {
        decreaseCurrentHealth(damage, damageFromCard, null);
    }

    public void decreaseCurrentHealth(int damage, boolean damageFromCard, GameContext gameContext) {
        if (gameContext != null) {
            triggerEffect(EffectTrigger.BEFORE_ATTACK_SOURCE, gameContext, gameContext.getSelectedEnemy());
            triggerEffect(EffectTrigger.BEFORE_ATTACK_TARGET, gameContext, this);
        }
        int damageAfterEffects = (int) ((damage + damageModifier) * damageFactor);
        int oldHealth = currentHealth;
        int damageAfterBlock;
        if (block - damageAfterEffects >= 0) {
            block -= damageAfterEffects;
            damageAfterBlock = 0;
        }
        else {
            damageAfterBlock = Math.abs(block - damageAfterEffects);
            block = 0;
        }
        currentHealth -= damageAfterBlock;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
        GameSettings.increaseReceivedDamageStats(oldHealth - currentHealth);
        notifyDamageReceived(oldHealth - currentHealth, damageFromCard);
        triggerEffect(EffectTrigger.AFTER_ATTACK, gameContext, this);
    }

    protected void triggerEffect(EffectTrigger trigger, GameContext gameContext, Entity target) {
        for (Effect effect : effects.keySet()) {
            if (effect.getEffectTrigger() == trigger) {
                effect.apply(gameContext, target);
            }
        }
    }

    /**
     * Verringert das Gold des Spielers um einen bestimmten Betrag.
     *
     * @param gold Der Betrag, um den das Gold verringert werden soll.
     */
    public void decreaseGold(int gold) {
        this.gold -= gold;
        notifyGoldEvent(new InventoryEvent(this, InventoryEvent.Direction.LOSE, InventoryEvent.Type.GOLD, gold));
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
        notifyLevelEvent(new InventoryEvent(this, InventoryEvent.Direction.GAIN, InventoryEvent.Type.LEVEL, currentField));
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

    @Override
    public int getEffectCounter(Effect effect) {
        return effects.getOrDefault(effect, 0);
    }

    @Override
    public Map<Effect, Integer> getEffects() {
        return effects;
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

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
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

    public List<Relic> getRelics() {
        return relics;
    }

    public void setRelics(List<Relic> relics) {
        this.relics = relics;
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
        notifyGoldEvent(new InventoryEvent(this, InventoryEvent.Direction.GAIN, InventoryEvent.Type.GOLD, gold));
    }

    /**
     * Erhöht die maximale Gesundheit des Spielers um einen bestimmten Punktwert.
     *
     * @param hp Der Punktwert, um den die maximale Gesundheit erhöht werden soll.
     */
    public void increaseMaxHealth(int hp) {
        maxHealth += hp;
        notifyMaxHealthChanged(hp);
    }

    /**
     * Überprüft, ob der Spieler lebt.
     *
     * @return true, wenn die aktuelle Gesundheit größer als 0 ist, andernfalls false.
     */
    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void reduceDurationEffects() {
        for (Map.Entry<Effect, Integer> entry : effects.entrySet()) {
            if (entry.getKey().getStackingBehaviour() == StackingBehaviour.DURATION) {
                entry.setValue(entry.getValue() - 1);
            }
        }
        effects.entrySet().removeIf(entry -> entry.getValue() <= 0);
    }

    public void removeCardFromDeck(Card card) {
        deck.remove(card);
        notifyCardEvent(new InventoryEvent(this, InventoryEvent.Direction.LOSE, InventoryEvent.Type.CARD, card));
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

    public void resetListeners() {
        inventoryEventListeners.clear();
        playerEventListeners.clear();
    }

    public void setDamageFactor(double damageFactor) {
        this.damageFactor = damageFactor;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    protected abstract void initDeck();

    protected abstract void initRelic();

    /**
     * Benachrichtigt den Listener über den empfangenen Blockwert.
     *
     * @param blockAmount Der Betrag des Blocks, der empfangen wurde.
     */
    private void notifyBlockReceived(int blockAmount) {
        PlayerBlockEvent event = new PlayerBlockEvent(this, blockAmount);
        for (PlayerEventListener playerEventListener : playerEventListeners) {
            playerEventListener.onBlockReceived(event);
        }
    }

    private void notifyCardEvent(InventoryEvent event) {
        for (InventoryEventListener inventoryListener : inventoryEventListeners) {
            inventoryListener.onCardEvent(event);
        }
    }

    /**
     * Benachrichtigt den Listener über den erlittenen Schaden.
     *
     * @param damageAmount   Der Betrag des Schadens, der erlitten wurde.
     * @param damageFromCard Gibt an, ob der Schaden von einer Karte stammt.
     */
    private void notifyDamageReceived(int damageAmount, boolean damageFromCard) {
        PlayerDamageEvent event = new PlayerDamageEvent(this, damageAmount, damageFromCard);
        for (PlayerEventListener playerEventListener : playerEventListeners) {
            playerEventListener.onDamageReceived(event);
        }
    }

    /**
     * Benachrichtigt den Listener über die empfangene Energie.
     *
     * @param energyAmount Der Betrag der Energie, die empfangen wurde.
     */
    private void notifyEnergyReceived(int energyAmount) {
        PlayerEnergyEvent event = new PlayerEnergyEvent(this, energyAmount);
        for (PlayerEventListener playerEventListener : playerEventListeners) {
            playerEventListener.onEnergyReceived(event);
        }
    }

    private void notifyEffect(Effect effect, int value) {
        EffectEvent event = new EffectEvent(this, effect, value);
        for (PlayerEventListener playerEventListener : playerEventListeners) {
            playerEventListener.onEffect(event);
        }
    }

    private void notifyGoldEvent(InventoryEvent event) {
        for (InventoryEventListener inventoryListener : inventoryEventListeners) {
            inventoryListener.onGoldEvent(event);
        }
    }

    /**
     * Benachrichtigt den Listener über die empfangene Lebenskraft.
     *
     * @param hpAmount Der Betrag der Lebenskraft, die empfangen wurde.
     */
    private void notifyHealthReceived(int hpAmount) {
        PlayerHealthEvent event = new PlayerHealthEvent(this, hpAmount);
        for (PlayerEventListener playerEventListener : playerEventListeners) {
            playerEventListener.onHealthReceived(event);
        }
    }

    private void notifyLevelEvent(InventoryEvent event) {
        for (InventoryEventListener inventoryListener : inventoryEventListeners) {
            inventoryListener.onLevelEvent(event);
        }
    }

    private void notifyMaxHealthChanged(int hpAmount) {
        PlayerHealthEvent event = new PlayerHealthEvent(this, hpAmount);
        for (PlayerEventListener playerEventListener : playerEventListeners) {
            playerEventListener.onMaxHealthChanged(event);
        }
    }

    private void notifyPotionEvent(InventoryEvent event) {
        for (InventoryEventListener inventoryListener : inventoryEventListeners) {
            inventoryListener.onPotionEvent(event);
        }
    }

    private void notifyRelicEvent(InventoryEvent event) {
        for (InventoryEventListener inventoryListener : inventoryEventListeners) {
            inventoryListener.onRelicEvent(event);
        }
    }

}