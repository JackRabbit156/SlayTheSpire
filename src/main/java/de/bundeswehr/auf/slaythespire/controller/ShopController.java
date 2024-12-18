package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.gui.ShopView;
import de.bundeswehr.auf.slaythespire.gui.events.ShopViewEvents;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.helper.MusicBoy;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import javafx.stage.Stage;

import java.util.List;

/**
 * Die Klasse ShopController steuert den Shop-Vorgang im Spiel und verwaltet die Anzeige der Shop-Ansicht.
 * Sie ermöglicht es dem Spieler, Karten und Tränke zu kaufen und in das Spieldeck aufzunehmen.
 *
 * @author Keil, Vladislav
 */
public class ShopController implements Controller, ShopViewEvents {

    private final Player player;
    private final List<Card> purchasableCards;
    private final Potion purchasablePotion;
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
        purchasableCards = deckFactory.init(true);
        purchasablePotion = deckFactory.generatePotion();
        entryShop();
    }

    /**
     * Gibt die Shop-Ansicht zurück.
     *
     * @return Die ShopView-Instanz.
     */
    public ShopView getShopView() {
        return shopView;
    }

    /**
     * Event-Handler für den Zurück-Klick im Shop.
     * Kehrt zur Kartenansicht zurück.
     */
    @Override
    public void onBackClicked() {
        LoggingAssistant.log("Shop closed");
        GuiHelper.Scenes.startMapScene(player);
    }

    /**
     * Event-Handler für Klicks auf Karten im Shop.
     * Verringert das Gold des Spielers und fügt die Karte dem Deck des Spielers hinzu.
     *
     * @param card  Die geklickte Karte.
     */
    @Override
    public void onCardClick(Card card) {
        int cardPrice = card.getPrice();

        if (player.getGold() >= cardPrice) {
            player.decreaseGold(cardPrice);
            player.addCardToDeck(card);
            purchasableCards.remove(card);
            refreshSelectableCards();
            LoggingAssistant.log("Refresh Cards");
        }
        else {
            LoggingAssistant.log("Not enough Gold", Color.YELLOW);
            shopView.showDialog("You have not enough Gold!");
        }
    }

    @Override
    public void onFullScreenClick() {
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
    public void onPotionClick(Potion potion) {
        int cardPrice = potion.getPrice();

        if (player.getGold() >= cardPrice) {
            if (player.getPotions().size() < 3) {
                player.decreaseGold(cardPrice);
                player.addPotion(potion);
                refreshSelectablePotion();
                LoggingAssistant.log("Refresh Cards");
            }
            else {
                LoggingAssistant.log("Maximum amount of potions", Color.YELLOW);
                shopView.showDialog("You have reached the maximum amount of Potions.");
            }
        }
        else {
            LoggingAssistant.log("Not enough Gold", Color.YELLOW);
            shopView.showDialog("You have not enough Gold!");
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
        shopView = new ShopView(player, purchasableCards, this, purchasablePotion);
        shopView.initShopViewEvents(this);
    }

    /**
     * Aktualisiert die Liste der kaufbaren Karten im Shop.
     */
    private void refreshSelectableCards() {
        shopView.setShopCards(purchasableCards);
    }

    /**
     * Aktualisiert die kaufbaren Tränke im Shop.
     */
    private void refreshSelectablePotion() {
        shopView.setPurchaseablePotion();
    }

}
