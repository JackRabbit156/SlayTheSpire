package de.bundeswehr.auf.slaythespire.model.event.act_two;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.potion.PotionFactory;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;

/**
 * Der Spieler verliert Leben, kann aber Gold, oder Karten erhalten
 *
 * @author  Loeschner, Marijan
 */
public class KnowingSkull extends Event {

    private final Button button1 = new Button("\t[Riches] Obtain 90 Gold. Lose 10% of your MaxHP. ");
    private final Button button2 = new Button("\t[Success?] Obtain a random Uncommon card. Lose 10% of MaxHP.");
    private final Button button3 = new Button("\t[A Pick Me Up?] Obtain a Potion. Lose 10% of your MaxHP.");

    public KnowingSkull(Player player) {
        super(player, "Knowing Skull",
            "\n\nYou find yourself in an old, decorated chamber. \n" +
                    "In the center of the room, a large skull sits atop an ornate pedestal. As you approach, \n" +
                    "the skull bursts into flames and turns to face you.\n" +
                    "\"WHAT IS IT YOU SEEK? WHAT IS IT YOU OFFER?\"\n" +
                    "In sync with its final words, the door behind you slams shut.\n");
        setImage(new PathAssistent().toPath(this));
    }

    @Override
    public Button getButton1() {
        button1.setOnAction(event -> {
            getPlayer().setCurrentHealth((getPlayer().getMaxHealth() / 10));
            getPlayer().gainGold(90);
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);

        });
        return button1;
    }

    @Override
    public Button getButton2() {
        button2.setOnAction(event -> {
            Card selectedCard;
            getPlayer().setCurrentHealth((getPlayer().getMaxHealth() / 10));
            DeckFactory df = new DeckFactory(getPlayer(), 100);
            for (Card card : df.init(false)) {
                if (card.getRarity() == CardRarity.UNCOMMON) {
                    selectedCard = card;
                    getPlayer().addCardToDeck(selectedCard);
                    break;
                }
            }
            button2.setVisible(false);
            button1.setVisible(false);
            button3.setVisible(false);

        });
        return button2;
    }

    @Override
    public Button getButton3() {
        Potion potion = PotionFactory.generatePotion();
        button3.setOnAction(event -> {
            getPlayer().setCurrentHealth(getPlayer().getMaxHealth() / 10);
            getPlayer().addPotion(potion);
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);
        });
        return button3;
    }

}
