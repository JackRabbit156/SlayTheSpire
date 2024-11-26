package controller.gui;

import helper.Color;
import helper.ConsoleAssistent;
import helper.GuiHelper;
import helper.MusicBoy;
import models.card.DeckFactory;
import models.card.card_structure.Card;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;
import models.relic.relic_structure.Relic;
import view.gui.ShopView;
import view.gui.layouts.layout_events.ShopViewEvents;

import java.util.List;

/**
 * Die Klasse ShopController steuert den Shop-Vorgang im Spiel und verwaltet die Anzeige der Shop-Ansicht.
 * Sie ermöglicht es dem Spieler, Karten und Tränke zu kaufen und in das Spieldeck aufzunehmen.
 *
 * @author Keil, Vladislav
 */
public class ShopController implements ShopViewEvents {
    private Player player;
    private List<Card> purchasableCards;
    private DeckFactory deckFactory;
    private ShopView shopView;
    private PotionCard purchasablePotion;
    private List<Relic> purchasableRelics;

    /**
     * Konstruktor für die Klasse ShopController.
     * Initialisiert den Spieler und füllt den Shop mit kaufbaren Gegenständen.
     *
     * @param player Der Spieler, der den Shop betritt.
     */
    public ShopController(Player player) {
        MusicBoy.play("shop");
        this.player = player;

        this.deckFactory = new DeckFactory(player, 5);
        // Bei jeder Initialisierung wird der Shop befüllt.
        // Wird beim Spielstart ausgeführt und bei jedem Act.
        this.purchasableCards = this.deckFactory.init();
        this.purchasablePotion = this.deckFactory.generatePotion();
        entryShop();
    }

    /**
     * Initialisiert den Shop und die ShopViewEvents.
     */
    private void entryShop() {
        this.shopView = new ShopView(player.getImagePath(), purchasableCards, this, purchasablePotion);
        this.shopView.initShopViewEvents(this);
    }

    /**
     * Aktualisiert die Liste der kaufbaren Karten im Shop.
     */
    private void refreshSelectableCards() {
        this.shopView.setShopCards(purchasableCards);
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
        } else {
            this.shopView.showDialog("Not enough Gold!");
            ConsoleAssistent.print(Color.YELLOW, "Not enough Gold!");
        }
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
            } else {
                this.shopView.showDialog("You have reached the maximum of Potion.");
            }
        } else {
            this.shopView.showDialog("Not enough Gold!");
            ConsoleAssistent.print(Color.YELLOW, "Not enough Gold!");
        }
    }

    /**
     * Aktualisiert die kaufbaren Tränke im Shop.
     */
    private void refreshSelectablePotion() {
        this.shopView.setPurchaseablePotion();
    }

    /**
     * Event-Handler für Klicks auf Relikte im Shop.
     *
     * @param relic  Das geklickte Relikt.
     * @param index Der Index des geklickten Relikts.
     */
    @Override
    public void onRelicClick(Relic relic, int index) {
        // Logik zum Kaufen von Relikten kann hier hinzugefügt werden.
    }

    /**
     * Event-Handler für den Zurück-Klick im Shop.
     * Kehrt zur Kartenansicht zurück.
     */
    @Override
    public void onBackClicked() {
        ConsoleAssistent.print(Color.YELLOW, "Shop Leaved!");
        GuiHelper.Scenes.startMapScene(player);
    }

    /**
     * Gibt die Shop-Ansicht zurück.
     *
     * @return Die ShopView-Instanz.
     */
    public ShopView getShopView() {
        return this.shopView;
    }
}
