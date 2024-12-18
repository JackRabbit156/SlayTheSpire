package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.controller.listener.CardDeathListener;
import de.bundeswehr.auf.slaythespire.controller.listener.EnemyEventListener;
import de.bundeswehr.auf.slaythespire.controller.listener.PlayerEventListener;
import de.bundeswehr.auf.slaythespire.events.EnemyDamageEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerBlockEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerDamageEvent;
import de.bundeswehr.auf.slaythespire.gui.BattleView;
import de.bundeswehr.auf.slaythespire.gui.events.BattleViewEvents;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.battle.Playable;
import de.bundeswehr.auf.slaythespire.model.card.structure.*;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.map.field.FieldEnum;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import javafx.stage.Stage;

import java.util.List;

/**
 * Diese Klasse steuert den Kampfabschnitt des Spiels, einschließlich der Spieler- und
 * Gegneraktionen sowie des Kampf-Dialogs. Die Klasse verarbeitet die Karten, Energien und
 * Punkte, die im Kampf verwendet werden.
 *
 * @author Warawa Alexander, Willig Daniel
 */
public class BattleController implements Controller, BattleViewEvents, PlayerEventListener, EnemyEventListener, CardDeathListener {

    private final BattleDeck battleDeck;
    private final BattleView battleView;
    private final List<Enemy> enemies;
    private final FieldEnum fieldType;
    private final GameContext gameContext;
    private final Player player;
    private final List<Potion> potions;
    private Card selectedCard;

    public BattleController(Player player, List<Enemy> enemies, FieldEnum fieldType) {
        this.player = player;
        this.enemies = enemies;
        this.fieldType = fieldType;
        for (Enemy enemy : enemies) {
            enemy.setEnemyEventListener(this);
            enemy.calcIntent();
        }

        battleDeck = new BattleDeck(player.getDeck());
        potions = player.getPotions();

        gameContext = new GameContext(player, enemies, battleDeck);

        battleView = new BattleView(player, enemies, this, battleDeck);
        player.setPlayerEventListener(this);

        startOfCombat();
    }

    @Override
    public void discard() {
        battleView.discard();
    }

    public BattleView getBattleView() {
        return battleView;
    }

    @Override
    public void onBlockReceived(PlayerBlockEvent event) {
        triggerPowerCards(CardTrigger.GAIN_BLOCK);
    }

    /**
     * This function calls 'playerTurn(...)' and checks if the card needs a target
     *
     * @param card  selected card
     * @param index index of selected card
     */
    @Override
    public void onCardClick(Card card, int index) {
        selectedCard = card;
    }

    @Override
    public void onCardDeath(Card card) {
        cardDeath(card);
    }

    @Override
    public void onDamageDealt() {

    }

    @Override
    public void onDamageReceived(PlayerDamageEvent event) {
        if (event.isCard()) {
            triggerPowerCards(CardTrigger.LOSE_HP_CARD);
        }
        else {
            triggerPowerCards(CardTrigger.LOSE_HP_ENEMY);
        }
    }

    @Override
    public void onDamageReceived(EnemyDamageEvent event) {

    }

    @Override
    public void onEndTurnClick() {
        playerEOT();
        enemyTurn();
        playerBOT();
    }

    /**
     * @param enemy that enemy you clicked on after your selection of a card
     */
    @Override
    public void onEnemyClick(Enemy enemy) {
        if (selectedCard == null) {
            return;
        }
        playCard(enemy);
        selectedCard = null;
    }

    @Override
    public void onEnemyDeath(Enemy enemy) {
        for (Enemy singleEnemy : enemies) {
            if (singleEnemy.isAlive()) {
                return;
            }
        }
        endOfCombat();
    }

    @Override
    public void onFullScreenClick() {
        Stage primaryStage = player.getPrimaryStage();

        primaryStage.setFullScreen(!primaryStage.isFullScreen());
    }

    @Override
    public void onPlayerClick() {
        if (selectedCard == null) {
            return;
        }

        playCard();

        selectedCard = null;
    }

    public void resetEnergyAndBlock() {
        player.resetEnergy();
        player.resetBlock();
    }

    private void calcIntentForAllEnemies() {
        for (Enemy enemy : enemies) {
            enemy.calcIntent();
        }
    }

    private void cardDeath(Card card) {
        switch (card.getCardGrave()) {
            case POTION:
                potions.remove(card);
                break;
            case EXHAUST:
                battleDeck.exhaustCardFromHand(card);
                break;
            case DISCARD:
                battleDeck.discardCardFromHand(card);
                break;
            default:
                battleDeck.removeCardFromHand(card);
        }
        battleView.updateBottom();
    }

    private void endOfCombat() {
        LoggingAssistant.log("End of combat");
        triggerRelics(RelicTrigger.END_OF_COMBAT);

        GuiHelper.Scenes.startLootScene(this.player, this.fieldType);
    }

    private void enemyTurn() {
        LoggingAssistant.log("Enemies' turn");
        removeBlockOfEnemiesAfterEndOfTurn();
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                enemy.action(gameContext);
            }
        }
    }

    private boolean isCardPlayable() {
        if (selectedCard.getCost() > player.getCurrentEnergy()) {
            LoggingAssistant.log("Not enough Energy", Color.YELLOW);
            battleView.showDialog("Not enough Energy!");
            return false;
        }
        Playable cardPlayable = selectedCard.isPlayable(gameContext);
        if (!cardPlayable.isPlayable()) {
            LoggingAssistant.log(cardPlayable.getLogMessage(), Color.YELLOW);
            battleView.showDialog(cardPlayable.getErrorMessage());
        }
        return cardPlayable.isPlayable();
    }

    private void playCard(Enemy enemy) {
        if (!isCardPlayable()) {
            return;
        }
        if (enemy == null) {
            gameContext.setRandomEnemy();
        }
        else {
            gameContext.setSelectedEnemy(enemy);
        }
        selectedCard.register(this);
        selectedCard.play(gameContext);
        selectedCard.played();
    }

    private void playCard() {
        if (!isCardPlayable()) {
            return;
        }
        selectedCard.register(this);
        selectedCard.play(gameContext);
        selectedCard.played();
    }

    private void playerBOT() {
        LoggingAssistant.log("Players' turn");
        calcIntentForAllEnemies();
        resetEnergyAndBlock();
        battleDeck.fillHand(battleDeck.getStartHandSize());

        triggerPowerCards(CardTrigger.PLAYER_BOT);
    }

    private void playerEOT() {
        removeHandAfterEndOfTurn();

        triggerPowerCards(CardTrigger.PLAYER_EOT);
    }

    // Block hält nur 1. Runde an.
    private void removeBlockOfEnemiesAfterEndOfTurn() {
        for (Enemy enemy : enemies) {
            enemy.setBlock(0);
        }
    }

    private void removeHandAfterEndOfTurn() {
        int size = battleDeck.getHand().size();
        for (int i = 0; i < size; i++) {
            battleDeck.discardCardFromHand(battleDeck.getHand().get(0));
        }
    }

    private void startOfCombat() {
        LoggingAssistant.log("Start of combat");
        calcIntentForAllEnemies();
        resetEnergyAndBlock();
        battleDeck.fillHand(battleDeck.getStartHandSize());

        triggerRelics(RelicTrigger.START_OF_COMBAT);
        triggerPowerCards(CardTrigger.PLAYER_BOT);
    }

    private void triggerPowerCards(CardTrigger trigger) {
        List<PowerCard> powerCards = battleDeck.getCurrentPowerCards();
        for (PowerCard powerCard : powerCards) {
            if (powerCard.getCardTrigger().equals(trigger)) {
                powerCard.onTrigger(gameContext);
            }
        }
    }

    private void triggerRelics(RelicTrigger trigger) {
        Relic relic = player.getRelic();
        if (relic.getTrigger().equals(trigger)) {
            relic.getsUsed(gameContext);
        }
    }

}