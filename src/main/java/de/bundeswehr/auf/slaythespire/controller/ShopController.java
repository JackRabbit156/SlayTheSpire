package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.ConsoleAssistent;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.helper.MusicBoy;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.models.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.Card;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;
import de.bundeswehr.auf.slaythespire.models.potion.potion_structure.PotionCard;
import de.bundeswehr.auf.slaythespire.models.relic.relic_structure.Relic;
import de.bundeswehr.auf.slaythespire.gui.ShopView;
import de.bundeswehr.auf.slaythespire.gui.events.ShopViewEvents;

import java.util.List;

/**
 * Die Klasse ShopController steuert den Shop-Vorgang im Spiel und verwaltet die Anzeige der Shop-Ansicht.
 * Sie ermöglicht es dem Spieler, Karten und Tränke zu kaufen und in das Spieldeck aufzunehmen.
 *
 * @author Keil, Vladislav
 */
public class ShopController implements ShopViewEvents {

    private final Player player;
    private final List<Card> purchasableCards;
    private final PotionCard purchasablePotion;
    private List<Relic> purchasableRelics;
    private ShopView shopView;

    /**
     * Konstruktor für die Klasse ShopController.
     * Initialisiert den Spieler und füllt den Shop mit kaufbaren Gegenständen.
     *
     * @param player Der Spieler, der den Shop betritt.
     */
    public ShopController(Player player) {
        MusicBoy.play("shop");
        this.player = player;

        DeckFactory deckFactory = new DeckFactory(player, 5);
        // Bei jeder Initialisierung wird der Shop befüllt.
        // Wird beim Spielstart ausgeführt und bei jedem Act.
        this.purchasableCards = deckFactory.init();
        this.purchasablePotion = deckFactory.generatePotion();
        entryShop();
    }

    /**
     * Gibt die Shop-Ansicht zurück.
     *
     * @return Die ShopView-Instanz.
     */
    public ShopView getShopView() {
        return this.shopView;
    }

    /**
     * Event-Handler für den Zurück-Klick im Shop.
     * Kehrt zur Kartenansicht zurück.
     */
    @Override
    public void onBackClicked() {
        ConsoleAssistent.print(Color.YELLOW, "Left shop!");
        GuiHelper.Scenes.startMapScene(player);
    }

    /**
     * Event-Handler für Klicks auf Karten im Shop.
     * Verringert das Gold des Spielers und fügt die Karte dem Deck des Spielers hinzu.
     *
     * @param card  Die geklickte Karte.
     * @param index Der Index der geklickten Karte.
     */
    @Override
    public void onCardClick(Card card, int index) {
        int cardPrice = card.getPrice();

        if (this.player.getGold() >= cardPrice) {
            this.player.decreaseGold(cardPrice);
            this.player.addCardToDeck(card);
            this.purchasableCards.remove(card);
            refreshSelectableCards();
            ConsoleAssistent.print(Color.YELLOW, "Refresh Cards!");
        }
        else {
            this.shopView.showDialog("You have not enough Gold!");
            ConsoleAssistent.print(Color.YELLOW, "Not enough Gold!");
        }
    }

    @Override
    public void onFullscreenClick() {
        Stage primaryStage = player.getPrimaryStage();

        primaryStage.setFullScreen(!primaryStage.isFullScreen());
    }

    /**
     * Event-Handler für Klicks auf Tränke im Shop.
     * Verringert das Gold des Spielers und fügt den Trank dem Inventar des Spielers hinzu.
     *
     * @param potion Der geklickte Trank.
     */
    @Override
    public void onPotionClick(PotionCard potion) {
        int cardPrice = potion.getPrice();

        if (this.player.getGold() >= cardPrice) {
            if (this.player.getPotionCards().size() < 3) {
                this.player.decreaseGold(cardPrice);
                this.player.addPotionCard(potion);
                refreshSelectablePotion();
                ConsoleAssistent.print(Color.YELLOW, "Refresh Cards!");
            }
            else {
                this.shopView.showDialog("You have reached the maximum amount of Potions.");
                ConsoleAssistent.print(Color.YELLOW, "Maximum amount of Potions.");
            }
        }
        else {
            this.shopView.showDialog("You have not enough Gold!");
            ConsoleAssistent.print(Color.YELLOW, "Not enough Gold!");
        }
    }

    /**
     * Event-Handler für Klicks auf Relikte im Shop.
     *
     * @param relic Das geklickte Relikt.
     * @param index Der Index des geklickten Relikts.
     */
    @Override
    public void onRelicClick(Relic relic, int index) {
        // Logik zum Kaufen von Relikten kann hier hinzugefügt werden.
    }

    /**
     * Initialisiert den Shop und die ShopViewEvents.
     */
    private void entryShop() {
        this.shopView = new ShopView(player, purchasableCards, this, purchasablePotion);
        this.shopView.initShopViewEvents(this);
    }

    /**
     * Aktualisiert die Liste der kaufbaren Karten im Shop.
     */
    private void refreshSelectableCards() {
        this.shopView.setShopCards(purchasableCards);
    }

    /**
     * Aktualisiert die kaufbaren Tränke im Shop.
     */
    private void refreshSelectablePotion() {
        this.shopView.setPurchaseablePotion();
    }

}
