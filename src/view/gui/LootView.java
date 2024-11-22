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
import models.potion.potion_structure.PotionCard;
import view.gui.layouts.layout_events.LootViewEvents;
import view.gui.layouts.loot_layout.CardSelectionLayout;

import java.util.List;
/**
 * @author Keil, Vladislav
 */
public class LootView extends StackPane {
    private PotionCard potionCard;
    private List<Card> lootCards;
    private int gold;
    private LootViewEvents lootViewEvents;

    private Popup popup;
    private Insets insets = new Insets(15,15,15,15);
    private VBox centerVBox;
    private VBox topVBox;
    private Popup cardSelectionPopup;

    private BorderPane lootLayout;
    private BorderPane bottomLayout;
    private boolean cardSelectionDisabled;
    private StackPane cardSelectionButtonStackPane;

    /**
     * Konstruktor für die Klasse LootView.
     * Initialisiert die Ansicht ohne eine Trankkarte.
     *
     * @param lootCards          Die Liste der verfügbaren Karten.
     * @param gold               Die Menge an Gold im Schatz.
     * @param lootViewEvents     Die Ereignisse der Loot-Ansicht.
     */
    public LootView(List<Card> lootCards, int gold, LootViewEvents lootViewEvents) {
        this.lootLayout = new BorderPane();
        this.bottomLayout = new BorderPane();
        this.lootCards = lootCards;
        this.gold = gold;
        this.lootViewEvents = lootViewEvents;
    }

    /**
     * Konstruktor für die Klasse LootView.
     * Initialisiert die Ansicht mit einer Trankkarte.
     *
     * @param lootCards          Die Liste der verfügbaren Karten.
     * @param gold               Die Menge an Gold im Schatz.
     * @param potionCard         Die im Schatz enthaltene Trankkarte.
     * @param lootViewEvents     Die Ereignisse der Loot-Ansicht.
     */
    public LootView(List<Card> lootCards, int gold, PotionCard potionCard, LootViewEvents lootViewEvents) {
        this(lootCards, gold, lootViewEvents);
        this.potionCard = potionCard;
        this.cardSelectionPopup = new Popup();
        display();
    }

    /**
     * Initialisiert die View.
     */
    public void display() {
        getChildren().add(lootLayout);
        getChildren().add(bottomLayout);

        setBackground(new Background(GuiHelper.background("/images/backgrounds/greenBg.jpg")));
        initLootLayout();
        initBottomLayout();

    }

    /**
     * Initialisiert das Layout der Loot-Ansicht.
     */
    private void initLootLayout() {
        lootLayout.setPickOnBounds(false);
        initTop();
        initCenter();
    }

    /**
     * Initialisiert das untere Layout der Loot-Ansicht.
     */
    private void initBottomLayout() {
        bottomLayout.setPickOnBounds(false);
        initBottom();
    }

    /**
     * Initialisiert das zentrale Layout der Loot-Ansicht.
     */
    private void initCenter(){
        // Card Options
        centerVBox = new VBox();
        centerVBox.setSpacing(10);
        centerVBox.setAlignment(Pos.TOP_CENTER);

        // Gold Option
        StackPane goldStackPane = getGoldStackPane();
        centerVBox.getChildren().add(goldStackPane);

        // Potion Option
        if (this.potionCard != null) {
            StackPane getPotionStackPane = getPotionStackPane();
            centerVBox.getChildren().add(getPotionStackPane);
        }

        // Card Selection Option
        setCardSelectionLayout();

        lootLayout.setCenter(centerVBox);
    }


    /**
     * Initialisiert das obere Layout der Loot-Ansicht.
     */
    private void initTop(){
        topVBox = new VBox();
        Label label = new Label();
        label.setText("Loot");
        label.setId("title");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 56px;");

        topVBox.getChildren().add(label);
        topVBox.setAlignment(Pos.BOTTOM_CENTER);
        topVBox.setPrefHeight(200);
        lootLayout.setTop(topVBox);
    }

    /**
     * Initialisiert das untere Layout der Loot-Ansicht.
     */
    private void initBottom(){
        Image btnImage = new Image(getClass().getResource("/images/buttons/buttonL.png").toExternalForm());
        ImageView imgView = new ImageView(btnImage);
        HBox bottomHBox = new HBox();

        Label label = new Label("Back");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24;");

        bottomHBox.getChildren().add(GuiHelper.addButtonStackPane(imgView, label, 0.7));

        imgView.setOnMouseClicked(event -> lootViewEvents.onBackClicked());
        label.setOnMouseClicked(event -> lootViewEvents.onBackClicked());

        bottomHBox.setAlignment(Pos.TOP_LEFT);
        bottomHBox.setTranslateY(150);
        bottomLayout.setBottom(bottomHBox);
    }

    /**
     * Setzt das Layout für die Kartenauswahl.
     */
    private void setCardSelectionLayout() {
        // Card Selection Layout
        CardSelectionLayout cardSelectionLayout = new CardSelectionLayout(this.lootCards, this);
        cardSelectionLayout.setPadding(new Insets(50,50,15,50));
        this.cardSelectionButtonStackPane = getCardSelectionStackPane();
        this.centerVBox.getChildren().add(this.cardSelectionButtonStackPane);

        // Popup
        this.cardSelectionPopup.setAutoHide(true);
        this.cardSelectionPopup.getContent().add(cardSelectionLayout);

        this.cardSelectionButtonStackPane.setOnMouseClicked(event -> {
            if (!this.cardSelectionDisabled) {
                this.cardSelectionPopup.show(cardSelectionButtonStackPane.getScene().getWindow(), 300, 300);
            }
        });
    }

    /**
     * Erzeugt das StackPane für die Kartenauswahl.
     *
     * @return Das StackPane für die Kartenauswahl.
     */
    private StackPane getCardSelectionStackPane() {
        Image btnImage = new Image(getClass().getResource("/images/panel/rewardListItemPanel.png").toExternalForm());
        Image img = new Image(getClass().getResource("/images/card/cardSymbol.png").toExternalForm());
        ImageView imgView = new ImageView(img);
        ImageView itemPanelView = new ImageView(btnImage);
        imgView.setScaleY(0.6);
        imgView.setScaleX(0.6);

        // Label
        Label label = new Label("New Cards!");
        label.setStyle("-fx-font-size: 28px; -fx-font-family: Kreon;");
        label.setTextFill(Paint.valueOf("White"));

        // Loot
        HBox lootBox = new HBox();
        lootBox.setAlignment(Pos.CENTER);
        lootBox.getChildren().addAll(imgView, label);

        // Panel
        StackPane cardSelectionStackPane = new StackPane(itemPanelView);
        cardSelectionStackPane.getChildren().add(lootBox);
        cardSelectionStackPane.setAlignment(Pos.CENTER);

        return cardSelectionStackPane;
    }

    /**
     * Erzeugt das StackPane für die Gold-Option.
     *
     * @return Das StackPane für die Gold-Option.
     */
    private StackPane getGoldStackPane() {
        Image btnImage = new Image(getClass().getResource("/images/panel/rewardListItemPanel.png").toExternalForm());
        Image img = new Image(getClass().getResource("/images/gold.png").toExternalForm());
        ImageView imgView = new ImageView(img);
        ImageView itemPanelView = new ImageView(btnImage);

        // Label
        Label label = new Label(String.valueOf(gold));
        label.setText(String.valueOf(gold));
        label.setStyle("-fx-font-size: 28px; -fx-font-family: Kreon;");
        label.setTextFill(Paint.valueOf("White"));
        // Loot
        HBox lootBox = new HBox();
        lootBox.setAlignment(Pos.CENTER);
        lootBox.getChildren().addAll(imgView,label);

        // Panel
        StackPane goldStackPane = new StackPane(itemPanelView);
        goldStackPane.getChildren().add(lootBox);
        goldStackPane.setAlignment(Pos.CENTER);

//        GuiHelper.setButtonHoverEffect(itemPanelView, label);

        goldStackPane.setOnMouseClicked(event -> {
            if(!goldStackPane.isDisabled()) {
                onGoldClick();
            }
            goldStackPane.setOpacity(0.6);
            goldStackPane.setDisable(true);
        });

        return goldStackPane;
    }

    /**
     * Erzeugt das StackPane für die Trank-Option.
     *
     * @return Das StackPane für die Trank-Option.
     */
    private StackPane getPotionStackPane() {
        Image btnImage = new Image(getClass().getResource("/images/panel/rewardListItemPanel.png").toExternalForm());
        ImageView itemPanelView = new ImageView(btnImage);
        Image img = new Image(getClass().getResource(potionCard.getImagePath()).toExternalForm());
        ImageView imgView = new ImageView(img);

        // Label
        Label label = new Label();
        label.setText(potionCard.getName());
        label.setStyle("-fx-font-size: 28px; -fx-font-family: Kreon;");
        label.setTextFill(Paint.valueOf("White"));

        // Loot
        HBox lootBox = new HBox();
        lootBox.setAlignment(Pos.CENTER);
        lootBox.getChildren().addAll(imgView, label);

        // Panel
        StackPane potionStackPane = new StackPane(itemPanelView);
        potionStackPane.getChildren().add(lootBox);
        potionStackPane.setAlignment(Pos.CENTER);

        potionStackPane.setOnMouseClicked(event -> {
            if(!potionStackPane.isDisabled()) {
                onPotionClick();
            }
            potionStackPane.setOpacity(0.6);
            potionStackPane.setDisable(true);
        });

        return potionStackPane;
    }

    /**
     * Event-Handler für Klicks auf Karten im Loot.
     *
     * @param card  Die angeklickte Karte.
     * @param index Der Index der angeklickten Karte.
     */
    public void onCardClick(Card card, int index) {
        lootViewEvents.onCardClick(card, index);
    }

    /**
     * Event-Handler für Klicks auf das Gold im Loot.
     */
    private void onGoldClick(){
        lootViewEvents.onGoldClick(this.gold);
    }

    /**
     * Event-Handler für Klicks auf einen Trank im Loot.
     */
    private void onPotionClick(){
        lootViewEvents.onPotionClick(this.potionCard);
    }

    /**
     * Übergabe des LootViewEvents.
     *
     * @param lootViewEvents Die Ereignisse der Loot-Ansicht.
     */
    public void initTreasureViewEvents(LootViewEvents lootViewEvents) { this.lootViewEvents = lootViewEvents; }

    /**
     * Zeigt ein Popup-Fenster mit einer Nachricht an.
     *
     * @param text Die Nachricht im Popup-Fenster.
     */
    public void showDialog(String text) {
        Image popupImage = new Image(getClass().getResource("/images/popup/popupBg.png").toExternalForm());
        ImageView imageView = new ImageView(popupImage);
        imageView.setScaleX(0.5);
        imageView.setScaleY(0.5);

        StackPane stackPopup = new StackPane();
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 36; -fx-font-family: Kreon;");
        label.setTextFill(Paint.valueOf("White"));
        label.autosize();
        stackPopup.getChildren().addAll(imageView, label);

        this.popup = new Popup();
        this.popup.setAutoHide(true);
        this.popup.getContent().add(stackPopup);
        this.popup.show(this.lootLayout.getScene().getWindow(), 800, 500);
    }

    /**
     * Deaktiviert die Kartenauswahl im Loot.
     */
    public void disableCardSelection() {
        this.cardSelectionDisabled = true;
        this.cardSelectionButtonStackPane.setOpacity(0.6);
        this.cardSelectionButtonStackPane.setDisable(true);
    }
}
