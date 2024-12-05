package de.bundeswehr.auf.slaythespire.model.event.act_two;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;
/**
 * Der Spieler kann eine Karte hinzufügen, oder HP erhalten
 *
 * @author  Loeschner, Marijan
 */
public class TheLibrary extends Event {
    private TilePane box = new TilePane();
    private Popup cardPopup = new Popup();
    private DeckFactory df;
    private Image image = new Image("/images/event/act_two/library.jpg");
    private String title = "The Library";
    private String story = "\n\nYou come across an ornate building which appears abandoned.\n" +
            "A plaque that has been torn free from a wall is on the floor. It reads, \"THE LIBRARY\".\n" +
            "Inside, you find countless rows of scrolls, manuscripts, and books.\n" +
            "You pick one and cozy yourself into a chair for some quiet time.\n";
    private Button button1 = new Button("\t[Read] Choose one of 20 Cards.");
    private Button button2 = new Button("\t[Sleep] Heal 1/3 of your max HP.");

    public TheLibrary() {
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
            df = new DeckFactory(player, 20);
            List<Card> deck;
            deck = df.init();
            button1.setVisible(false);
            button2.setVisible(false);
            box.setBackground(Background.EMPTY);
            box.setMaxSize(1920, 1080);
            box.setAlignment(Pos.BOTTOM_CENTER);
            for(Card card : deck) {
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

    public Button getButton2(Player player) {

        button2.setOnMouseClicked(event -> {
            player.increaseCurrentHealth(player.getMaxHealth() / 3);
            button2.setVisible(false);
            button1.setVisible(false);

        });
        return button2;
    }
}
