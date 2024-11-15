package controller.gui;

import helper.Color;
import helper.ConsoleAssistent;
import models.cards.DeckFactory;
import models.cards.card_structure.Card;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;
import models.relics.relic_structure.Relic;
import view.gui.RestView;
import view.gui.ShopView;
import view.gui.layouts.layout_events.ShopViewEvents;

import java.util.List;

/**
 * @author Keil, Vladislav
 */
public class ShopViewController implements ShopViewEvents {
    private Player player;
    private List<Card> purchasableCards;
    private DeckFactory deckFactory;
    private ShopView shopView;
    private List<PotionCard> purchasablePotions;
    private List<Relic> purchasableRelics;

    public ShopViewController(Player player) {
        this.player = player;

        this.deckFactory = new DeckFactory(player, 5);
        // Bei jeder Initialisierung wird der Shop befüllt.
        // Wird beim Spielstart ausgeführt und bei jedem Act.
        this.purchasableCards = this.deckFactory.init();
    }

    /**
     * Auswahl der Kaufoptionen.
     */
    public void entryShop() {
        this.shopView = new ShopView(player, purchasableCards, this);
        this.shopView.initShopViewEvents(this);
    }

    private void refreshSelectableCards(){
        this.shopView.setShopCards(purchasableCards);
    }


    @Override
    public void onCardClick(Card card, int index) {
        int cardPrice = card.getPrice();

        if (this.player.getGold() > cardPrice) {
            this.player.decreaseGold(cardPrice);
            this.player.addCardToDeck(card);
            this.purchasableCards.remove(card);
            refreshSelectableCards();
            ConsoleAssistent.print(Color.RED, "Refresh Cards in Shop!");

        } else {
            System.out.println();
            this.shopView.showDialog("Not enough Gold in Shop!");
            ConsoleAssistent.print(Color.YELLOW, "Not enough Gold in Shop!");
        }
    }

    @Override
    public void onPotionClick(PotionCard potion, int index) {

    }

    @Override
    public void onRelicClick(Relic relic, int index) {

    }

    @Override
    public void onBackClick() {
        // TODO Back Option wie?
    }

    public ShopView getShopView() {
        return this.shopView;
    }
}
