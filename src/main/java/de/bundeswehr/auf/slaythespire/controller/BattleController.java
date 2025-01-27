package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.controller.listener.CardDeathListener;
import de.bundeswehr.auf.slaythespire.controller.listener.EnemyEventListener;
import de.bundeswehr.auf.slaythespire.controller.listener.PlayerEventListener;
import de.bundeswehr.auf.slaythespire.events.*;
import de.bundeswehr.auf.slaythespire.gui.BattleView;
import de.bundeswehr.auf.slaythespire.gui.events.BattleViewEvents;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.battle.Playable;
import de.bundeswehr.auf.slaythespire.model.card.structure.*;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.map.field.FieldEnum;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
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
    private final List<EnemyController> enemies = new ArrayList<>();
    private final FieldEnum fieldType;
    private final GameContext gameContext;
    private final PlayerController player;
    private Card selectedCard;

    public BattleController(Player player, List<Enemy> enemies, FieldEnum fieldType) {
        this.fieldType = fieldType;
        for (Enemy enemy : enemies) {
            enemy.addEnemyEventListener(this);
        }

        battleDeck = new BattleDeck(player.getDeck());

        gameContext = new GameContext(player, enemies, battleDeck);
        // TODO PlayerController (und EnemyController) muss auch außerhalb existieren
        this.player = new PlayerController(gameContext);
        for (Enemy enemy : enemies) {
            this.enemies.add(new EnemyController(enemy, gameContext, this.player));
        }

        calculateIntentForAllEnemies();
        battleView = new BattleView(player, enemies, this, gameContext);
        player.addPlayerEventListener(this);
    }

    @Override
    public void discard() {
        battleView.discard();
    }

    public BattleView getBattleView() {
        return battleView;
    }

    @Override
    public void onBanter(EnemyBanterEvent event) {
        battleView.banter(event.getEnemy(), event.getBanter());
    }

    @Override
    public void onBlockReceived(PlayerBlockEvent event) {
        triggerPowerCards(CardTrigger.GAIN_BLOCK);
    }

    @Override
    public void onBlockReceived(EnemyBlockEvent event) {

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
        player.triggerRelics(RelicTrigger.PLAY_CARD);
        triggerPowerCards(CardTrigger.PLAY_CARD);
        if (card instanceof AttackCard) {
            player.triggerRelics(RelicTrigger.PLAY_ATTACK);
            triggerPowerCards(CardTrigger.PLAY_ATTACK);
        }
        else if (card instanceof SkillCard) {
            player.triggerRelics(RelicTrigger.PLAY_SKILL);
            triggerPowerCards(CardTrigger.PLAY_SKILL);
        }
        else if (card instanceof PowerCard) {
            player.triggerRelics(RelicTrigger.PLAY_POWER);
            triggerPowerCards(CardTrigger.PLAY_POWER);
        }
        else if (card instanceof Potion) {
            player.triggerRelics(RelicTrigger.PLAY_POTION);
        }
        cardDeath(card);
    }

    @Override
    public void onCardDrawn(Card card) {
        if (card instanceof StatusCard) {
            ((StatusCard) card).onDraw(gameContext);
        }
    }

    @Override
    public void onDamageDealt(PlayerDamageEvent event) {

    }

    @Override
    public void onDamageDealt(EnemyDamageEvent event) {
        triggerPowerCards(CardTrigger.PLAY_ATTACK);
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
    public void onEffect(EffectEvent event) {

    }

    @Override
    public void onEndTurnClick() {
        playerEndOfTurn();
        enemyTurn();
        playerBeginOfTurn();
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
        for (EnemyController enemyController : enemies) {
            if (enemyController.isAlive()) {
                return;
            }
        }
        endOfCombat();
    }

    @Override
    public void onEnergyReceived(PlayerEnergyEvent event) {
        triggerPowerCards(CardTrigger.GAIN_ENERGY);
    }

    @Override
    public void onFullScreenClick() {
        Stage primaryStage = gameContext.getPlayer().getPrimaryStage();

        primaryStage.setFullScreen(!primaryStage.isFullScreen());
    }

    @Override
    public void onHealthReceived(PlayerHealthEvent event) {
    }

    @Override
    public void onMaxHealthChanged(PlayerHealthEvent event) {
    }

    @Override
    public void onPlayerClick() {
        if (selectedCard == null) {
            return;
        }
        playCard();
        selectedCard = null;
    }

    @Override
    public void onScream(PlayerScreamEvent event) {
        battleView.scream(event.getText());
    }

    public void resetEnergyAndBlock() {
        gameContext.getPlayer().resetEnergy();
        gameContext.getPlayer().resetBlock();
    }

    public void startOfCombat() {
        LoggingAssistant.log("Start of combat");
        resetEnergyAndBlock();

        player.triggerRelics(RelicTrigger.START_OF_COMBAT);

        battleDeck.fillHand(battleDeck.getStartHandSize());

        player.triggerRelics(RelicTrigger.BEGIN_OF_TURN);
        triggerEffects(EffectTrigger.BEGIN_OF_TURN, gameContext.getPlayer());
        triggerPowerCards(CardTrigger.PLAYER_BEGIN_OF_TURN);
    }

    private void calculateIntentForAllEnemies() {
        for (EnemyController enemy : enemies) {
            enemy.calculateIntent();
        }
    }

    private void cardDeath(Card card) {
        switch (card.getCardGrave()) {
            case POTION:
                gameContext.getPlayer().removePotion((Potion) card);
                break;
            case EXHAUST:
                battleDeck.exhaustCardFromHand(card);
                player.triggerRelics(RelicTrigger.EXHAUST);
                break;
            case ETHEREAL:
            case DISCARD:
                battleDeck.discardCardFromHand(card);
                player.triggerRelics(RelicTrigger.DISCARD);
                break;
            default:
                battleDeck.removeCardFromHand(card);
        }
        if (card.getCardGrave() != CardGrave.POTION) {
            triggerEffects(EffectTrigger.CARD_DEATH, gameContext.getPlayer());
        }
        battleView.updateBottom();
    }

    private void endOfCombat() {
        LoggingAssistant.log("End of combat");
        removeEffects();
        player.triggerRelics(RelicTrigger.END_OF_COMBAT);

        GuiHelper.Scenes.startLootScene(gameContext.getPlayer(), fieldType);
    }

    private void enemyTurn() {
        LoggingAssistant.log("Enemies' turn");
        resetBlockOfEnemies();
        for (EnemyController enemy : enemies) {
            enemy.action();
        }
    }

    private boolean isCardPlayable() {
        if (selectedCard.getCost() > gameContext.getPlayer().getCurrentEnergy()) {
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

    private void playerBeginOfTurn() {
        LoggingAssistant.log("Players' turn");
        calculateIntentForAllEnemies();
        resetEnergyAndBlock();
        gameContext.getPlayer().reduceDurationEffects();
        battleDeck.fillHand(battleDeck.getStartHandSize());

        player.triggerRelics(RelicTrigger.BEGIN_OF_TURN);
        triggerEffects(EffectTrigger.BEGIN_OF_TURN, gameContext.getPlayer());
        triggerPowerCards(CardTrigger.PLAYER_BEGIN_OF_TURN);
    }

    private void playerEndOfTurn() {
        removeHandAfterEndOfTurn();

        player.triggerRelics(RelicTrigger.END_OF_TURN);
        triggerEffects(EffectTrigger.END_OF_TURN, gameContext.getPlayer());
        triggerPowerCards(CardTrigger.PLAYER_END_OF_TURN);
        battleDeck.removeNonPowerCards();
    }

    private void removeEffects() {
        gameContext.getPlayer().getEffects().clear();
    }

    private void removeHandAfterEndOfTurn() {
        int size = battleDeck.getHand().size();
        for (int i = 0; i < size; i++) {
            Card card = battleDeck.getHand().get(0);
            if (card.getCardGrave() == CardGrave.ETHEREAL) {
                battleDeck.exhaustCardFromHand(card);
            }
            else {
                battleDeck.discardCardFromHand(card);
            }
        }
    }

    private void resetBlockOfEnemies() {
        for (EnemyController enemy : enemies) {
            enemy.resetBlock();
        }
    }

    private void triggerEffects(EffectTrigger trigger, Entity target) {
        for (Effect effect : new HashSet<>(target.getEffects().keySet())) {
            if (effect.getEffectTrigger() == trigger) {
                effect.apply(gameContext, target);
            }
        }
    }

    private void triggerPowerCards(CardTrigger trigger) {
        List<TriggeredCard> cards = battleDeck.getTriggeredCards();
        for (TriggeredCard card : cards) {
            if (card.getTrigger().equals(trigger)) {
                card.onTrigger(gameContext);
            }
        }
    }

}