package controller;

import Events.PlayerBlockEvent;
import helper.ConsoleAssistent;
import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardTrigger;
import models.cards.card_structure.PowerCard;
import models.enemy.Enemy;
import models.player.player_structure.Player;
import view.BattleView;
import listener.PlayerEventListener;

import java.util.List;
import java.util.Scanner;

public class BattleViewController implements PlayerEventListener{
    private Player player;
    private List<Enemy> enemies;
    private BattleView view;
    private Scanner scanner;
    private BattleDeck battleDeck;
    private GameContext gameContext;

    public BattleViewController(Player player, List<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
        this.view = new BattleView();
        this.scanner = new Scanner(System.in);
        this.battleDeck = new BattleDeck(player.getDeck());
        this.gameContext = new GameContext(player, enemies, battleDeck);
        player.setListener(this);
    }

    public void startBattle() {
        ConsoleAssistent.clearScreen();
        while (player.isAlive() && !enemies.isEmpty()) {
            playerBOT();

            view.display(player, enemies, battleDeck.getHand());

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

    private void triggerCard(CardTrigger trigger) {
        List<PowerCard> currentPowerCards = battleDeck.getCurrentPowerCards();

        for (PowerCard currentPowerCard : currentPowerCards) {
            if (currentPowerCard.getCardTrigger().equals(trigger)) {
                currentPowerCard.ability(gameContext);
            }
        }
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
                    view.display(player, enemies, battleDeck.getHand());
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

        if (cardIndex >= 0 && cardIndex < hand.size()) {
            Card selectedCard = hand.get(cardIndex);












            selectedCard.play(gameContext);



//            for (PowerCard powerCard : powerCards) {
//                if (powerCard.getCardTrigger().equals(CardTrigger.GAIN_BLOCK)) {
//                    powerCard.ability(gameContext);
//                }
//            }








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
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                int damage = enemy.attack();
                player.decreaseCurrentHealth(damage);
                view.displayAttack(enemy.getName(), player.getName(), damage);
            }
        }
    }

    @Override
    public void onBlockReceived(PlayerBlockEvent event) {
        List<PowerCard> powerCards = battleDeck.getCurrentPowerCards();
        for (PowerCard powerCard : powerCards) {
            if (powerCard.getCardTrigger().equals(CardTrigger.GAIN_BLOCK)) {
                powerCard.ability(gameContext);
            }
        }
    }
}