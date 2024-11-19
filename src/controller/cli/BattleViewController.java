package controller.cli;

import events.PlayerBlockEvent;
import events.PlayerDamageEvent;
import helper.ConsoleAssistent;
import models.battle.BattleDeck;
import models.battle.GameContext;
import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardTrigger;
import models.card.card_structure.PowerCard;
import models.enemy.Enemy;
import models.player.player_structure.Player;
import view.cli.BattleView;
import controller.listener.*;

import java.util.List;
import java.util.Scanner;


/**
 * Diese Klasse steuert den Kampfabschnitt des Spiels, einschließlich der Spieler- und
 * Gegneraktionen sowie des Kampf-Dialogs. Sie verarbeitet die Karten, Energien und
 * Punkte, die im Kampf verwendet werden.
 *
 * @author Warawa Alexander, Willig Daniel
 */
public class BattleViewController implements PlayerEventListener{
    private Player player;
    private List<Enemy> enemies;
    private BattleView view;
    private Scanner scanner;
    private BattleDeck battleDeck;
    private GameContext gameContext;

    /**
     * Konstruktor für die BattleViewController-Klasse.
     *
     * @param player Der Spieler, der im Kampf beteiligt ist.
     * @param enemies Die Liste der Gegner, die im Kampf bekämpft werden.
     */
    public BattleViewController(Player player, List<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
        this.view = new BattleView();
        this.scanner = new Scanner(System.in);
        this.battleDeck = new BattleDeck(player.getDeck());
        this.gameContext = new GameContext(player, enemies, battleDeck);
        //player.setListener(this);
        player.setListener(this);
    }

    /**
     * Startet den Kampf zwischen dem Spieler und den Gegnern.
     * Der Kampf findet so lange statt, bis der Spieler besiegt wird oder alle Gegner besiegt sind.
     */
    public void startBattle() {
        ConsoleAssistent.clearScreen();
        while (player.isAlive() && !enemies.isEmpty()) {
            playerBOT();

            printBattleView();

            playerTurn();

            playerEOT();

            ConsoleAssistent.clearScreen();

            enemyTurn();

            enemies.removeIf(enemy -> !enemy.isAlive()); // remove dead enemies. Sick function!
        }

        if (player.isAlive())
            view.displayVictory();
        else
            view.displayDefeat();
    }

    private void playerBOT() {
        battleDeck.fillHand(battleDeck.getStartHandSize());
        player.resetEnergy();
        player.resetBlock();
        triggerCard(CardTrigger.PLAYER_BOT);
    }


    private void playerEOT() {
        removeHandAfterEndOfTurn();
        triggerCard(CardTrigger.PLAYER_EOT);
    }

    /**
     * Löst die Kartenfähigkeiten aus, die mit dem angegebenen Trigger verknüpft sind.
     *
     * @param trigger Der Trigger, der die Kartenauswirkung aktiviert.
     */
    private void triggerCard(CardTrigger trigger) {
        List<PowerCard> currentPowerCards = battleDeck.getCurrentPowerCards();

        for (PowerCard currentPowerCard : currentPowerCards) {
            if (currentPowerCard.getCardTrigger().equals(trigger)) {
                currentPowerCard.ability(gameContext);
            }
        }
    }

    /**
     * Gibt die aktuelle Sicht des Kampfes aus, einschließlich des Spielers,
     * der Gegner und der Handkarten des Spielers.
     */
    private void printBattleView(){
        enemies.removeIf(enemy -> !enemy.isAlive());
        view.display(player, enemies, battleDeck.getHand());
    }

    private void playerTurn() {

        while(!enemies.isEmpty()){
            System.out.println("\n1.Play a Card");
            System.out.println("2.End Turn\n");

            System.out.print("Choose your action: ");
            String choice = scanner.next();

            switch (choice){
                case "1":
                    selectCard();
                    ConsoleAssistent.clearScreen();
                    printBattleView();
                    break;
                case "2": return;
                default:
                    System.out.println("Wrong input...");
                    playerTurn();
                    break;
            }
        }
    }

    private void selectCard() {
        System.out.print("Choose a card to play: ");
        int cardIndex = scanner.nextInt() - 1;
        List<Card> hand = battleDeck.getHand();

        if (cardIndex >= 0 && cardIndex < hand.size() ) {
            Card selectedCard = hand.get(cardIndex);

            if(selectedCard.getCost() > player.getCurrentEnergy()){
                System.out.println("\nNot enough Energy!");
                ConsoleAssistent.sleep(1000);
                return;
            }

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
        } else {
            System.out.println("Invalid card selection.");
        }
    }

    private void removeHandAfterEndOfTurn() {
        for(int i = 0; i< battleDeck.getHand().size(); i++) {
            battleDeck.discardCardFromHand(battleDeck.getHand().get(i));
        }
    }

    private void enemyTurn() {
        System.out.println("\nEnemies' Turn:");

        removeBlockOfEnemiesAfterEndOfTurn();

        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                enemy.action(gameContext);
            }
            // kurze Verzögerung, damit der Schaden des Gegners nicht auf einem Schlag kommt.
            ConsoleAssistent.sleep(300);
        }
    }
    // Block hält nur 1. Runde an.
    private void removeBlockOfEnemiesAfterEndOfTurn() {
        for (Enemy enemy : enemies) {
            enemy.setBlock(0);
        }
    }

    @Override
    public void onBlockReceived(PlayerBlockEvent event) {
        List<PowerCard> powerCards = battleDeck.getCurrentPowerCards();
        if (powerCards.isEmpty()) {
            return;
        }
        for (PowerCard powerCard : powerCards) {
            if (powerCard.getCardTrigger().equals(CardTrigger.GAIN_BLOCK)) {
                powerCard.ability(gameContext);
            }
        }
    }

    @Override
    public void onDamageReceived(PlayerDamageEvent event) {
        List<PowerCard> powerCards = battleDeck.getCurrentPowerCards();
        if (powerCards.isEmpty()) {
            return;
        }
        for (PowerCard powerCard : powerCards) {
            if (powerCard.getCardTrigger().equals(CardTrigger.LOSE_HP_CARD)) {
                powerCard.ability(gameContext);
            }
        }
    }

    @Override
    public void onDamageDealed() {
    }
}