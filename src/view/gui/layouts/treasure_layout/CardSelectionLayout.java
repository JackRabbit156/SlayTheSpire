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

public class CardSelectionLayout extends HBox {
    private TreasureView treasureView;
    private List<Card> selectableCards;

    public CardSelectionLayout(List<Card> cardList, TreasureView treasureView) {
        this.selectableCards = cardList;
        this.treasureView = treasureView;

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
        treasureView.disableCardSelection();
        this.setVisible(false);
    }

    public void handleCardClick(Card card, int index) {
        this.treasureView.onCardClick(card, index);
    }
}
