package de.bundeswehr.auf.slaythespire.model.event.general;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;


/**
 * Spieler kann entscheiden, welche Karte er duplizieren möchte.
 *
 * @author Loeschner, Marijan
 */
public class Duplicator extends Event {

    public Duplicator(Player player) {
        super(player, "Duplicator",
            "\n\nBefore you lies a decorated altar to some ancient entity.\n");
        setImage(new PathAssistent().toPath(this));
    }

    @Override
    public Button getButton1() {
        Button button1 = new Button("\t[Pray] "); // duplicate 1 card from your deck
        button1.setOnAction(event -> {
            button1.setVisible(false);
            TilePane box = new TilePane();
            box.setBackground(Background.EMPTY);
            box.setMaxSize(1920, 1080);
            box.setAlignment(Pos.BOTTOM_CENTER);
            Popup cardPopup = new Popup();
            for(Card card : getPlayer().getDeck()) {
                Image imageCard = new Image(card.getImagePath());
                ImageView imageView = new ImageView(imageCard);
                imageView.setFitHeight(250);
                imageView.setFitWidth(200);
                imageView.setOnMouseClicked(e -> {
                    getPlayer().addCardToDeck(DeckFactory.copy(card));
                    cardPopup.hide();
                });
                box.getChildren().addAll(imageView);
            }
            cardPopup.getContent().addAll(box);
            cardPopup.setAutoFix(true);
            cardPopup.show(button1.getScene().getWindow());
        });
        return button1;
    }

}

