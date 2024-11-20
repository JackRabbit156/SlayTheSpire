package view.gui.layouts.loot_layout;

import helper.GuiHelper;
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
import models.card.card_structure.Card;
import models.player.player_structure.Player;
import view.gui.LootView;
import view.gui.ShopView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CardSelectionLayout extends HBox {
    private LootView lootView;
    private List<Card> selectableCards;

    public CardSelectionLayout(List<Card> cardList, LootView lootView) {
        this.selectableCards = cardList;
        this.lootView = lootView;

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
            box.getChildren().addAll(images(card));
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
        GuiHelper.setHoverEffect(imageViewCard);

        // Klick-Event hinzufügen
        imageViewCard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            handleCardClick(card, this.selectableCards.indexOf(card));
            disableCardSelectionButton();
        });
        return imageViewCard;
    }

    private void disableCardSelectionButton() {
        lootView.disableCardSelection();
        this.setVisible(false);
    }

    public void handleCardClick(Card card, int index) {
        this.lootView.onCardClick(card, index);
    }
}
