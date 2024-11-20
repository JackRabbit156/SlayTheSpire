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

    private void initBottomLayout() {
        bottomLayout.setPickOnBounds(false);
        initBottom();
    }

    private void initShopLayout() {
        shopLayout.setPickOnBounds(false);
        initTop();
        initCenter();
    }

    public void initShopViewEvents(ShopViewEvents shopViewEvents){
        this.shopViewEvents = shopViewEvents;
    }

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
     * Bottom side
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
//        bottomHBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("Green"), null, null)));
        bottomHBox.setAlignment(Pos.TOP_LEFT);
        bottomHBox.setTranslateY(150);
        bottomHBox.getChildren().add(stackPaneBtn);
        bottomLayout.setBottom(bottomHBox);
    }

//    /**
//     * Left side
//     */
//    private void initLeft(){
//        VBox leftVBox = new VBox();
//        leftVBox.setAlignment(Pos.CENTER);
//        Region placeHolder = new Region();
//        placeHolder.setPrefWidth(200); // Festlegen der konstanten Höhe
//        leftVBox.getChildren().add(placeHolder);
//        shopLayout.setLeft(leftVBox);
//    }
//
//    /**
//     * Right side
//     */
//    private void initRight(){
//        VBox rightVBox = new VBox();
//        rightVBox.setAlignment(Pos.CENTER);
//        Region bottomPlaceholder = new Region();
//        bottomPlaceholder.setPrefWidth(200); // Festlegen der konstanten Höhe
//        rightVBox.getChildren().add(bottomPlaceholder);
//        shopLayout.setRight(rightVBox);
//    }

    /**
     * Aktion bei Klicken auf eine Karte.
     * @param card Card
     * @param index int
     */
    public void onCardClick(Card card, int index) {
        shopViewEvents.onCardClick(card, index);
    }

    public void onPotionClick(PotionCard card) {
        shopViewEvents.onPotionClick(card);
    }

    /**
     * Initialisiert die Center View mit den übergabenen Karten.
     * @param purchasableCards List of Cards
     */
    public void setShopCards(List<Card> purchasableCards) {
        this.shopCards = purchasableCards;
        initCenter();
    }

    /**
     * Es ploppt ein Popup Fenster auf und gibt eine Nachricht aus.
     * @param text Popup Text
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

    public void setPurchaseablePotion() {
        this.potionCard = null;
        initCenter();
    }
}