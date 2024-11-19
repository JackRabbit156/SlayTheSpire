package view.gui.layouts.shop_layout;

import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import models.card.card_structure.Card;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;
import view.gui.ShopView;

public class PotionSelectionLayout extends FlowPane {
    private PotionCard potionCard;
    private ShopView shopView;

    public PotionSelectionLayout(PotionCard potionCard, ShopView shopView) {
        this.potionCard = potionCard;
        this.shopView = shopView;
        // Center of the bottom
        setAlignment(Pos.CENTER);
        if (potionCard != null)
            showPotion();
    }

    private void showPotion(){
        VBox box = new VBox();
        Label label = new Label();
        label.setText("Gold: " + this.potionCard.getPrice()); //TODO RARITÄT DER KARTEN!
        label.setStyle("-fx-size: 46;");
        label.setTextFill(Paint.valueOf("White"));
        box.getChildren().addAll(images(this.potionCard), label);
        box.setAlignment(Pos.CENTER);

        getChildren().addAll(box);
    }

    private Node images(Card potion) {
        Image imageCard = new Image(getClass().getResource(potion.getImagePath()).toExternalForm());
        ImageView imageViewCard = new ImageView(imageCard);

        imageViewCard.setFitWidth(100); // Breite in Pixel
        imageViewCard.setFitHeight(100); // Höhe in Pixel
        imageViewCard.setPreserveRatio(true);
        GuiHelper.setHoverEffect(imageViewCard);

        // Klick-Event hinzufügen
        imageViewCard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            // (PotionCard) Casting, da Image über Klasse "Card" hinterlegt ist.
            handlePotionClick((PotionCard) potion); // Hier eine Methode aufrufen, die das Klick-Event verarbeitet
        });

        return imageViewCard;
    }

    public void handlePotionClick(PotionCard potion) {
        // Verarbeite hier den Klick auf die Karte, z.B. öffne Details oder führe eine Aktion aus
        this.shopView.onPotionClick(potion);
    }
}
