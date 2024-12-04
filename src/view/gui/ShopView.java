package view.gui;

import helper.GuiHelper;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import models.card.card_structure.Card;
import models.potion.potion_structure.PotionCard;
import view.gui.layouts.layout_events.ShopViewEvents;
import view.gui.layouts.shop_layout.BackLayout;
import view.gui.layouts.shop_layout.CardSelectionLayout;
import view.gui.layouts.shop_layout.EntryLayout;
import view.gui.layouts.shop_layout.PotionSelectionLayout;

import java.util.List;

/**
 * Die Klasse ShopView verwaltet die grafische Darstellung der Shop-Ansicht im Spiel.
 * Sie zeigt die kaufbaren Karten und Tränke an und ermöglicht es dem Spieler, mit diesen zu interagieren.
 *
 * @author Vladislav Keil
 */
public class ShopView extends StackPane {

    private final BorderPane backLayout = new BorderPane();
    private VBox centerVBox;
    private final BorderPane entryLayout = new BorderPane();
    private final Insets insets = new Insets(15, 15, 15, 15);
    private final String playerImagePath;
    private PotionCard potionCard;
    private List<Card> shopCards;
    private final BorderPane shopLayout = new BorderPane();
    private ShopViewEvents shopViewEvents;

    public ShopView(String playerImagePath, List<Card> shopCards, ShopViewEvents shopViewEvents, PotionCard... potionCard) {
        this.playerImagePath = playerImagePath;
        this.shopCards = shopCards;
        this.shopViewEvents = shopViewEvents;
        this.potionCard = potionCard[0] != null ? potionCard[0] : null;
        display();
    }

    /**
     * Initialisiert die View.
     */
    public void display() {
        getChildren().addAll(this.entryLayout, this.shopLayout, this.backLayout);

        setBackground(new Background(GuiHelper.background("/images/act1.png")));

        this.entryLayout.setPickOnBounds(false);
        this.backLayout.setPickOnBounds(false);
        this.shopLayout.setPickOnBounds(false);

        initEntryLayout();
        initBackLayout();
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
        this.shopViewEvents.onBackClicked();
    }

    /**
     * Aktion bei Klicken auf eine Karte.
     * Ruft das entsprechende Ereignis auf.
     *
     * @param card  Die angeklickte Karte.
     * @param index Der Index der angeklickten Karte.
     */
    public void onCardClick(Card card, int index) {
        shopViewEvents.onCardClick(card, index);
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
    public void onPotionClick(PotionCard card) {
        shopViewEvents.onPotionClick(card);
    }

    /**
     * Setzt die kaufbare Trankkarte auf null und initialisiert die Center View neu.
     */
    public void setPurchaseablePotion() {
        this.potionCard = null;
        initCenter();
    }

    /**
     * Initialisiert die Center View mit den übergebenen Karten.
     *
     * @param purchasableCards Die Liste der kaufbaren Karten.
     */
    public void setShopCards(List<Card> purchasableCards) {
        this.shopCards = purchasableCards;
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
        label.setStyle("-fx-font-size: 36;" +
                "-fx-font-family: Kreon;");
        label.setTextFill(Paint.valueOf("White"));
        label.autosize();
        stackPopup.getChildren().addAll(imageView, label);

        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.getContent().add(stackPopup);
        Bounds bounds = this.centerVBox.localToScreen(this.centerVBox.getBoundsInLocal());
        popup.show(this.centerVBox.getScene().getWindow(), bounds.getMinX(), bounds.getMinY());
    }

    private void initBackLayout() {
        BackLayout back = new BackLayout(this);
        HBox buttonZone = new HBox(back);
        buttonZone.setAlignment(Pos.TOP_LEFT);
        buttonZone.setPadding(new Insets(50, 50, 50, 50));
        this.backLayout.setBottom(buttonZone);
    }

    /**
     * Initialisiert das zentrale Layout der Shop-Ansicht.
     */
    private void initCenter() {
        centerVBox = new VBox();
        // Card Options
        CardSelectionLayout cardSelectionLayout = new CardSelectionLayout(this.shopCards, this);

        centerVBox.setSpacing(30);
        centerVBox.setPadding(insets);
        centerVBox.setAlignment(Pos.TOP_CENTER);
        centerVBox.getChildren().add(cardSelectionLayout);

        // Potion Options
        FlowPane potionSelectionLayout;
        if (this.potionCard != null) {
            potionSelectionLayout = new PotionSelectionLayout(this.potionCard, this);
        }
        else {
            potionSelectionLayout = new FlowPane();
        }
        centerVBox.getChildren().add(potionSelectionLayout);
        shopLayout.setCenter(centerVBox);
    }

    private void initEntryLayout() {
        EntryLayout entry = new EntryLayout(this, playerImagePath);
        this.entryLayout.setCenter(entry);

    }

    /**
     * Initialisiert das Layout der Shop-Ansicht.
     */
    private void initShopLayout() {
        this.shopLayout.setBackground(new Background(GuiHelper.background("/images/backgrounds/shop_panel_bg.png")));
        initTop();
        initCenter();
    }

    /**
     * Initialisiert das obere Layout der Shop-Ansicht.
     */
    private void initTop() {
        Image img = new Image(getClass().getResource("/images/banner/abandon.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        VBox topVBox = new VBox();
        StackPane titlePane = new StackPane();

        Label label = new Label();
        label.setText("Welcome to Shop.");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 38px; -fx-font-family: Kreon;");

        titlePane.getChildren().addAll(imageView, label);

        topVBox.getChildren().add(titlePane);
        topVBox.setAlignment(Pos.BOTTOM_CENTER);
        shopLayout.setTop(topVBox);
    }

}