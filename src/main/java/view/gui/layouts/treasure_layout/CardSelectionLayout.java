package view.gui.layouts.treasure_layout;

import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.card.card_structure.Card;
import view.gui.TreasureView;

import java.util.ArrayList;
import java.util.List;
/**
 * Die Klasse CardSelectionLayout verwaltet die grafische Darstellung der Kartenauswahl im Schatz-Layout.
 * Sie zeigt die verfügbaren Karten an und ermöglicht es dem Spieler, eine Karte auszuwählen.
 *
 * @author Vladislav Keil
 */
public class CardSelectionLayout extends HBox {
    private TreasureView treasureView;
    private List<Card> selectableCards;

    /**
     * Konstruktor für die Klasse CardSelectionLayout.
     * Initialisiert die Kartenauswahl mit einer Liste von Karten und der Schatz-Ansicht.
     *
     * @param cardList      Die Liste der auswählbaren Karten.
     * @param treasureView  Die Schatz-Ansicht, in der die Kartenauswahl angezeigt wird.
     */
    public CardSelectionLayout(List<Card> cardList, TreasureView treasureView) {
        this.selectableCards = cardList;
        this.treasureView = treasureView;

        // Center of the bottom
        setAlignment(Pos.CENTER);

        showCards();
    }

    /**
     * Zeigt die Karten in der Ansicht an.
     */
    private void showCards(){
        List<Node> nodes = new ArrayList<>();
        Card card;
        for(int i = 0; i < selectableCards.size(); i++){
            VBox box = new VBox();

            card = selectableCards.get(i);
            box.getChildren().addAll(images(card));
            box.setAlignment(Pos.CENTER);
            nodes.add(box);
        }

        getChildren().addAll(nodes);
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

        imageViewCard.setFitWidth(350); // Breite in Pixel
        imageViewCard.setFitHeight(350); // Höhe in Pixel
        imageViewCard.setPreserveRatio(true);
        GuiHelper.setHoverEffect(imageViewCard);

        // Klick-Event hinzufügen
        imageViewCard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            handleCardClick(card, this.selectableCards.indexOf(card));
            disableCardSelectionButton();
        });
        return imageViewCard;
    }

    /**
     * Deaktiviert den Kartenauswahl-Button.
     */
    private void disableCardSelectionButton() {
        treasureView.disableCardSelection();
        this.setVisible(false);
    }

    /**
     * Event-Handler für Klicks auf eine Karte.
     * Ruft das entsprechende Ereignis der Schatz-Ansicht auf.
     *
     * @param card  Die angeklickte Karte.
     * @param index Der Index der angeklickten Karte.
     */
    public void handleCardClick(Card card, int index) {
        this.treasureView.onCardClick(card, index);
    }
}
