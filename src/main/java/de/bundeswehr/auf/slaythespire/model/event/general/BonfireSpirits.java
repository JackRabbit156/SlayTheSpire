package de.bundeswehr.auf.slaythespire.model.event.general;

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
 * Spieler kann eine Karte je nach Rarität für eine gegenleistung eintauschen.
 * @author Loeschner, marijan
 */
public class BonfireSpirits extends Event {
    private Popup cardPopup = new Popup();
    private TilePane box = new TilePane();
    private Image image = new Image("/images/event/general/BonfireSpiritsEvent.png");
    private String title = "Bonfire Spirits";
    private String story = "\n\nYou happen to stumble upon a group of what looks like purple fire spirits dancing around a large bonfire.\n" +
            "The spirits toss small bones and fragments into the fire, which brilliantly erupts each time. \n" +
            "As you approach, the spirits all turn to you, expectantly...\n";
    private Button button1 = new Button("\t[Take] Give Card in exchange for Health.");

    public BonfireSpirits() {
        super();
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getStory() {
        return story;
    }

    public Image getImage() {
        return image;
    }

    public Button getButton1(Player player) {

        button1.setOnMouseClicked(event -> {
            //TODO: CardRarity common nichts, uncommon 100% heilung, rare 100%heilung und +10 maxHP
            button1.setVisible(false);
            box.setBackground(Background.EMPTY);
            box.setMaxSize(1920, 1080);
            box.setAlignment(Pos.BOTTOM_CENTER);
            for(Card card : player.getDeck()) {
                Image imageCard = new Image(getClass().getResource(card.getImagePath()).toExternalForm());
                ImageView imageView = new ImageView(imageCard);
                imageView.setFitHeight(250);
                imageView.setFitWidth(200);
                imageView.setOnMouseClicked(e -> {
                    switch(card.getCardRarity()){
                        case COMMON:
                            player.increaseCurrentHealth(5);
                        case UNCOMMON:
                            player.setCurrentHealth(player.getMaxHealth());
                        case RARE:
                            player.increaseMaxHealth(10);
                            player.setCurrentHealth(player.getMaxHealth());
                        default:
                            cardPopup.hide();
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
