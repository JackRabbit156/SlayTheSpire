package controller;

import helper.ConsoleAssistent;
import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.enemy.Enemy;
import models.player.player_structure.Player;
import view.BattleView;

import java.util.List;
import java.util.Scanner;

public class BattleViewController {
    private Player player;
    private List<Enemy> enemies;
    private BattleView view;
    private Scanner scanner;
    private BattleDeck battleDeck;

    public BattleViewController(Player player, List<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
        this.view = new BattleView();
        this.scanner = new Scanner(System.in);
        this.battleDeck = new BattleDeck(player.getDeck());
    }

    public void startBattle() {
        ConsoleAssistent.clearScreen();
        while (player.isAlive() && !enemies.isEmpty()) {
            battleDeck.fillHand(battleDeck.getStartHandSize());
            player.resetEnergy();
            player.resetBlock();

            printBatteView();

            playerTurn();

            removeHandAfterEndOfTurn();

            ConsoleAssistent.clearScreen();

            enemyTurn();

            enemies.removeIf(enemy -> !enemy.isAlive()); // remove dead enemies. Sick function!
        }

        if (player.isAlive())
            view.displayVictory();
        else
            view.displayDefeat();
    }

    private void printBatteView(){
        enemies.removeIf(enemy -> !enemy.isAlive());
        view.display(player, enemies, battleDeck.getHand());
    }

    private void playerTurn() {

        while(!enemies.isEmpty()){
            System.out.println("\n1.Play a Card");
            System.out.println("2.End Turn\n");

            System.out.print("Choose your action: ");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    selectCard();
                    ConsoleAssistent.clearScreen();
                    printBatteView();
                    break;
                case 2: return;
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

            selectedCard.play(new GameContext(player, enemies, battleDeck));
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
        for(int i = 0; i< battleDeck.getHand().size(); i++)
            battleDeck.discardCardFromHand(battleDeck.getHand().get(i));
    }

    private void enemyTurn() {
        System.out.println("\nEnemies' Turn:");

        removeBlockOfEnemiesAfterEndOfTurn();

        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                enemy.attack(new GameContext(player, enemies, battleDeck));
            }
            // kurze Verzögerung, damit der Schaden des Gegners nicht auf einem Schlag kommt.
            ConsoleAssistent.sleep(300);
        }
    }
    // Block hält nur 1. Runde an.
    private void removeBlockOfEnemiesAfterEndOfTurn() {
        for(int i = 0; i<enemies.size(); i++){
            enemies.get(i).setBlock(0);
        }
    }
}