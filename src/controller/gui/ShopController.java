package controller.gui;

import helper.Color;
import helper.ConsoleAssistent;
import helper.GuiHelper;
import models.card.DeckFactory;
import models.card.card_structure.Card;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;
import models.relic.relic_structure.Relic;
import view.gui.ShopView;
import view.gui.layouts.layout_events.ShopViewEvents;

import java.util.List;

/**
 * @author Keil, Vladislav
 */
public class ShopController implements ShopViewEvents {
    private Player player;
    private List<Card> purchasableCards;
    private DeckFactory deckFactory;
    private ShopView shopView;
    private PotionCard purchasablePotion;
    private List<Relic> purchasableRelics;

     public ShopController(Player player) {
        this.player = player;

        this.deckFactory = new DeckFactory(player, 5);
        // Bei jeder Initialisierung wird der Shop befüllt.
        // Wird beim Spielstart ausgeführt und bei jedem Act.
        this.purchasableCards = this.deckFactory.init();
        this.purchasablePotion = this.deckFactory.generatePotion();
    }

    /**
     * Initialisierung des Shops und des ShopViewEvents
     */
    public void entryShop() {
        this.shopView = new ShopView(player, purchasableCards, this, purchasablePotion);
        this.shopView.initShopViewEvents(this);
    }

    private void refreshSelectableCards(){
        this.shopView.setShopCards(purchasableCards);
    }

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

    @Override
    public void onPotionClick(PotionCard potion) {
        int cardPrice = potion.getPrice();

        if (this.player.getGold() >= cardPrice) {
            if(this.player.getPotionCards().size() < 3) {
                this.player.decreaseGold(cardPrice);
                this.player.addPotionCard(potion);
                refreshSelectablePotion();
                ConsoleAssistent.print(Color.YELLOW, "Refresh Cards!");
            } else {
                this.shopView.showDialog("You have reached the maximum of Potion.");
            }
        } else {
            System.out.println();
            this.shopView.showDialog("Not enough Gold!");
            ConsoleAssistent.print(Color.YELLOW, "Not enough Gold!");
        }
    }

    private void refreshSelectablePotion() {
        this.shopView.setPurchaseablePotion();
    }

    @Override
    public void onRelicClick(Relic relic, int index) {

    }

    @Override
    public void onBackClicked() {
        ConsoleAssistent.print(Color.YELLOW, "Shop Leaved!");
        GuiHelper.Scenes.startMapScene(player, true);
    }

    /**
     * Übergabe der ShopView
     * @return ShopView
     */
    public ShopView getShopView() {
        return this.shopView;
    }
}
