package view.gui;

import helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import models.card.card_structure.Card;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;
import view.gui.layouts.layout_events.ShopViewEvents;
import view.gui.layouts.shop_layout.CardSelectionLayout;
import view.gui.layouts.shop_layout.PotionSelectionLayout;

import java.util.List;

/**
 * Die Klasse ShopView verwaltet die grafische Darstellung der Shop-Ansicht im Spiel.
 * Sie zeigt die kaufbaren Karten und Tränke an und ermöglicht es dem Spieler, mit diesen zu interagieren.
 *
 * @autor Vladislav Keil
 */
public class ShopView extends StackPane {
    private List<Card> shopCards;
    private Player player;
    private ShopViewEvents shopViewEvents;
    private PotionCard potionCard;

    private Insets insets = new Insets(15,15,15,15);
    private VBox centerVBox;
    private VBox topVBox;
    private Popup popup;

    private BorderPane shopLayout;
    private BorderPane bottomLayout;

    /**
     * Konstruktor für die Klasse ShopView.
     * Initialisiert die Ansicht mit einem Spieler, kaufbaren Karten und Ereignissen der Shop-Ansicht.
     *
     * @param player           Der Spieler, der den Shop betritt.
     * @param shopCards        Die Liste der im Shop verfügbaren Karten.
     * @param shopViewEvents   Die Ereignisse der Shop-Ansicht.
     * @param potionCard       Optional: Die im Shop verfügbare Trankkarte.
     */
    public ShopView(Player player, List<Card> shopCards, ShopViewEvents shopViewEvents, PotionCard... potionCard) {
        shopLayout = new BorderPane();
        bottomLayout = new BorderPane();
        this.player = player;
        this.shopCards = shopCards;
        this.shopViewEvents = shopViewEvents;
        this.potionCard = potionCard[0] != null ? potionCard[0] :  null;
        display();
    }

    /**
     * Initialisiert die View.
     */
    public void display() {
        getChildren().add(shopLayout);
        getChildren().add(bottomLayout);

        setBackground(new Background(GuiHelper.background("/images/backgrounds/shop_panel_bg.png")));
        initShopLayout();
        initBottomLayout();
    }

    /**
     * Initialisiert das untere Layout der Shop-Ansicht.
     */
    private void initBottomLayout() {
        bottomLayout.setPickOnBounds(false);
        initBottom();
    }

    /**
     * Initialisiert das Layout der Shop-Ansicht.
     */
    private void initShopLayout() {
        shopLayout.setPickOnBounds(false);
        initTop();
        initCenter();
    }

    /**
     * Initialisiert die Ereignisse der Shop-Ansicht.
     *
     * @param shopViewEvents Die Ereignisse der Shop-Ansicht.
     */
    public void initShopViewEvents(ShopViewEvents shopViewEvents){
        this.shopViewEvents = shopViewEvents;
    }

    /**
     * Initialisiert das zentrale Layout der Shop-Ansicht.
     */
    private void initCenter(){
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
        } else {
            potionSelectionLayout = new FlowPane();
        }
        centerVBox.getChildren().add(potionSelectionLayout);
        shopLayout.setCenter(centerVBox);
    }

    /**
     * Initialisiert das obere Layout der Shop-Ansicht.
     */
    private void initTop(){
        topVBox = new VBox();

        Label label = new Label();
        label.setText("Welcome to Shop.");
        label.setId("title");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 56px; -fx-font-family: Kreon;");

        topVBox.getChildren().add(label);
        topVBox.setAlignment(Pos.BOTTOM_CENTER);
        shopLayout.setTop(topVBox);
    }

    /**
     * Initialisiert das untere Layout der Shop-Ansicht.
     */
    private void initBottom(){
        Image btnImage = new Image(getClass().getResource("/images/buttons/buttonL.png").toExternalForm());
        ImageView imgView = new ImageView(btnImage);
        HBox bottomHBox = new HBox();

        // Label Setzen
        Label label = new Label("Back");
        label.setTextFill(Paint.valueOf("White"));

        // Events und Button erstellung
        StackPane stackPaneBtn = GuiHelper.addButtonStackPane(imgView, label, 0.7, 0.7);
        stackPaneBtn.setTranslateY(-100);
        imgView.setOnMouseClicked(event -> shopViewEvents.onBackClicked());
        label.setOnMouseClicked(event -> shopViewEvents.onBackClicked());

        // HBox Config
        bottomHBox.setAlignment(Pos.TOP_LEFT);
        bottomHBox.setTranslateY(150);
        bottomHBox.getChildren().add(stackPaneBtn);
        bottomLayout.setBottom(bottomHBox);
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

        this.popup = new Popup();
        this.popup.setAutoHide(true);
        this.popup.getContent().add(stackPopup);
        this.popup.show(this.centerVBox.getScene().getWindow(), 800, 500);
    }

    /**
     * Setzt die kaufbare Trankkarte auf null und initialisiert die Center View neu.
     */
    public void setPurchaseablePotion() {
        this.potionCard = null;
        initCenter();
    }
}