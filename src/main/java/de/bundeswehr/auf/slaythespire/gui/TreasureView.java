package de.bundeswehr.auf.slaythespire.gui;

import de.bundeswehr.auf.slaythespire.gui.events.CardEventListener;
import de.bundeswehr.auf.slaythespire.gui.events.TreasureViewEvents;
import de.bundeswehr.auf.slaythespire.gui.layouts.CardSelectionLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.top_bar.TopBarLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.treasure.BackLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.treasure.EntryLayout;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

import java.util.List;

/**
 * Die Klasse TreasureView verwaltet die grafische Darstellung der Schatz-Ansicht im Spiel.
 * Sie zeigt die Beuteoptionen einschließlich Karten, Gold und Tränken an und ermöglicht es
 * dem Spieler, mit diesen zu interagieren.
 *
 * @author Vladislav Keil
 */
public class TreasureView extends StackPane implements WithTopBar, CardEventListener {

    private static final String FONT = "-fx-font-size: 38; -fx-font-family: Kreon;";
    private static final String FONT_SMALL = "-fx-font-size: 28; -fx-font-family: Kreon;";
    private static final double PANEL_SCALE = 0.7;

    private final BorderPane backLayout;
    private final List<Card> cardList;
    private StackPane cardSelectionButtonStackPane;
    private boolean cardSelectionDisabled;
    private final Popup cardSelectionPopup;
    private VBox center;
    private final BorderPane entryLayout;
    private final int gold;
    private final String playerImagePath;
    private final Potion potion;
    private TopBarLayout top;
    private final BorderPane treasureLayout;
    private final TreasureViewEvents treasureViewEvents;

    /**
     * Konstruktor für die Klasse TreasureView.
     * Initialisiert die Ansicht mit einer Trankkarte.
     *
     * @param cardList           Die Liste der verfügbaren Karten.
     * @param gold               Die Menge an Gold im Schatz.
     * @param potion         Die im Schatz enthaltene Trankkarte.
     * @param player             Der Spieler
     * @param treasureViewEvents Die Ereignisse der Schatz-Ansicht.
     */
    public TreasureView(List<Card> cardList, int gold, Potion potion, Player player, TreasureViewEvents treasureViewEvents) {
        playerImagePath = player.getImagePath();
        this.cardList = cardList;
        this.gold = gold;
        this.potion = potion;
        this.treasureViewEvents = treasureViewEvents;

        treasureLayout = new BorderPane();
        entryLayout = new BorderPane();
        backLayout = new BorderPane();
        cardSelectionPopup = new Popup();
        display(player);
    }

    public void onBackClick() {
        treasureViewEvents.onBackClicked();
    }

    /**
     * Event-Handler für Klicks auf Karten im Schatz.
     *
     * @param card Die angeklickte Karte.
     */
    @Override
    public void onCardClick(Card card) {
        treasureViewEvents.onCardClick(card);
        disableCardSelection();
    }

    @Override
    public void onFullScreen() {
        treasureViewEvents.onFullScreenClicked();
    }

    @Override
    public void onMap() {
        treasureViewEvents.onBackClicked();
    }

    /**
     * Event-Handler für Klicks auf die Schatzkiste. Öffnet das TreasureLayout und fügt den Zurück Button hinzu.
     */
    public void onTreasureClick() {
        initTreasureLayout();
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
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setWrapText(true);
        label.setMaxWidth(400);
        label.setStyle(FONT);
        label.setTextFill(Color.WHITE);
        label.autosize();
        stackPopup.getChildren().addAll(imageView, label);

        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.getContent().add(stackPopup);

        Bounds bounds = treasureLayout.localToScreen(treasureLayout.getBoundsInLocal());
        popup.show(treasureLayout.getScene().getWindow(), bounds.getMinX(), bounds.getMinY());
    }

    @Override
    public boolean showMap() {
        return true;
    }

    public void updateTop() {
        top.update();
    }

    /**
     * Deaktiviert die Kartenauswahl im Schatz.
     */
    private void disableCardSelection() {
        cardSelectionPopup.hide();
        cardSelectionDisabled = true;
        cardSelectionButtonStackPane.setOpacity(0.6);
        cardSelectionButtonStackPane.setDisable(true);
    }

    /**
     * Initialisiert die View und zeigt die Schatz-Ansicht an.
     *
     * @param player Der Spieler
     */
    private void display(Player player) {
        entryLayout.setPickOnBounds(false);
        backLayout.setPickOnBounds(false);
        treasureLayout.setPickOnBounds(false);

        getChildren().addAll(entryLayout, treasureLayout, backLayout);
        setBackground(new Background(GuiHelper.backgroundInHD("/images/act1.png")));
        initEntryLayout();
        initBackLayout(player);
    }

    /**
     * Erzeugt das StackPane für die Kartenauswahl.
     *
     * @return Das StackPane für die Kartenauswahl.
     */
    private StackPane getCardSelectionStackPane() {
        Image btnImage = new Image("/images/panel/rewardListItemPanel.png");
        Image img = new Image("/images/card/cardSymbol.png");
        ImageView imgView = new ImageView(img);
        ImageView itemPanelView = new ImageView(btnImage);

        imgView.setScaleX(0.25);
        imgView.setScaleY(0.25);

        // Label
        Label label = new Label("New Cards!");
        label.setTranslateX(-30);
        label.setStyle(FONT_SMALL);
        label.setTextFill(Color.WHITE);

        // Loot
        HBox lootBox = new HBox();
        lootBox.setAlignment(Pos.CENTER);
        lootBox.getChildren().addAll(imgView, label);

        // Panel
        StackPane cardSelectionStackPane = new StackPane(itemPanelView);
        cardSelectionStackPane.getChildren().add(lootBox);
        cardSelectionStackPane.setAlignment(Pos.CENTER);
        GuiHelper.setHoverEffect(cardSelectionStackPane);
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
        label.setStyle(FONT_SMALL);
        label.setTextFill(Color.WHITE);

        // Loot
        HBox lootBox = new HBox();
        lootBox.setAlignment(Pos.CENTER);
        lootBox.getChildren().addAll(imgView, label);

        // Panel
        StackPane goldStackPane = new StackPane(itemPanelView);
        goldStackPane.getChildren().add(lootBox);
        goldStackPane.setAlignment(Pos.CENTER);
        GuiHelper.setHoverEffect(goldStackPane);
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
        Image img = new Image(getClass().getResource(potion.getImagePath()).toExternalForm());
        ImageView imgView = new ImageView(img);

        imgView.setScaleX(PANEL_SCALE);
        imgView.setScaleY(PANEL_SCALE);

        // Label
        Label label = new Label();
        label.setText(potion.getName());
        label.setStyle(FONT_SMALL);
        label.setTextFill(Color.WHITE);

        // Loot
        HBox lootBox = new HBox();
        lootBox.setAlignment(Pos.CENTER);
        lootBox.getChildren().addAll(imgView, label);

        // Panel
        StackPane potionStackPane = new StackPane(itemPanelView);
        potionStackPane.getChildren().add(lootBox);
        potionStackPane.setAlignment(Pos.CENTER);
        GuiHelper.setHoverEffect(potionStackPane);
        potionStackPane.setOnMouseClicked(event -> {
            if (!potionStackPane.isDisabled()) {
                onPotionClick();
            }
            potionStackPane.setOpacity(0.6);
            potionStackPane.setDisable(true);
        });

        return potionStackPane;
    }

    private void initBackLayout(Player player) {
        top = new TopBarLayout(this, player);
        top.update();
        backLayout.setTop(top);

        BackLayout back = new BackLayout(this);
        HBox buttonZone = new HBox(back);
        buttonZone.setAlignment(Pos.TOP_LEFT);
        buttonZone.setPadding(new Insets(50, 50, 50, 50));
        backLayout.setBottom(buttonZone);
    }

    /**
     * Initialisiert das Layout beim Eintritt in das Encounter.
     */
    private void initEntryLayout() {
        EntryLayout entry = new EntryLayout(this, playerImagePath);
        entryLayout.setCenter(entry);

    }

    /**
     * Initialisiert das Layout des Schatzes.
     */
    private void initTreasureLayout() {
        treasureCenterLayer();
        treasureTitleLayout();
    }

    /**
     * Event-Handler für Klicks auf das Gold im Schatz.
     */
    private void onGoldClick() {
        treasureViewEvents.onGoldClick(gold);
    }

    /**
     * Event-Handler für Klicks auf einen Trank im Schatz.
     */
    private void onPotionClick() {
        treasureViewEvents.onPotionClick(potion);
    }

    /**
     * Setzt das Layout für die Kartenauswahl.
     */
    private void setCardSelectionLayout() {
        CardSelectionLayout cardSelectionLayout = new CardSelectionLayout(cardList, this);
        cardSelectionLayout.setPadding(new Insets(50, 50, 15, 50));
        cardSelectionButtonStackPane = getCardSelectionStackPane();

        HBox centerCard = new HBox();
        centerCard.setAlignment(Pos.CENTER);
        centerCard.setTranslateX(-180);
        centerCard.getChildren().add(cardSelectionLayout);

        cardSelectionPopup.setAutoHide(true);
        cardSelectionPopup.getContent().add(centerCard);

        cardSelectionButtonStackPane.setOnMouseClicked(event -> {
            if (!cardSelectionDisabled) {
                Bounds bounds = center.localToScreen(center.getBoundsInLocal());
                cardSelectionPopup.show(center.getScene().getWindow(), bounds.getMinX() - centerCard.getBoundsInLocal().getWidth() / 2, bounds.getMinY());
            }
        });
    }

    /**
     * Initialisiert das zentrale Layout der Schatz-Ansicht.
     */
    private void treasureCenterLayer() {
        center = new VBox();

        Image btnImage = new Image(getClass().getResource("/images/panel/rewardPanel.png").toExternalForm());
        ImageView rewardPanelImgView = new ImageView(btnImage);
        StackPane rewardStackPanel = new StackPane(rewardPanelImgView);
        rewardPanelImgView.setTranslateY(-140);

        center.setPadding(new Insets(15, 0, 0, 0));
        center.setAlignment(Pos.TOP_CENTER);
        center.setMaxWidth(100);

        // Gold Option
        StackPane goldStackPane = getGoldStackPane();
        center.getChildren().add(goldStackPane);

        // Potion Option
        if (potion != null) {
            StackPane getPotionStackPane = getPotionStackPane();
            center.getChildren().add(getPotionStackPane);
        }

        // Card Selection Option
        setCardSelectionLayout();

        center.getChildren().add(cardSelectionButtonStackPane);

        rewardStackPanel.getChildren().add(center);

        treasureLayout.setCenter(rewardStackPanel);
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
        label.setTextFill(Color.WHITE);
        label.setStyle(FONT);

        titlePane.getChildren().addAll(imageView, label);
        topVBox.getChildren().add(titlePane);
        topVBox.setAlignment(Pos.BOTTOM_CENTER);
        topVBox.setPrefHeight(200);
        treasureLayout.setTop(topVBox);
    }

}




