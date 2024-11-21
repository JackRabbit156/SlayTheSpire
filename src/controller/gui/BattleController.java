package controller.gui;

import controller.listener.EnemyEventListener;
import controller.listener.PlayerEventListener;
import events.EnemyDamageEvent;
import events.PlayerBlockEvent;
import events.PlayerDamageEvent;
import helper.GuiHelper;
import models.battle.BattleDeck;
import models.battle.GameContext;

import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardType;
import models.enemy.Enemy;
import models.map_elements.field_types.FieldEnum;
import models.player.player_structure.Player;

import models.potion.potion_structure.PotionCard;
import view.gui.BattleView;
import view.gui.layouts.layout_events.BattleViewEvents;

import java.util.List;

/**
 * Diese Klasse steuert den Kampfabschnitt des Spiels, einschließlich der Spieler- und
 * Gegneraktionen sowie des Kampf-Dialogs. Die Klasse verarbeitet die Karten, Energien und
 * Punkte, die im Kampf verwendet werden.
 *
 * @author Warawa Alexander, Willig Daniel
 */
public class BattleController implements BattleViewEvents, PlayerEventListener, EnemyEventListener {
    private final BattleView battleView;
    private FieldEnum fieldType;

    private final Player player;
    private final List<Enemy> enemies;

    private final BattleDeck battleDeck;
    private List<PotionCard> potions;
    private final GameContext gameContext;

    private Card selectedCard;

    public BattleController(Player player, List<Enemy> enemies, FieldEnum fieldType) {
        this.player = player;
        this.enemies = enemies;
        this.fieldType = fieldType;
        for (Enemy enemy : enemies) {
            enemy.setEnemyEventListener(this);
            enemy.calcIntent();
        }

        this.battleDeck = new BattleDeck(player.getDeck());
        this.potions = player.getPotionCards();

        this.gameContext = new GameContext(player, enemies, battleDeck);
        resetEnergyAndBlock();
        calcIntentForAllEnemies(enemies);


        this.battleView = new BattleView(player, enemies, this, battleDeck);
        player.setPlayerEventListener(this);


        battleDeck.fillHand(battleDeck.getStartHandSize());

//        playerBOT();
    }

    private void playerBOT() {
        calcIntentForAllEnemies(enemies);
        resetEnergyAndBlock();
        battleDeck.fillHand(battleDeck.getStartHandSize());
    }

    private void calcIntentForAllEnemies(List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            enemy.calcIntent();
        }
    }

    public void resetEnergyAndBlock() {
        player.resetEnergy();
        player.resetBlock();
    }

    /**
     * This function calls 'playerTurn(...)' and checks if the card needs a target
     *
     * @param card selected card
     * @param index index of selected card
     */
    @Override
    public void onCardClick(Card card, int index) {
        playerTurn(card, index);
    }

    /**
     *
     * @param enemy that enemy you clicked on after your selection of a card
     */
    @Override
    public void onEnemyClick(Enemy enemy) {
        if(selectedCard == null)
            return;

        playCard(enemy);

        selectedCard = null;

        if(enemies.isEmpty()) {
            startingLoot();
        }
    }

    private void startingLoot() {
        GuiHelper.Scenes.startLootScene(this.player, this.fieldType);
    }

    @Override
    public void onPlayerClick() {
        if(selectedCard == null)
            return;

        playCard();

        selectedCard = null;
    }

    @Override
    public void onEndTurnClick() {
        playerEOT();
        enemyTurn();

        playerBOT();
    }

    private void removeHandAfterEndOfTurn() {
        int size = battleDeck.getHand().size();
        for(int i = 0; i < size; i++) {
            battleDeck.discardCardFromHand(battleDeck.getHand().get(0));
        }
    }

    /**
     * If a target is required we save the selected card for 'onEnemyClick'
     * If no target is required then we play the card
     * @param card selected card
     * @param index index of selected card
     */
    private void playerTurn(Card card, int index) {
        selectedCard = card;
    }

    private void playerEOT() {
        removeHandAfterEndOfTurn();
    }

    private void enemyTurn() {
        System.out.println("\nEnemies' Turn:");
        removeBlockOfEnemiesAfterEndOfTurn();

        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                enemy.action(gameContext);
            }

        }
    }

    // Block hält nur 1. Runde an.
    private void removeBlockOfEnemiesAfterEndOfTurn() {
        for (Enemy enemy : enemies) {
            enemy.setBlock(0);
        }
    }

    private boolean isCardPlayable() {
        if(selectedCard.getCost() > player.getCurrentEnergy()){
            System.out.println("\nNot enough Energy!");
            return false;
        }

        if (selectedCard.getName().equals("Clash")) {
            List<Card> hand = gameContext.getBattleDeck().getHand();
            for (Card card : hand) {
                if (!card.getCardType().equals(CardType.ATTACK)) {
                    System.out.println("\nOnly playable if only Attack-Cards in Hand");
                    return false;
                }
            }
        }
        return true;
    }

    private void cardDeath() {
        if (selectedCard.getCardGrave().equals(CardGrave.POTION)) {
            potions.remove(selectedCard);
        }

        if (selectedCard.getCardGrave().equals(CardGrave.EXHAUST)) {
            battleDeck.exhaustCardFromHand(selectedCard);
        }
        else if (selectedCard.getCardGrave().equals(CardGrave.DISCARD)) {
            battleDeck.discardCardFromHand(selectedCard);
        }
        else {
            battleDeck.removeCardFromHand(selectedCard);
        }
    }

    private void playCard(Enemy enemy) {
        if (!isCardPlayable()) {
            return;
        }

        // Play the card (and add the enemy)
        gameContext.setSelectedEnemy(enemy);
        selectedCard.play(gameContext);

        cardDeath();
    }

    private void playCard() {
        if (!isCardPlayable()) {
            return;
        }

        // Play the card
        selectedCard.play(gameContext);

        cardDeath();
    }

    public BattleView getBattleView(){
        return this.battleView;
    }

    @Override
    public void onBlockReceived(PlayerBlockEvent event) {

    }

    @Override
    public void onDamageReceived(PlayerDamageEvent event) {

    }

    @Override
    public void onDamageDealt() {

    }

    @Override
    public void onDamageReceived(EnemyDamageEvent event) {

    }

    @Override
    public void onEnemyDeath(Enemy enemy) {
        for (Enemy singleEnemy : enemies) {
            if (singleEnemy.isAlive()) {
                return;
            }
        }
        startingLoot();
    }

    public FieldEnum getFieldType() {
        return fieldType;
    }
}