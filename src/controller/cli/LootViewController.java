package controller.cli;

import helper.Color;
import helper.ConsoleAssistent;
import models.card.DeckFactory;
import models.card.card_structure.Card;
import models.game_settings.GameSettings;
import models.player.player_structure.Player;
import view.cli.LootView;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Keil, Vladislav
 */
public class LootViewController {
    private Scanner scanner;
    private Random rand = new Random();

    private Player player;
    private List<Card> choisenCards;
    private DeckFactory deckFactory;
    private LootView lootView;
    private int gold = 0;

    /**
     * Anzahl an möglichen Karten wird initialisiert, je nach Schwierigkeit.
     * Für eine neuinitialisierung muss ein neuer LootViewCohtroller geschaffen werden.
     * @param player wird für die Loot-Deckerstellung benötigt.
     * @param fieldType Gold Faktor für den Loot
     */
    public LootViewController(Player player, String fieldType) {
        this.scanner = new Scanner(System.in);
        this.lootView = new LootView();
        int amount = 5;
        initGoldLoot(fieldType);

        switch (GameSettings.getDifficultyLevel()) {
            case SUPEREASY:
            case EASY:
                amount = 5;
                this.gold = (int) (this.gold * 1.5);
                break;
            case NORMAL:
                amount = 3;
                break;
            case IMPOSSIBLE:
            case HARD:
                amount = 1;
                this.gold = (int) (this.gold * 0.5);
                break;
        }

        this.player = player;
        this.deckFactory = new DeckFactory(player, amount);
        this.choisenCards = initialLootDeck();
    }

    private List<Card> initialLootDeck() {
        return this.deckFactory.init();
    }

    private void initGoldLoot(String fieldType) {
        // Initialisierung
        switch (fieldType) {
            case "BossField": {
                // Boss Encounter: 95-105 Gold
                this.gold = rand.nextInt(105 + 1 - 95) + 95;
                break;
            }
            case "EliteField": {
                // Elite Encounter: 25-35 Gold
                this.gold = rand.nextInt(35 + 1 - 25) + 25;
                break;
            }
            case "EnemyField": {
                // Normal Encounter: 10-20 Gold
                this.gold = rand.nextInt(20 + 1 - 10) + 10;
                break;
            }
        }
    }

    /**
     * Öffnet den LootView.
     * @param player um Spieler Gold/ Karten zu übergeben
     */
    public void openLootView(Player player) {
        int input = 0;

        while(true){
            lootView.display( this.choisenCards, this.gold);
            lootView.displayCardChoiceMenu(this.choisenCards.size());
            try{
                System.out.print(">> ");
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                ConsoleAssistent.print(Color.YELLOW, "Wrong input...");
            }
        }

        switch (input) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                addCardToDeck(this.choisenCards.get(input - 1));
                break;
            case 0:
                return;
            default:
                ConsoleAssistent.print(Color.YELLOW, "Wrong input...");
                openLootView(player);
        }
    }

    private void addCardToDeck(Card card) {
        this.player.addCardToDeck(card);
        this.player.increaseGold(this.gold);
    }
}
