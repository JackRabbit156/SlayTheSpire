package view.gui.layouts.battle_view_layouts;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import models.cards.card_structure.Card;
import view.gui.BattleView;

import java.util.ArrayList;
import java.util.List;

public class CardLayout extends HBox {
    private List<Card> hand;
    private BattleView battleView;

    public CardLayout(List<Card> hand, BattleView battleView){
        this.battleView = battleView;
        this.hand = hand;
        // Cards move close to each other
        setSpacing(-30);
        // Center of the bottom
        setAlignment(Pos.CENTER);

        showCards();
    }

    public void refreshHand() {
        this.getChildren().clear();
        showCards();
    }

    private void showCards(){
        List<Node> nodes = new ArrayList<>();

        for(int i = 0; i < hand.size(); i++){
            nodes.add(images(hand.get(i)));
        }

        getChildren().addAll(nodes);
    }


    private Node images(Card card) {
        Image imageCard = new Image(getClass().getResource(card.getImagePath()).toExternalForm());
        ImageView imageViewCard = new ImageView(imageCard);

        imageViewCard.setFitWidth(250); // Breite in Pixel
        imageViewCard.setFitHeight(250); // Höhe in Pixel
        imageViewCard.setPreserveRatio(true);

        imageViewCard.setTranslateY(60);

        setHoverEffect(imageViewCard);

        // Klick-Event hinzufügen
        imageViewCard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            handleCardClick(card, hand.indexOf(card)); // Hier eine Methode aufrufen, die das Klick-Event verarbeitet
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
            imageView.setClip(null); // Clip entfernen, um das gesamte Bild anzuzeigen
            imageView.setTranslateY(0); // Bei Hover zurück zur ursprünglichen Y-Position
        });

        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            imageView.setEffect(null);
            imageView.setScaleX(1.0); // Reset the width to original
            imageView.setScaleY(1.0); // Reset the height to original
            imageView.setTranslateY(60); // Bild wieder nach unten verschieben
        });
    }

    public void handleCardClick(Card card, int index) {
        // Verarbeite hier den Klick auf die Karte, z.B. öffne Details oder führe eine Aktion aus
        battleView.clickedOnCard(card, index);
    }

}