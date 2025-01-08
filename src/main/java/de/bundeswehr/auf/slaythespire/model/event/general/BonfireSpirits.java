package de.bundeswehr.auf.slaythespire.model.event.general;

import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;

/**
 * Spieler kann eine Karte je nach Rarität für eine gegenleistung eintauschen.
 *
 * @author Loeschner, marijan
 */
public class BonfireSpirits extends Event {


    public BonfireSpirits(Player player) {
        super(player, "Bonfire Spirits", new Image("/images/event/general/BonfireSpiritsEvent.png"),
            "\n\nYou happen to stumble upon a group of what looks like purple fire spirits dancing around a large bonfire.\n" +
                    "The spirits toss small bones and fragments into the fire, which brilliantly erupts each time. \n" +
                    "As you approach, the spirits all turn to you, expectantly...\n");
    }

    @Override
    public Button getButton1() {
        Button button1 = new Button("\t[Take] Give Card in exchange for Health."); // B: nothing, C: heal 5, U: heal 100%, R: heal 100% +10 maxHP
        button1.setOnAction(event -> {
            button1.setVisible(false);
            TilePane box = new TilePane();
            box.setBackground(Background.EMPTY);
            box.setMaxSize(1920, 1080);
            box.setAlignment(Pos.BOTTOM_CENTER);
            Popup cardPopup = new Popup();
            for (Card card : getPlayer().getDeck()) {
                Image imageCard = new Image(card.getImagePath());
                ImageView imageView = new ImageView(imageCard);
                imageView.setFitHeight(250);
                imageView.setFitWidth(200);
                imageView.setOnMouseClicked(e -> {
                    switch (card.getRarity()) {
                        case COMMON:
                            getPlayer().increaseCurrentHealth(5);
                            break;
                        case UNCOMMON:
                            getPlayer().setCurrentHealth(getPlayer().getMaxHealth());
                            break;
                        case RARE:
                            getPlayer().increaseMaxHealth(10);
                            getPlayer().setCurrentHealth(getPlayer().getMaxHealth());
                            break;
                    }
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
