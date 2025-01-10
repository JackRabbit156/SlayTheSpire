package de.bundeswehr.auf.slaythespire.gui;

import de.bundeswehr.auf.slaythespire.gui.events.CardEventListener;
import de.bundeswehr.auf.slaythespire.gui.events.ShopViewEvents;
import de.bundeswehr.auf.slaythespire.gui.layouts.shop.*;
import de.bundeswehr.auf.slaythespire.gui.layouts.top_bar.PotionLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.top_bar.TopBarLayout;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

import java.util.List;

/**
 * Die Klasse ShopView verwaltet die grafische Darstellung der Shop-Ansicht im Spiel.
 * Sie zeigt die kaufbaren Karten und Tränke an und ermöglicht es dem Spieler, mit diesen zu interagieren.
 *
 * @author Vladislav Keil
 */
public class ShopView extends StackPane implements CardEventListener, WithTopBar {

    private VBox center;
    private final BorderPane entryLayout = new BorderPane();
    private final Insets insets = new Insets(15, 15, 15, 15);
    private final BorderPane outerLayout = new BorderPane();
    private final Player player;
    private Potion potion;
    private List<Card> shopCards;
    private final BorderPane shopLayout = new BorderPane();
    private ShopViewEvents shopViewEvents;
    private TopBarLayout top;

    public ShopView(Player player, List<Card> shopCards, ShopViewEvents shopViewEvents, Potion... potion) {
        this.player = player;
        this.shopCards = shopCards;
        this.shopViewEvents = shopViewEvents;
        this.potion = potion[0] != null ? potion[0] : null;
        display();
    }

    @Override
    public void discard() {
        top.discard();
    }

    /**
     * Initialisiert die View.
     */
    public void display() {
        getChildren().addAll(entryLayout, shopLayout, outerLayout);

        setBackground(new Background(GuiHelper.backgroundInHD(player.getActImage())));

        entryLayout.setPickOnBounds(false);
        shopLayout.setPickOnBounds(false);
        outerLayout.setPickOnBounds(false);

        initEntryLayout();
        shopLayout.setPadding(new Insets(70, 0, 0, 0));
        initOuterLayout();
    }

    /**
     * Initialisiert die Ereignisse der Shop-Ansicht.
     *
     * @param shopViewEvents Die Ereignisse der Shop-Ansicht.
     */
    public void initShopViewEvents(ShopViewEvents shopViewEvents) {
        this.shopViewEvents = shopViewEvents;
    }

    public void onBackClick() {
        shopViewEvents.onBackClicked();
    }

    /**
     * Aktion bei Klicken auf eine Karte.
     * Ruft das entsprechende Ereignis auf.
     *
     * @param card Die angeklickte Karte.
     */
    @Override
    public void onCardClick(Card card) {
        shopViewEvents.onCardClick(card);
    }

    @Override
    public void onFullScreen() {
        shopViewEvents.onFullScreenClick();
    }

    public void onMerchantClick() {
        initShopLayout();
    }

    /**
     * Aktion bei Klicken auf einen Trank.
     * Ruft das entsprechende Ereignis auf.
     *
     * @param card Der angeklickte Trank.
     */
    public void onPotionClick(Potion card) {
        shopViewEvents.onPotionClick(card);
    }

    /**
     * Setzt die kaufbare Trankkarte auf null und initialisiert die Center View neu.
     */
    public void setPurchaseablePotion() {
        potion = null;
        initCenter();
    }

    /**
     * Initialisiert die Center View mit den übergebenen Karten.
     *
     * @param purchasableCards Die Liste der kaufbaren Karten.
     */
    public void setShopCards(List<Card> purchasableCards) {
        shopCards = purchasableCards;
        initCenter();
    }

    /**
     * Zeigt ein Popup-Fenster mit einer Nachricht an.
     *
     * @param text Der Text im Popup-Fenster.
     */
    public void showDialog(String text) {
        Image popupImage = new Image(getClass().getResource("/images/popup/popupBg.png").toExternalForm());
        ImageView imageView = new ImageView(popupImage);
        imageView.setScaleX(0.5);
        imageView.setScaleY(0.5);

        StackPane stackPopup = new StackPane();
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setWrapText(true);
        label.setMaxWidth(400);
        label.setStyle("-fx-font-size: 36;" +
                "-fx-font-family: Kreon;");
        label.setTextFill(Color.WHITE);
        stackPopup.getChildren().addAll(imageView, label);

        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.getContent().add(stackPopup);
        Bounds bounds = center.localToScreen(center.getBoundsInLocal());
        popup.show(center.getScene().getWindow(), bounds.getMinX(), bounds.getMinY());
    }

    /**
     * Initialisiert das zentrale Layout der Shop-Ansicht.
     */
    private void initCenter() {
        center = new VBox();
        // Card Options
        CardSelectionLayout cardSelectionLayout = new CardSelectionLayout(this.shopCards, this);

        center.setPadding(insets);
        center.setAlignment(Pos.TOP_CENTER);
        center.getChildren().add(cardSelectionLayout);

        // Potion Options
        FlowPane potionSelectionLayout;
        if (this.potion != null) {
            potionSelectionLayout = new PotionSelectionLayout(this.potion, this);
        }
        else {
            potionSelectionLayout = new FlowPane();
        }
        center.getChildren().add(potionSelectionLayout);
        shopLayout.setCenter(center);
    }

    private void initEntryLayout() {
        EntryLayout entry = new EntryLayout(this, player.getImagePath());
        this.entryLayout.setCenter(entry);

    }

    private void initOuterLayout() {
        top = new TopBarLayout(this, player, new PotionLayout(player.getPotions()));
        outerLayout.setTop(top);

        BackLayout back = new BackLayout(this);
        HBox buttonZone = new HBox(back);
        buttonZone.setAlignment(Pos.TOP_LEFT);
        buttonZone.setPadding(new Insets(50, 50, 50, 50));
        outerLayout.setBottom(buttonZone);
    }

    /**
     * Initialisiert das Layout der Shop-Ansicht.
     */
    private void initShopLayout() {
        shopLayout.setBackground(new Background(GuiHelper.backgroundInHD("/images/shop/shop_panel.png")));
        initTop();
        initCenter();
    }

    private void initTop() {
        Image img = new Image(getClass().getResource("/images/banner/abandon.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        StackPane titlePane = new StackPane();

        Label label = new Label();
        label.setText("Welcome to Shop.");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 38px; -fx-font-family: Kreon;");

        titlePane.getChildren().addAll(imageView, label);
        shopLayout.setTop(titlePane);
    }

}