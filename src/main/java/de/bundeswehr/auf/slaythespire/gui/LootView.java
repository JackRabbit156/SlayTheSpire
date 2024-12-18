package de.bundeswehr.auf.slaythespire.gui;

import de.bundeswehr.auf.slaythespire.gui.events.CardEventListener;
import de.bundeswehr.auf.slaythespire.gui.events.LootViewEvents;
import de.bundeswehr.auf.slaythespire.gui.layouts.CardSelectionLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.loot.PlayerLayout;
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
import javafx.stage.Popup;

import java.util.List;

/**
 * @author Keil, Vladislav
 */
public class LootView extends StackPane implements WithTopBar, CardEventListener {

    private static final String STYLE = "-fx-font-size: 38; -fx-font-family: Kreon;";
    private static final String STYLE_SMALL = "-fx-font-size: 28; -fx-font-family: Kreon;";
    private final BorderPane backLayout;
    private final BorderPane backgroundLayout;
    private StackPane cardSelectionButtonStackPane;
    private boolean cardSelectionDisabled;
    private final Popup cardSelectionPopup;
    private VBox center;
    private final int gold;
    private final List<Card> lootCards;
    private final BorderPane lootLayout;
    private final LootViewEvents lootViewEvents;
    private final Player player;
    private final Potion potion;
    private TopBarLayout top;

    /**
     * Konstruktor für die Klasse LootView.
     * Initialisiert die Ansicht mit einer Trankkarte.
     *
     * @param lootCards      Die Liste der verfügbaren Karten.
     * @param gold           Die Menge an Gold im Schatz.
     * @param player         Der Spieler
     * @param potion     Die im Schatz enthaltene Trankkarte.
     * @param lootViewEvents Die Ereignisse der Loot-Ansicht.
     */
    public LootView(List<Card> lootCards, int gold, Potion potion, Player player, LootViewEvents lootViewEvents) {
        this.lootCards = lootCards;
        this.gold = gold;
        this.potion = potion;
        this.player = player;
        this.lootViewEvents = lootViewEvents;

        lootLayout = new BorderPane();
        backLayout = new BorderPane();
        backgroundLayout = new BorderPane();
        cardSelectionPopup = new Popup();
        display();
    }

    /**
     * Initialisiert die View.
     */
    public void display() {
        getChildren().add(backgroundLayout);
        getChildren().add(lootLayout);
        getChildren().add(backLayout);
        initBackgroundLayout();
        initLootLayout();
        initBottomLayout();
    }

    /**
     * Event-Handler für Klicks auf Karten im Loot.
     *
     * @param card Die angeklickte Karte.
     */
    @Override
    public void onCardClick(Card card) {
        lootViewEvents.onCardClick(card);
        disableCardSelection();
    }

    @Override
    public void onFullScreen() {
        lootViewEvents.onFullScreenClick();
    }

    @Override
    public void onMap() {
        lootViewEvents.onBackClicked();
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
        label.setStyle(STYLE_SMALL);
        label.setTextFill(Color.WHITE);
        label.autosize();
        stackPopup.getChildren().addAll(imageView, label);

        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.getContent().add(stackPopup);
        Bounds bounds = lootLayout.localToScreen(lootLayout.getBoundsInLocal());
        popup.show(lootLayout.getScene().getWindow(), bounds.getMinX(), bounds.getMinY());
    }

    @Override
    public boolean showMap() {
        return true;
    }

    public void updateTop() {
        top.update();
    }

    /**
     * Deaktiviert die Kartenauswahl im Loot.
     */
    private void disableCardSelection() {
        cardSelectionPopup.hide();
        cardSelectionDisabled = true;
        cardSelectionButtonStackPane.setOpacity(0.6);
        cardSelectionButtonStackPane.setDisable(true);
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
        label.setStyle(STYLE_SMALL);
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
        // Label
        Label label = new Label(String.valueOf(gold));
        label.setText(String.valueOf(gold));
        label.setStyle(STYLE_SMALL);
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
        // Label
        Label label = new Label();
        label.setText(potion.getName());
        label.setStyle(STYLE_SMALL);
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

    /**
     * Initialisiert das untere Layout der Loot-Ansicht.
     */
    private void initBackLayout() {
        top = new TopBarLayout(this, player);
        top.update();
        backLayout.setTop(top);

        Image btnImage = new Image(getClass().getResource("/images/buttons/buttonL-small.png").toExternalForm());
        ImageView imgView = new ImageView(btnImage);
        HBox bottomHBox = new HBox();

        Label label = new Label("Back");
        label.setTextFill(Color.WHITE);
        label.setStyle(STYLE);

        bottomHBox.getChildren().add(GuiHelper.addButtonStackPane(imgView, label, 12, 8));

        GuiHelper.setHoverEffect(imgView);

        imgView.setOnMouseClicked(event -> lootViewEvents.onBackClicked());
        label.setOnMouseClicked(event -> lootViewEvents.onBackClicked());

        bottomHBox.setAlignment(Pos.TOP_LEFT);
        bottomHBox.setPadding(new Insets(50, 50, 50, 50));
        backLayout.setBottom(bottomHBox);
    }

    private void initBackgroundLayout() {
        backgroundLayout.setPickOnBounds(false);

        PlayerLayout playerLayout = new PlayerLayout(player.getImagePath());
        setBackground(new Background(GuiHelper.backgroundInHD("/images/act1.png")));
        backgroundLayout.setCenter(playerLayout);
    }

    /**
     * Initialisiert das untere Layout der Loot-Ansicht.
     */
    private void initBottomLayout() {
        backLayout.setPickOnBounds(false);
        initBackLayout();
    }

    /**
     * Initialisiert das zentrale Layout der Loot-Ansicht.
     */
    private void initCenter() {
        center = new VBox();

        Image img = new Image(getClass().getResource("/images/panel/rewardPanel.png").toExternalForm());
        ImageView imageView = new ImageView(img);
        StackPane rewardStackPanel = new StackPane(imageView);
        imageView.setTranslateY(-140);
        // Card Options
        center.setSpacing(10);
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

        lootLayout.setCenter(rewardStackPanel);
    }

    /**
     * Initialisiert das Layout der Loot-Ansicht.
     */
    private void initLootLayout() {
        lootLayout.setPickOnBounds(false);
        initCenter();
        initTop();
    }

    /**
     * Initialisiert das obere Layout der Loot-Ansicht.
     */
    private void initTop() {
        Image img = new Image(getClass().getResource("/images/banner/abandon.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        VBox topVBox = new VBox();
        StackPane titlePane = new StackPane();

        Label label = new Label();
        label.setText("Loot");
        label.setId("title");
        label.setTextFill(Color.WHITE);
        label.setStyle(STYLE);

        titlePane.getChildren().addAll(imageView, label);

        topVBox.getChildren().add(titlePane);
        topVBox.setAlignment(Pos.BOTTOM_CENTER);
        topVBox.setPrefHeight(200);
        lootLayout.setTop(topVBox);
    }

    /**
     * Event-Handler für Klicks auf das Gold im Loot.
     */
    private void onGoldClick() {
        lootViewEvents.onGoldClick(gold);
    }

    /**
     * Event-Handler für Klicks auf einen Trank im Loot.
     */
    private void onPotionClick() {
        lootViewEvents.onPotionClick(potion);
    }

    /**
     * Setzt das Layout für die Kartenauswahl.
     */
    private void setCardSelectionLayout() {
        CardSelectionLayout cardSelectionLayout = new CardSelectionLayout(lootCards, this);
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

}
