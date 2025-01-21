package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.gui.ShopView;
import de.bundeswehr.auf.slaythespire.gui.events.ShopViewEvents;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.helper.MusicBoy;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.PotionFactory;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.RelicFactory;
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
    private final Relic purchasableRelic;
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
        purchasablePotion = PotionFactory.generatePotion();
        RelicFactory relicFactory = new RelicFactory(player);
        purchasableRelic = relicFactory.generateRelicForShop();

        entryShop();
    }

    @Override
    public void discard() {
        shopView.discard();
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
     * @param card Die geklickte Karte.
     */
    @Override
    public void onCardClick(Card card) {
        int price = card.getPrice();

        if (player.getGold() >= price) {
            player.decreaseGold(price);
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
        int price = potion.getPrice();

        if (player.getGold() >= price) {
            if (player.getPotions().size() < 3) {
                player.decreaseGold(price);
                player.addPotion(potion);
                refreshSelectablePotion();
                LoggingAssistant.log("Refresh Potion");
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
     */
    @Override
    public void onRelicClick(Relic relic) {
        int price = relic.getPrice();

        if (player.getGold() >= price) {
            player.decreaseGold(price);
            player.addRelic(relic);
            refreshSelectableRelic();
            LoggingAssistant.log("Refresh Relic");
        }
        else {
            LoggingAssistant.log("Not enough Gold", Color.YELLOW);
            shopView.showDialog("You have not enough Gold!");
        }
    }

    /**
     * Initialisiert den Shop und die ShopViewEvents.
     */
    private void entryShop() {
        shopView = new ShopView(player, purchasableCards, this, purchasablePotion, purchasableRelic);
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
        shopView.setPurchasablePotion();
    }

    /**
     * Aktualisiert die kaufbaren Relics im Shop.
     */
    private void refreshSelectableRelic() {
        shopView.setPurchasableRelic();
    }

}
