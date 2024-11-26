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
import view.gui.layouts.layout_events.TreasureViewEvents;
import view.gui.layouts.treasure_layout.BackLayout;
import view.gui.layouts.treasure_layout.CardSelectionLayout;
import view.gui.layouts.treasure_layout.EntryLayout;

import java.util.List;

/**
 * Die Klasse TreasureView verwaltet die grafische Darstellung der Schatz-Ansicht im Spiel.
 * Sie zeigt die Beuteoptionen einschließlich Karten, Gold und Tränken an und ermöglicht es
 * dem Spieler, mit diesen zu interagieren.
 *
 * @autor Vladislav Keil
 */
public class TreasureView extends StackPane {
    private PotionCard potionCard;
    private List<Card> cardList;
    private int gold;
    private TreasureViewEvents treasureViewEvents;
    private String playerImagePath;

    private String STYLE = "-fx-font-size: 38; -fx-font-family: Kreon;";
    private String STYLE_SMALL = "-fx-font-size: 28; -fx-font-family: Kreon;";

    private Popup popup;
    private VBox centerVBox;
    private Popup cardSelectionPopup;
    private double PANEL_SCALE = 0.7;

    private BorderPane entryLayout;
    private BorderPane backLayout;
    private BorderPane treasureLayout;
    private boolean cardSelectionDisabled;
    private StackPane cardSelectionButtonStackPane;

    /**
     * Konstruktor für die Klasse TreasureView.
     * Initialisiert die Ansicht ohne eine Trankkarte.
     *
     * @param cardList             Die Liste der verfügbaren Karten.
     * @param gold                 Die Menge an Gold im Schatz.
     * @param treasureViewEvents   Die Ereignisse der Schatz-Ansicht.
     */
    public TreasureView(List<Card> cardList, int gold, String playerImagePath, TreasureViewEvents treasureViewEvents) {
        this.treasureLayout = new BorderPane();
        this.entryLayout = new BorderPane();
        this.backLayout = new BorderPane();

        this.cardList = cardList;
        this.gold = gold;
        this.playerImagePath = playerImagePath;
        this.treasureViewEvents = treasureViewEvents;
    }

    /**
     * Konstruktor für die Klasse TreasureView.
     * Initialisiert die Ansicht mit einer Trankkarte.
     *
     * @param cardList             Die Liste der verfügbaren Karten.
     * @param gold                 Die Menge an Gold im Schatz.
     * @param potionCard           Die im Schatz enthaltene Trankkarte.
     * @param treasureViewEvents   Die Ereignisse der Schatz-Ansicht.
     */
    public TreasureView(List<Card> cardList, int gold, PotionCard potionCard, String playerImagePath, TreasureViewEvents treasureViewEvents) {
        this(cardList, gold, playerImagePath, treasureViewEvents);
        this.potionCard = potionCard;
        this.cardSelectionPopup = new Popup();
        display();
    }

    /**
     * Initialisiert die View und zeigt die Schatz-Ansicht an.
     */
    public void display() {
        this.getChildren().addAll(this.entryLayout, this.treasureLayout, this.backLayout);

        this.entryLayout.setPickOnBounds(false);
        this.backLayout.setPickOnBounds(false);
        this.treasureLayout.setPickOnBounds(false);

        this.setBackground(new Background(GuiHelper.background("/images/act1.png")));
        initEntryLayout();
//        initTreasureLayout();
//        setBackground(new Background(GuiHelper.background("/images/backgrounds/TreasureViewBG.jpeg")));
    }

    private void initBackLayout() {
        BackLayout back = new BackLayout(this);
        HBox buttonZone = new HBox(back);
        buttonZone.setAlignment(Pos.TOP_LEFT);
        buttonZone.setPadding(new Insets(50,50,50,50));
        this.backLayout.setBottom(buttonZone);
    }

    /**
     * Initialisiert das Layout beim Eintritt in das Encounter.
     */
    private void initEntryLayout() {
        EntryLayout entry = new EntryLayout(this, playerImagePath);
        this.entryLayout.setCenter(entry);

    }

    /**
     * Initialisiert das Layout des Schatzes.
     */
    private void initTreasureLayout() {
        treasureCenterLayer();
        treasureTitleLayout();
    }

    /**
     * Initialisiert das zentrale Layout der Schatz-Ansicht.
     */
    private void treasureCenterLayer() {
        this.centerVBox = new VBox();

        Image btnImage = new Image(getClass().getResource("/images/panel/rewardPanel.png").toExternalForm());
        ImageView rewardPanelImgView = new ImageView(btnImage);
        StackPane rewardStackPanel = new StackPane(rewardPanelImgView);
        rewardPanelImgView.setTranslateY(-140);

        centerVBox.setPadding(new Insets(15,0,0,0));
        this.centerVBox.setAlignment(Pos.TOP_CENTER);
        centerVBox.setMaxWidth(100);

        // Gold Option
        StackPane goldStackPane = getGoldStackPane();
        this.centerVBox.getChildren().add(goldStackPane);

        // Potion Option
        if (this.potionCard != null) {
            StackPane getPotionStackPane = getPotionStackPane();
            this.centerVBox.getChildren().add(getPotionStackPane);
        }

        // Card Selection Option
        setCardSelectionLayout();

        this.centerVBox.getChildren().add(this.cardSelectionButtonStackPane);

        rewardStackPanel.getChildren().add(centerVBox);

        this.treasureLayout.setCenter(rewardStackPanel);
    }

    /**
     * Setzt das Layout für die Kartenauswahl.
     */
    private void setCardSelectionLayout() {
        CardSelectionLayout cardSelectionLayout = new CardSelectionLayout(this.cardList, this);
        cardSelectionLayout.setPadding(new Insets(50, 50, 15, 50));
        this.cardSelectionButtonStackPane = getCardSelectionStackPane();

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

        imgView.setScaleX(0.25);
        imgView.setScaleY(0.25);

        // Label
        Label label = new Label("New Cards!");
        label.setTranslateX(-30);
        label.setStyle(STYLE_SMALL);
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

        imgView.setScaleX(PANEL_SCALE);
        imgView.setScaleY(PANEL_SCALE);

        // Label
        Label label = new Label(String.valueOf(gold));
        label.setText(String.valueOf(gold));
        label.setStyle(STYLE_SMALL);
        label.setTextFill(Paint.valueOf("White"));

        // Loot
        HBox lootBox = new HBox();
        lootBox.setAlignment(Pos.CENTER);
        lootBox.getChildren().addAll(imgView, label);

        // Panel
        StackPane goldStackPane = new StackPane(itemPanelView);
        goldStackPane.getChildren().add(lootBox);
        goldStackPane.setAlignment(Pos.CENTER);

        goldStackPane.setOnMouseClicked(event -> {
            if (!goldStackPane.isDisabled()) {
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

        imgView.setScaleX(PANEL_SCALE);
        imgView.setScaleY(PANEL_SCALE);

        // Label
        Label label = new Label();
        label.setText(potionCard.getName());
        label.setStyle(STYLE_SMALL);
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
            if (!potionStackPane.isDisabled()) {
                onPotionClick();
            }
            potionStackPane.setOpacity(0.6);
            potionStackPane.setDisable(true);
        });

        return potionStackPane;
    }

    /**
     * Initialisiert das obere Layout der Schatz-Ansicht.
     */
    private void treasureTitleLayout() {
        Image img = new Image(getClass().getResource("/images/banner/abandon.png").toExternalForm());
        ImageView imageView = new ImageView(img);
        StackPane titlePane = new StackPane();

        VBox topVBox = new VBox();
        Label label = new Label();
        label.setText("Loot");
        label.setId("title");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle(STYLE);

        titlePane.getChildren().addAll(imageView,label);
        topVBox.getChildren().add(titlePane);
        topVBox.setAlignment(Pos.BOTTOM_CENTER);
        topVBox.setPrefHeight(200);
        this.treasureLayout.setTop(topVBox);
    }

    public void onBackClick() {
        this.treasureViewEvents.onBackClicked();
    }

    /**
     * Event-Handler für Klicks auf Karten im Schatz.
     *
     * @param card  Die angeklickte Karte.
     * @param index Der Index der angeklickten Karte.
     */
    public void onCardClick(Card card, int index) {
        treasureViewEvents.onCardClick(card, index);
    }

    /**
     * Event-Handler für Klicks auf das Gold im Schatz.
     */
    private void onGoldClick() {
        treasureViewEvents.onGoldClick(this.gold);
    }

    /**
     * Event-Handler für Klicks auf einen Trank im Schatz.
     */
    private void onPotionClick() {
        treasureViewEvents.onPotionClick(this.potionCard);
    }

    /**
     * Initialisiert die Ereignisse der Schatz-Ansicht.
     *
     * @param treasureViewEvents Die Ereignisse der Schatz-Ansicht.
     */
    public void initTreasureViewEvents(TreasureViewEvents treasureViewEvents) {
        this.treasureViewEvents = treasureViewEvents;
    }

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
        label.setStyle(STYLE);
        label.setTextFill(Paint.valueOf("White"));
        label.autosize();
        stackPopup.getChildren().addAll(imageView, label);

        this.popup = new Popup();
        this.popup.setAutoHide(true);
        this.popup.getContent().add(stackPopup);
        this.popup.show(this.treasureLayout.getScene().getWindow(), 800, 500);
    }

    /**
     * Deaktiviert die Kartenauswahl im Schatz.
     */
    public void disableCardSelection() {
        this.cardSelectionDisabled = true;
        this.cardSelectionButtonStackPane.setOpacity(0.6);
        this.cardSelectionButtonStackPane.setDisable(true);
    }

    /**
     * Event-Handler für Klicks auf die Schatzkiste. Öffnet das TreasureLayout und fügt den Zurück Button hinzu.
     */
    public void onTreasureClick() {
        initTreasureLayout();
        initBackLayout();
    }
}




