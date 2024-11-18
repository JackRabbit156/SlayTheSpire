package controller.gui;

import helper.GuiHelper;
import javafx.stage.Stage;
import models.battle.BattleDeck;
import models.battle.GameContext;

import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardType;
import models.enemy.Enemy;
import models.player.player_structure.Player;

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
public class BattleController implements BattleViewEvents {
    private final BattleView battleView;

    private final Player player;
    private final List<Enemy> enemies;

    private final BattleDeck battleDeck;
    private final GameContext gameContext;

    private Card selectedCard;

    public BattleController(Player player, List<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;

        this.battleDeck = new BattleDeck(player.getDeck());

        this.gameContext = new GameContext(player, enemies, battleDeck);
        this.battleView = new BattleView(player, enemies, this, battleDeck);

        battleDeck.fillHand(battleDeck.getStartHandSize());

        playerBOT();
    }

    private void playerBOT() {
        battleDeck.fillHand(battleDeck.getStartHandSize());
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

        playCardIfPossible(enemy);

        selectedCard = null;

        if(enemies.isEmpty()) {
            startingMap();
        }
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
        if(card.isTargetRequired())
            selectedCard = card;
         else
            card.play(gameContext);
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

    private void playCardIfPossible(Enemy enemy) {
        if(selectedCard.getCost() > player.getCurrentEnergy()){
            System.out.println("\nNot enough Energy!");
            return;
        }

        if (selectedCard.getName().equals("Clash")) {
            List<Card> hand = gameContext.getBattleDeck().getHand();
            for (Card card : hand) {
                if (!card.getCardType().equals(CardType.ATTACK)) {
                    System.out.println("\nOnly playable if only Attack-Cards in Hand");
                    return;
                }
            }
        }

        // Play the card (and add the enemy)
        gameContext.setSelectedEnemy(enemy);
        selectedCard.play(gameContext);

        if (selectedCard.getCardGrave() == CardGrave.EXHAUST) {
            battleDeck.exhaustCardFromHand(selectedCard);
        }
        else if (selectedCard.getCardGrave() == CardGrave.DISCARD) {
            battleDeck.discardCardFromHand(selectedCard);
        }
        else {
            battleDeck.removeCardFromHand(selectedCard);
        }
    }

    private void startingMap(){
        Stage primaryStage = player.getPrimaryStage();
        GuiHelper.Scenes.startMapScene(primaryStage, player, true);
    }

    public BattleView getBattleView(){
        return this.battleView;
    }
}