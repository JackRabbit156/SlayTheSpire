package de.bundeswehr.auf.slaythespire.model.player.structure;

import de.bundeswehr.auf.slaythespire.controller.listener.InventoryEventListener;
import de.bundeswehr.auf.slaythespire.controller.listener.PlayerEventListener;
import de.bundeswehr.auf.slaythespire.events.*;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
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
public abstract class Player extends Entity {

    private int currentAct = 1;
    private int currentEnergy;
    private String currentField = "0";
    private List<Card> deck;
    private String energyIconPath;
    private String gameOverImagePath;
    private int gold;
    private final List<InventoryEventListener> inventoryEventListeners = new ArrayList<>();
    private int maxEnergy;
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
        super(name, maxHealth);
        this.maxEnergy = maxEnergy;
        this.currentEnergy = this.maxEnergy;
        this.playerType = playerType;
        this.primaryStage = primaryStage;
    }

    public void addCardToDeck(Card card) {
        deck.add(card);
        notifyCardEvent(new InventoryEvent(this, InventoryEvent.Direction.GAIN, InventoryEvent.Type.CARD, card));
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

    public int getCurrentAct() {
        return currentAct;
    }

    public void removePotion(Potion potion) {
        potions.remove(potion);
        notifyPotionEvent(new InventoryEvent(this, InventoryEvent.Direction.LOSE, InventoryEvent.Type.POTION, potion));
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

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public String getEnergyIconPath() {
        return energyIconPath;
    }

    public void setEnergyIconPath(String energyIconPath) {
        this.energyIconPath = energyIconPath;
    }

    public String getGameOverImagePath() {
        return gameOverImagePath;
    }

    public void setGameOverImagePath(String gameOverImagePath) {
        this.gameOverImagePath = gameOverImagePath;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
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
     * Erhöht die aktuelle Energie des Spielers um einen bestimmten Betrag.
     *
     * @param energy Der Betrag, um den die Energie erhöht werden soll.
     */
    public void increaseCurrentEnergy(int energy) {
        currentEnergy += energy;
        notifyEnergyReceived(energy);
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

    public void removeCardFromDeck(Card card) {
        deck.remove(card);
        notifyCardEvent(new InventoryEvent(this, InventoryEvent.Direction.LOSE, InventoryEvent.Type.CARD, card));
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

    /**
     * Fügt dem Spieler Schaden zu. Der Schaden wird abhängig von
     * dem Blockwert des Spielers berücksichtigt.
     *
     * @param gameContext im {@link de.bundeswehr.auf.slaythespire.model.battle.AttackContext} des {@link GameContext} findet sich der zugefügte Schaden.
     */
    @Override
    public void takeDamage(GameContext gameContext) {
        super.takeDamage(gameContext);
        GameSettings.increaseReceivedDamageStats(gameContext.getAttackContext().getDamage());
    }

    protected abstract void initDeck();

    protected abstract void initRelic();

    @Override
    protected void notifyBlockReceived(int blockAmount) {
        PlayerBlockEvent event = new PlayerBlockEvent(this, blockAmount);
        for (PlayerEventListener playerEventListener : playerEventListeners) {
            playerEventListener.onBlockReceived(event);
        }
    }

    @Override
    protected void notifyDamageReceived(GameContext gameContext) {
        boolean damageFromCard = gameContext.getAttackContext().getSource() == gameContext.getAttackContext().getTarget();
        PlayerDamageEvent event = new PlayerDamageEvent(this, gameContext.getAttackContext().getDamage(), damageFromCard);
        for (PlayerEventListener playerEventListener : playerEventListeners) {
            playerEventListener.onDamageReceived(event);
        }
    }

    @Override
    protected void notifyEffect(Effect effect, int value) {
        EffectEvent event = new EffectEvent(this, effect, value);
        for (PlayerEventListener playerEventListener : playerEventListeners) {
            playerEventListener.onEffect(event);
        }
    }

    @Override
    protected void notifyHealthReceived(int hpAmount) {
        PlayerHealthEvent event = new PlayerHealthEvent(this, hpAmount);
        for (PlayerEventListener playerEventListener : playerEventListeners) {
            playerEventListener.onHealthReceived(event);
        }
    }

    @Override
    protected void notifyMaxHealthChanged(int hpAmount) {
        PlayerHealthEvent event = new PlayerHealthEvent(this, hpAmount);
        for (PlayerEventListener playerEventListener : playerEventListeners) {
            playerEventListener.onMaxHealthChanged(event);
        }
    }

    private void notifyCardEvent(InventoryEvent event) {
        for (InventoryEventListener inventoryListener : inventoryEventListeners) {
            inventoryListener.onCardEvent(event);
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

    private void notifyGoldEvent(InventoryEvent event) {
        for (InventoryEventListener inventoryListener : inventoryEventListeners) {
            inventoryListener.onGoldEvent(event);
        }
    }

    private void notifyLevelEvent(InventoryEvent event) {
        for (InventoryEventListener inventoryListener : inventoryEventListeners) {
            inventoryListener.onLevelEvent(event);
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