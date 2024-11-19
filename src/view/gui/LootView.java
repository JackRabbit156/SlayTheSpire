package view.gui;

import controller.gui.LootController;
import helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import models.card.card_structure.Card;
import models.player.player_structure.Player;
import view.gui.layouts.layout_events.LootViewEvents;
import view.gui.layouts.loot_layout.CardSelectionLayout;

import java.util.List;

public class LootView extends BorderPane {
    private List<Card> lootCards;
    private Player player;
    private int gold;
    private LootViewEvents lootViewEvents;

    private Insets insets = new Insets(15,15,15,15);
    private VBox centerVBox;
    private VBox topVBox;


    public LootView(Player player, List<Card> lootCards,int gold, LootViewEvents lootViewEvents) {
        this.player = player;
        this.lootCards = lootCards;
        this.gold = gold;
        this.lootViewEvents = lootViewEvents;
        display();
    }

    /**
     * Initialisiert die View.
     */
    public void display() {
        setBackground(new Background(GuiHelper.background("/images/backgrounds/LootViewBG.jpeg")));
        initTop();
        initBottom();
        initLeft();
        initRight();
        initCenter();
    }

    private void initCenter(){
        // Card Options
        CardSelectionLayout cardSelectionLayout = new CardSelectionLayout(this.lootCards, this.player, this);

        centerVBox = new VBox();
        centerVBox.setSpacing(30);
        centerVBox.setPadding(insets);
        centerVBox.setAlignment(Pos.TOP_CENTER);
        centerVBox.getChildren().add(cardSelectionLayout);

        Image btnImage = new Image(getClass().getResource("/images/buttons/blankButton.png").toExternalForm());
        ImageView itemPanelView = new ImageView(btnImage);
        itemPanelView.setScaleY(0.25);
        itemPanelView.setScaleX(0.25);

        StackPane itemStackPanel = new StackPane(itemPanelView);
        Label label = new Label("Gold: " + gold);
        label.setTextFill(Paint.valueOf("Gold"));
        label.setStyle("-fx-font-size: 24;");
        itemStackPanel.getChildren().add(label);
        centerVBox.getChildren().add(itemStackPanel);


        setCenter(centerVBox);
    }

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
        setTop(topVBox);
    }


    /**
     * Bottom side
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

        bottomHBox.setAlignment(Pos.CENTER);
        Region placeHolder = new Region();
        placeHolder.setPrefHeight(150); // Festlegen der konstanten Höhe
        bottomHBox.getChildren().add(placeHolder);
        setBottom(bottomHBox);
    }

    /**
     * Left side
     */
    private void initLeft(){
        VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.CENTER);
        Region placeHolder = new Region();
        placeHolder.setPrefWidth(200); // Festlegen der konstanten Höhe
        leftVBox.getChildren().add(placeHolder);
        setLeft(leftVBox);
    }

    /**
     * Right side
     */
    private void initRight(){
        VBox rightVBox = new VBox();
        rightVBox.setAlignment(Pos.CENTER);
        Region bottomPlaceholder = new Region();
        bottomPlaceholder.setPrefWidth(200); // Festlegen der konstanten Höhe
        rightVBox.getChildren().add(bottomPlaceholder);
        setRight(rightVBox);
    }

    public void onCardClick(Card card, int index) {
        lootViewEvents.onCardClick(card, index);
    }

    public void setLootCards(List<Card> selectableCards) {
        this.lootCards = selectableCards;
        initCenter();
    }

    public void initLootViewEvents(LootViewEvents lootViewEvents) { this.lootViewEvents = lootViewEvents; }
}
