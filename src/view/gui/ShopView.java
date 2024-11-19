package view.gui;

import helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Popup;
import models.card.card_structure.Card;
import models.player.player_structure.Player;
import view.gui.layouts.layout_events.ShopViewEvents;
import view.gui.layouts.shop_layout.CardSelectionLayout;

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

    /**
     * Initialisiert die View.
     */
    public void display() {
        setBackground(new Background(GuiHelper.background("/images/backgrounds/ShopViewBG.jpeg")));
        initTop();
        initBottom();
        initLeft();
        initRight();
        initCenter();
    }

    public void initShopViewEvents(ShopViewEvents shopViewEvents){
        this.shopViewEvents = shopViewEvents;
    }

    private void initCenter(){
        // Card Options
        CardSelectionLayout cardSelectionLayout = new CardSelectionLayout(this.shopCards, this.player, this);
        // Potion Options
        // Relics Options

        centerVBox = new VBox();
        if (shopCards.isEmpty()) {
            Region placeHolder = new Region();
            placeHolder.setPrefWidth(400);
            setCenter(placeHolder);
            return;
        }
        centerVBox.setSpacing(30);
        centerVBox.setPadding(insets);
        centerVBox.setAlignment(Pos.TOP_CENTER);
        centerVBox.getChildren().add(cardSelectionLayout);

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
        Image btnImage = new Image(getClass().getResource("/images/buttons/buttonL.png").toExternalForm());
        ImageView backImgView = new ImageView(btnImage);
        HBox bottomHBox = new HBox();
        Button backBtn = new Button();
        backBtn.setGraphic(backImgView);

        backImgView.setFitWidth(backBtn.getWidth());
        backImgView.setFitHeight(backBtn.getHeight());
        backImgView.setScaleX(0.7);
        backImgView.setScaleY(0.7);

        StackPane backBtnPane = new StackPane(backImgView);
        Label label = new Label("Back");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24;");
        backBtnPane.getChildren().add(label);
        bottomHBox.getChildren().add(backBtnPane);

        backImgView.setOnMouseClicked(event -> shopViewEvents.onBackClicked());
        label.setOnMouseClicked(event -> shopViewEvents.onBackClicked());
        GuiHelper.setButtonHoverEffect(backImgView, label);

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
        shopViewEvents.onCardClick(card,index);
    }

    public void setShopCards(List<Card> purchasableCards) {
        this.shopCards = purchasableCards;
        initCenter();
    }

    public void showDialog(String text) {
        Image popupImage = new Image(getClass().getResource("/images/popup/popupBg.png").toExternalForm());
        ImageView imageView = new ImageView(popupImage);
        imageView.setScaleX(0.5);
        imageView.setScaleY(0.5);

        StackPane stackPopup = new StackPane();
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 36;");
        label.setTextFill(Paint.valueOf("White"));
        label.autosize();
        stackPopup.getChildren().addAll(imageView, label);

        this.popup = new Popup();
        this.popup.setAutoHide(true);
        this.popup.getContent().add(stackPopup);
        this.popup.show(this.centerVBox.getScene().getWindow(), 800, 500);
    }
}