package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.gui.BattleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse 'CardLayout' repräsentiert das Layout für die
 * Handkarten eines Spielers im Schlachtansicht (Battle View).
 * Sie stellt die Karten visuell dar und ermöglicht
 * Interaktionen wie das Klicken auf eine Karte.
 *
 * <p>
 * Jede Karte wird als Bild in einer horizontalen Anordnung angezeigt,
 * und es gibt Hover- und Click-Effekte, die die Interaktivität
 * der Karten erhöhen.
 * </p>
 *
 * @author Warawa Alexander, Willig Daniel
 */
public class CardLayout extends HBox {

    private final BattleView battleView;
    private final List<Card> hand;
    private final ObjectProperty<Card> selected = new SimpleObjectProperty<>();

    /**
     * Konstruktor für die Klasse 'CardLayout'.
     *
     * <p>
     * Dieser Konstruktor initialisiert das Layout mit der angegebenen
     * Handkartenliste und der Schlachtansicht. Die Karten werden
     * durch die Methode 'showCards()' angezeigt.
     * </p>
     *
     * @param hand       Die Liste der Handkarten des Spielers
     * @param battleView Die aktuelle Instanz der Schlachtansicht
     */
    public CardLayout(List<Card> hand, BattleView battleView) {
        this.battleView = battleView;
        this.hand = hand;
        // Cards move close to each other
        setSpacing(-30);
        // Center of the bottom
        setAlignment(Pos.CENTER);

        showCards();
    }

    /**
     * Verarbeitet das Klicken auf eine Karte.
     *
     * @param card  Die angeklickte Karte
     * @param index Der Index der Karte in der Hand
     */
    public void handleCardClick(Card card, int index) {
        selected.set(card);
        // Verarbeite hier den Klick auf die Karte, z.B. öffne Details oder führe eine Aktion aus
        battleView.clickedOnCard(card, index);
    }

    /**
     * Aktualisiert das Layout, indem es die aktuellen Handkarten
     * des Spielers erneut anzeigt.
     */
    public void refreshHand() {
        this.getChildren().clear();
        showCards();
        selected.set(null);
    }

    private Node images(Card card) {
        Image imageCard = new Image(getClass().getResource(card.getImagePath()).toExternalForm());
        ImageView imageViewCard = new ImageView(imageCard);

        imageViewCard.setFitWidth(250);
        imageViewCard.setFitHeight(250);
        imageViewCard.setPreserveRatio(true);
        imageViewCard.setTranslateY(60);

        setEventHandler(imageViewCard, card);
        return imageViewCard;
    }

    private void setEventHandler(ImageView imageView, Card card) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.YELLOW);
        glow.setHeight(30);
        glow.setWidth(30);
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handleCardClick(card, hand.indexOf(card)));
        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> setHoverEffect(imageView, glow));
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            if (selected.get() != card) {
                unsetHoverEffect(imageView);
            }
        });
        selected.addListener((observable, oldValue, newValue) -> {
            if (oldValue == card && newValue != card) {
                unsetHoverEffect(imageView);
            }
        });
    }

    private void unsetHoverEffect(ImageView imageView) {
        imageView.setEffect(null);
        imageView.setScaleX(1.0); // Reset the width to original
        imageView.setScaleY(1.0); // Reset the height to original
        imageView.setTranslateY(60); // Bild wieder nach unten verschieben
    }

    private void setHoverEffect(ImageView imageView, DropShadow glow) {
        imageView.setEffect(glow);
        imageView.setScaleX(1.1); // Slightly increase the width
        imageView.setScaleY(1.1); // Slightly increase the height
        imageView.setClip(null); // Clip entfernen, um das gesamte Bild anzuzeigen
        imageView.setTranslateY(0); // Bei Hover zurück zur ursprünglichen Y-Position
    }

    private void showCards() {
        List<Node> nodes = new ArrayList<>();
        for (Card card : hand) {
            nodes.add(images(card));
        }
        getChildren().addAll(nodes);
    }

}