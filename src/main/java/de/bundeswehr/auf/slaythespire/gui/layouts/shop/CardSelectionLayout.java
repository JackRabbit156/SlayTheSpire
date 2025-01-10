package de.bundeswehr.auf.slaythespire.gui.layouts.shop;

import de.bundeswehr.auf.slaythespire.gui.events.CardEventListener;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse CardSelectionLayout verwaltet die grafische Darstellung der Kartenauswahl im Shop-Layout.
 * Sie zeigt die verfügbaren Karten im Shop an und ermöglicht es dem Spieler, eine Karte auszuwählen.
 *
 * @author Vladislav Keil
 */
public class CardSelectionLayout extends FlowPane {

    private final CardEventListener eventListener;

    /**
     * Konstruktor für die Klasse CardSelectionLayout.
     * Initialisiert die Kartenauswahl mit einer Liste von Karten und der Shop-Ansicht.
     *
     * @param cardList Die Liste der auswählbaren Karten.
     * @param eventListener Die Shop-Ansicht, in der die Kartenauswahl angezeigt wird.
     */
    public CardSelectionLayout(List<Card> cardList, CardEventListener eventListener) {
        this.eventListener = eventListener;
        setAlignment(Pos.CENTER);
        showCards(cardList);
    }

    /**
     * Event-Handler für Klicks auf eine Karte.
     * Ruft das entsprechende Ereignis der Shop-Ansicht auf.
     *
     * @param card  Die angeklickte Karte.
     */
    public void handleCardClick(Card card) {
        eventListener.onCardClick(card);
    }

    /**
     * Erzeugt die grafische Darstellung einer Karte.
     *
     * @param card Die darzustellende Karte.
     * @return Die grafische Darstellung der Karte als Node.
     */
    private Node images(Card card) {
        Image imageCard = new Image(getClass().getResource(card.getImagePath()).toExternalForm());
        ImageView imageViewCard = new ImageView(imageCard);

        imageViewCard.setFitHeight(350);
        imageViewCard.setPreserveRatio(true);
        GuiHelper.setHoverEffect(imageViewCard);

        imageViewCard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handleCardClick(card));
        return imageViewCard;
    }

    /**
     * Zeigt die Karten in der Ansicht an.
     */
    private void showCards(List<Card> cardList) {
        List<Node> nodes = new ArrayList<>();
        Card card;
        for (Card selectableCard : cardList) {
            VBox box = new VBox();
            HBox priceBox = new HBox();

            card = selectableCard;

            Image img = new Image(getClass().getResource("/images/gui/info/gold.png").toExternalForm());
            ImageView imgView = new ImageView(img);
            Label label = new Label();
            label.setText(String.valueOf(card.getPrice()));
            label.setStyle("-fx-font-size: 28px;" +
                    "-fx-font-family: Kreon;");
            label.setTextFill(Paint.valueOf("White"));
            priceBox.setAlignment(Pos.CENTER);
            priceBox.getChildren().addAll(imgView, label);

            box.getChildren().addAll(images(card), priceBox);
            box.setAlignment(Pos.CENTER);
            nodes.add(box);
        }

        getChildren().addAll(nodes);
    }

}
