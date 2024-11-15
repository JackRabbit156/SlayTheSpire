package view.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Popup;
import models.cards.card_structure.Card;
import models.player.player_structure.Player;
import view.gui.layouts.layout_events.ShopViewEvents;
import view.gui.layouts.shop_layout.SelectLayout;

import java.util.List;

public class ShopView extends BorderPane {
    private List<Card> shopCards;
    private Player player;
    private ShopViewEvents shopViewEvents;

    private Insets insets = new Insets(15,15,15,15);
    private VBox centerVBox;
    private VBox topVBox;
    private Popup popup;


    public ShopView(Player player, List<Card> shopCards, ShopViewEvents shopViewEvents) {
        this.player = player;
        this.shopCards = shopCards;
        this.shopViewEvents = shopViewEvents;
        display();
    }

    public void initShopViewEvents(ShopViewEvents shopViewEvents){
        this.shopViewEvents = shopViewEvents;
    }

    private BackgroundImage background() {
        Image backgroundImage = new Image(getClass().getResource("/images/ShopViewBG.png").toExternalForm());
        return new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, // Option: NO_REPEAT, REPEAT, REPEAT_X, REPEAT_Y
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
        );
    }

    private void initCenter(){
        // Card Options
        SelectLayout selectLayout = new SelectLayout(this.shopCards, this.player, this);
        // Potion Options
        // Relics Options

        centerVBox = new VBox();
        if (shopCards.isEmpty()) {
            Region placeHolder = new Region();
            placeHolder.setPrefWidth(400);
            setCenter(placeHolder);
            return;
        }
//        centerVBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("Red"),null, this.insets)));
        centerVBox.setSpacing(30);
        centerVBox.setPadding(insets);
        centerVBox.setAlignment(Pos.TOP_CENTER);
        centerVBox.getChildren().add(selectLayout);

        setCenter(centerVBox);
    }

    private void initTop(){
        topVBox = new VBox();
        Label label = new Label();
        label.setText("Welcome to Shop.");
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
        HBox bottomHBox = new HBox();
        Button backBtn = new Button("Back");
        backBtn.setOnAction(event -> shopViewEvents.onBackClick());
        bottomHBox.getChildren().add(backBtn);
//        bottomHBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("Blue"),null, this.insets)));
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
//        leftVBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("Yellow"),null, this.insets)));
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
//        rightVBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("Green"),null, this.insets)));
        rightVBox.setAlignment(Pos.CENTER);
        Region bottomPlaceholder = new Region();
        bottomPlaceholder.setPrefWidth(200); // Festlegen der konstanten Höhe
        rightVBox.getChildren().add(bottomPlaceholder);
        setRight(rightVBox);
    }


    /**
     * Initialisiert die View.
     */
    public void display() {
        setBackground(new Background(background()));
        initTop();
        initBottom();
        initLeft();
        initRight();
        initCenter();
    }

    public void onCardClick(Card card, int index) {
        shopViewEvents.onCardClick(card,index);
    }

    public void setShopCards(List<Card> purchasableCards) {
        this.shopCards = purchasableCards;
        initCenter();
    }

    public void showDialog(String text) {
        popup = new Popup();
        HBox box = new HBox();
        Label label = new Label(text);
        label.setTextFill(Paint.valueOf("White"));
        label.autosize();
        box.setPrefHeight(150);
        box.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"), new BorderStrokeStyle(StrokeType.CENTERED, StrokeLineJoin.ROUND, null, 0,0, null),null,null)));
        box.setPrefWidth(250);
        box.setOpacity(0.95);
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(label);
        box.setBackground(new Background(new BackgroundFill(Paint.valueOf("Grey"), null, null)));

        popup.setAutoHide(true);
        popup.getContent().add(box);
        popup.show(centerVBox.getScene().getWindow(), 800, 500);
    }
}