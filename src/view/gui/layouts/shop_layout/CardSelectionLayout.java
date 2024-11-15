package view.gui.layouts.shop_layout;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import models.cards.card_structure.Card;
import models.player.player_structure.Player;
import view.gui.RestView;
import view.gui.ShopView;

import java.util.ArrayList;
import java.util.List;

public class CardSelectionLayout extends FlowPane {
    private List<Card> selectableCards;
    private Player player;
    private ShopView shopView;

    public CardSelectionLayout(List<Card> cardList, Player player, ShopView shopView) {
        this.selectableCards = cardList;
        this.player = player;
        this.shopView = shopView;

        // Center of the bottom
        setAlignment(Pos.CENTER);

        showCards();
    }


    private void showCards(){
        List<Node> nodes = new ArrayList<>();
        Card card;
        for(int i = 0; i < selectableCards.size(); i++){
            VBox box = new VBox();

            card = selectableCards.get(i);
            Label label = new Label();
            label.setText("Gold: " + card.getPrice());
            label.setStyle("-fx-size: 46;");
            label.setTextFill(Paint.valueOf("White"));
            box.getChildren().addAll(images(card), label);
            box.setAlignment(Pos.CENTER);
            nodes.add(box);
        }

        getChildren().addAll(nodes);
    }


    private Node images(Card card) {
        Image imageCard = new Image(getClass().getResource(card.getImagePath()).toExternalForm());
        ImageView imageViewCard = new ImageView(imageCard);

        imageViewCard.setFitWidth(350); // Breite in Pixel
        imageViewCard.setFitHeight(350); // Höhe in Pixel
        imageViewCard.setPreserveRatio(true);
        setHoverEffect(imageViewCard);

        // Klick-Event hinzufügen
        imageViewCard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            handleCardClick(card, selectableCards.indexOf(card)); // Hier eine Methode aufrufen, die das Klick-Event verarbeitet
        });
        return imageViewCard;
    }

    private void setHoverEffect(ImageView imageView) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.YELLOW);
        glow.setHeight(30);
        glow.setWidth(30);

        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            imageView.setEffect(glow);
            imageView.setScaleX(1.1); // Slightly increase the width
            imageView.setScaleY(1.1); // Slightly increase the height
        });

        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            imageView.setEffect(null);
            imageView.setScaleX(1.0); // Reset the width to original
            imageView.setScaleY(1.0); // Reset the height to original
        });
    }

    public void handleCardClick(Card card, int index) {
        // Verarbeite hier den Klick auf die Karte, z.B. öffne Details oder führe eine Aktion aus
        this.shopView.onCardClick(card, index);
    }
}
