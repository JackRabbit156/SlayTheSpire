package de.bundeswehr.auf.slaythespire.model.event.act_two;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;
/**
 * Der Spieler verliert HP wenn er das Tome liest, bekommt aber eine seltene Karte wenn er dran bleibt
 *
 * @author  Loeschner, Marijan
 */
public class CursedTome extends Event {

    private final Button button1 = new Button("\t[Read] "); // loose 1 HP
    private final Button button2 = new Button("\t[Continue] Lose HP"); // loose 2 HP, loose 3 HP and get a random rare card

    public CursedTome(Player player){
        super(player, "Cursed Tome", new Image("/images/event/act_two/cursedTome.jpg"),
            "\n\nIn an abandoned temple, you find a giant book, open, riddled with cryptic writings.\n" +
                    "As you try to interpret the elaborate script, it begins shift and morph into writing you are familiar with.\n");
    }

    @Override
    public Button getButton1() {
        button1.setOnAction(event -> {
            getPlayer().setCurrentHealth((getPlayer().getCurrentHealth()- 1));
            button1.setVisible(false);
            button2.setVisible(true);
        });
        return button1;
    }

    @Override
    public Button getButton2() {
        button2.setVisible(false);
        DeckFactory df = new DeckFactory(getPlayer(), 1);
        button2.setOnAction(event -> {
            getPlayer().setCurrentHealth((getPlayer().getCurrentHealth()- 2));
            button2.setOnAction(e -> {
                Card selectedCard;
                getPlayer().setCurrentHealth((getPlayer().getCurrentHealth()- 3));
                for (Card card : df.init()) {
                    if (card.getCardRarity() == CardRarity.RARE) {
                        selectedCard = card;
                        getPlayer().addCardToDeck(selectedCard);
                    }
                }
                button2.setVisible(false);
            });
        });
        return button2;
    }

}
