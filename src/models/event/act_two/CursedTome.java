package models.event.act_two;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import models.card.DeckFactory;
import models.card.card_structure.Card;
import models.card.card_structure.CardRarity;
import models.event.Event;
import models.player.player_structure.Player;

import java.util.List;
/**
 * Der Spieler verliert HP wenn er das Tome liest, bekommt aber eine seltene Karte wenn er dran bleibt
 *
 * @author  Loeschner, Marijan
 */
public class CursedTome extends Event {
    private DeckFactory df;
    private Image image = new Image("/images/event/act_two/cursedTome.jpg");
    private String title = "Cursed Tome";
    private String story = "\n\nIn an abandoned temple, you find a giant book, open, riddled with cryptic writings.\n" +
            "As you try to interpret the elaborate script, it begins shift and morph into writing you are familiar with.\n";
    private Button button1 = new Button("\t[Read] ");
    private Button button2 = new Button("\t[Continue] Lose HP");
    public CursedTome(){
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
            player.setCurrentHealth((player.getCurrentHealth()- 1));
            button1.setVisible(false);
            button2.setVisible(true);
        });
        return button1;
    }
    public Button getButton2(Player player) {
        button2.setVisible(false);
        df = new DeckFactory(player, 1);
        List<Card> deck;
        deck = df.init();
        button2.setOnMouseClicked(event1 -> {
            player.setCurrentHealth((player.getCurrentHealth()- 2));
            button2.setOnMouseClicked(event2 -> {
                Card selectedCard;
                player.setCurrentHealth((player.getCurrentHealth()- 3));
                for (Card card : deck) {
                    if (card.getCardRarity() == CardRarity.RARE) {
                        selectedCard = card;
                        player.addCardToDeck(selectedCard);
                    }
                }
                button2.setVisible(false);
            });
        });
        return button2;
    }
}
