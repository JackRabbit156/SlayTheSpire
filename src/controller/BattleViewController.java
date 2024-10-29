package controller;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.Card;
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
    private BattleDeck cardManager;

    public BattleViewController(Player player, List<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
        this.view = new BattleView();
        this.scanner = new Scanner(System.in);
        this.cardManager = new BattleDeck(player.getDeck());
    }

    public void startBattle() {
        view.clearScreen();
        while (player.isAlive() && !enemies.isEmpty()) {
            cardManager.drawCards(5);
            player.resetEnergy();
            view.display(player, enemies, cardManager.getHand());

            playerTurn();

            removeHandAfterEndOfTurn();

            view.clearScreen();

            enemyTurn();

            enemies.removeIf(enemy -> !enemy.isAlive()); // remove dead enemies. Sick function!
        }

        if (player.isAlive())
            view.displayVictory();
         else
            view.displayDefeat();
    }

    private void playerTurn() {

        while(player.getCurrentEnergy() > 0){
            System.out.println("\n1.Play a Card");
            System.out.println("2.End Turn\n");

            System.out.print("Choose your action: ");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    selectCard();
                    view.display(player, enemies, cardManager.getHand());
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
        List<Card> hand = cardManager.getHand();

        if (cardIndex >= 0 && cardIndex < hand.size()) {
            Card selectedCard = hand.get(cardIndex);

            selectedCard.play(new GameContext(player, enemies));

            cardManager.discardCard(selectedCard);
        } else {
            System.out.println("Invalid card selection.");
        }
    }

    private void removeHandAfterEndOfTurn() {
        for(int i = 0; i< cardManager.getHand().size(); i++)
            cardManager.discardCard(cardManager.getHand().get(i));
    }

    private void enemyTurn() {
        System.out.println("\nEnemies' Turn:");
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                int damage = enemy.attack();
                player.decreaseCurrentHealth(damage);
                view.displayAttack(enemy.getName(), player.getName(), damage);
            }
        }
    }
}