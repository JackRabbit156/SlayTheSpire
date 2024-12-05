package models.event.generelevents;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import models.card.card_structure.Card;
import models.event.Event;
import models.player.player_structure.Player;


/**
 * Spieler kann entscheiden, welche Karte er duplizieren möchte.
 *
 * @author Loeschner, Marijan
 */
public class Duplicator extends Event {
    private TilePane box = new TilePane();
    private Popup cardPopup = new Popup();
    private Image image = new Image("/images/event/generalevents/DuplicatorEvent.png");
    private String title = "Duplicator";
    private String story = "\n\nBefore you lies a decorated altar to some ancient entity.\n";
    private Button button1 = new Button("\t[Pray] ");

    public Duplicator() {
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
                    player.addCardToDeck(card);
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

