package de.bundeswehr.auf.slaythespire.gui.layouts;

import de.bundeswehr.auf.slaythespire.gui.events.CardEvent;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;

import java.util.List;
/**
 * Die Klasse CardSelectionLayout verwaltet die grafische Darstellung der Kartenauswahl im Schatz-Layout.
 * Sie zeigt die verfügbaren Karten an und ermöglicht es dem Spieler, eine Karte auszuwählen.
 *
 * @author Vladislav Keil
 */
public class CardSelectionLayout extends HBox {

    private final CardEvent[] eventListeners;

    /**
     * Konstruktor für die Klasse CardSelectionLayout.
     * Initialisiert die Kartenauswahl mit einer Liste von Karten und der Schatz-Ansicht.
     *
     * @param cards      Die Liste der auswählbaren Karten.
     * @param eventListeners  Die Ansichten, die von der Kartenauswahl beeinflusst werden.
     */
    public CardSelectionLayout(List<Card> cards, CardEvent... eventListeners) {
        this.eventListeners = eventListeners;
        setAlignment(Pos.CENTER);
        showCards(cards);
    }

    /**
     * Zeigt die Karten in der Ansicht an.
     */
    private void showCards(List<Card> cards){
        for (Card selectableCard : cards) {
            VBox box = new VBox();
            box.getChildren().addAll(images(selectableCard));
            box.setAlignment(Pos.CENTER);
            getChildren().add(box);
        }
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

        imageViewCard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            handleCardClick(card);
            disableCardSelectionButton();
        });
        return imageViewCard;
    }

    /**
     * Deaktiviert den Kartenauswahl-Button.
     */
    private void disableCardSelectionButton() {
        for (CardEvent eventListener : eventListeners) {
            eventListener.disableCardSelection();
        }
        setVisible(false);
    }

    /**
     * Event-Handler für Klicks auf eine Karte.
     * Ruft das entsprechende Ereignis der Schatz-Ansicht auf.
     *
     * @param card  Die angeklickte Karte.
     */
    public void handleCardClick(Card card) {
        for (CardEvent eventListener : eventListeners) {
            eventListener.onCardClick(card);
        }
    }

}
