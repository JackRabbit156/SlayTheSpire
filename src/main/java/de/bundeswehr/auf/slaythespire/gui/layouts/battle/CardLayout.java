package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import de.bundeswehr.auf.slaythespire.gui.BattleView;
import de.bundeswehr.auf.slaythespire.gui.components.CardImageView;
import de.bundeswehr.auf.slaythespire.gui.components.EnemyImageView;
import de.bundeswehr.auf.slaythespire.gui.components.PlayerImageView;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

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

    private class DeselectHandler implements EventHandler<MouseEvent> {
        private final Scene scene;

        public DeselectHandler(Scene scene) {
            this.scene = scene;
        }

        @Override
        public void handle(MouseEvent event) {
            scene.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
            if (!isValidNextClick(event)) {
                deselectCard();
            }
        }

        private boolean isValidNextClick(MouseEvent event) {
            return event.getTarget() instanceof CardImageView ||
                    event.getTarget() instanceof EnemyImageView ||
                    event.getTarget() instanceof PlayerImageView;
        }

    }

    private final BattleView battleView;
    private final GameContext gameContext;
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
    public CardLayout(List<Card> hand, GameContext gameContext, BattleView battleView) {
        this.hand = hand;
        this.gameContext = gameContext;
        this.battleView = battleView;
        // Cards move close to each other
        setSpacing(-30);
        // Center of the bottom
        setAlignment(Pos.CENTER);

        showCards();
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

    private void deselectCard() {
        battleView.deselectCard();
    }

    /**
     * Verarbeitet das Klicken auf eine Karte.
     *
     * @param card  Die angeklickte Karte
     * @param index Der Index der Karte in der Hand
     */
    private void handleCardClick(Card card, int index) {
        selected.set(card);
        battleView.clickedOnCard(card, index);
    }

    private Node images(Card card) {
        ImageView imageView = new CardImageView(card.getImagePath());
        setEventHandler(imageView, card);
        return imageView;
    }

    private boolean isPlayable(Card card) {
        return gameContext.getPlayer().getCurrentEnergy() >= card.getCost() &&
                card.isPlayable(gameContext).isPlayable();
    }

    private void setEventHandler(ImageView imageView, Card card) {
        if (isPlayable(card)) {
            DropShadow glow = new DropShadow();
            glow.setColor(Color.DEEPSKYBLUE);
            glow.setHeight(50);
            glow.setWidth(50);
            imageView.setEffect(glow);
        }
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            handleCardClick(card, hand.indexOf(card));
            final Scene scene = imageView.getScene();
            scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new DeselectHandler(scene));
        });
        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> setHoverEffect(imageView));
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

    private void setHoverEffect(ImageView imageView) {
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

    private void unsetHoverEffect(ImageView imageView) {
        imageView.setScaleX(1.0); // Reset the width to original
        imageView.setScaleY(1.0); // Reset the height to original
        imageView.setTranslateY(60); // Bild wieder nach unten verschieben
    }

}