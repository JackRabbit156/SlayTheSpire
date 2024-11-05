package controller;

import helper.Color;
import helper.ConsoleAssistent;
import models.cards.DeckFactory;
import models.cards.card_structure.Card;
import models.player.player_structure.Player;
import models.relics.relic_structure.Relic;
import view.ShopView;

import java.util.List;
import java.util.Scanner;

/*
() Im Shop kann man
* Karten,
* () S: Potions und
* () K: Relics kaufen.
() Diese kosten Gold.
() Der Shop wird bei Spielstart und bei Wechsel des Acts neu generiert.
Die Zusammenstellung der verkauften Gegenstände ist zufällig.
 */

public class ShopViewController {
    private Player player;
    private List<Card> purchasableCards;
    private List<Relic> purchasableRelics;
    private DeckFactory deckFactory;
    private ShopView shopView;
    private Scanner scanner;

//    private List<Potion> purchasablePotions; // Soll Potions


    public ShopViewController(Player player) {
        this.scanner = new Scanner(System.in);
        this.player = player;
        this.deckFactory = new DeckFactory(player, 5);
        this.shopView = new ShopView();
        // Bei jeder Initialisierung wird der Shop befüllt.
        // Wird beim Spielstart ausgeführt und bei jedem Act.
        this.purchasableCards = this.deckFactory.init();
    }

    /**
     * Auswahl der Kaufoptionen.
     */
    public void entryShop() {
        ConsoleAssistent.clearScreen();
        shopView.display(player, purchasableCards);
        shopView.displayOptionChoiceMenu();

        switch (scanner.nextInt()) {
            case 3:
            case 2:
            case 1:
                cardChoice();
                break;
            case 0:
                return;
            default:
                entryShop();
        }
        return;
    }

    private void cardChoice() {
        ConsoleAssistent.clearScreen();
        shopView.displayCards(purchasableCards);
        shopView.displayCardChoiceMenu(purchasableCards.size());

        int input = scanner.nextInt();

        if (input > purchasableCards.size()) {
            ConsoleAssistent.print(Color.YELLOW, "Wrong Choice!");
            cardChoice();
        }

        switch (input) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                addCardToDeckAndRemoveFromShopDeck(purchasableCards.get(input - 1));
                cardChoice();
                break;
            case 0:
                entryShop();
        }
    }

    private void addCardToDeckAndRemoveFromShopDeck(Card card) {
        int cardPrice = card.getPrice();

        if (player.getGold() > cardPrice) {
            player.decreaseGold(cardPrice);
            player.addCardToDeck(card);

            purchasableCards.remove(card);
        } else {
            System.out.println();
            ConsoleAssistent.print(Color.YELLOW, "Not enough Gold!");
        }
    }

}
