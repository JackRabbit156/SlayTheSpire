package models.event.act_two;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import models.card.DeckFactory;
import models.card.card_structure.Card;
import models.card.card_structure.CardRarity;
import models.event.Event;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;

import java.util.List;

public class KnowingSkull extends Event {
    private DeckFactory df;
    private Image image = new Image("/images/event/act_two/knowingSkull.jpg");
    private String title = "Knowing Skull";
    private String story = "\n\nYou find yourself in an old, decorated chamber. \n" +
            "In the center of the room, a large skull sits atop an ornate pedestal. As you approach, \n" +
            "the skull bursts into flames and turns to face you.\n" +
            "\"WHAT IS IT YOU SEEK? WHAT IS IT YOU OFFER?\"\n" +
            "In sync with its final words, the door behind you slams shut.\n";
    private Button button1 = new Button("\t[Riches] Obtain 90 Gold. Lose 10% of your MaxHP. ");
    private Button button2 = new Button("\t[Success?] Obtain a random Uncommon card. Lose 10% of MaxHP.");
    private Button button3 = new Button("\t[A Pick Me Up?] Obtain a Potion. Lose 10% of your MaxHP.");

    public KnowingSkull() {
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
            player.setCurrentHealth((player.getMaxHealth() / 10));
            player.increaseGold(90);
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);

        });
        return button1;
    }

    public Button getButton2(Player player) {
        df = new DeckFactory(player, 44);
        List<Card> deck;
        deck = df.init();
        button2.setOnMouseClicked(event -> {
            Card selectedCard;
            player.setCurrentHealth((player.getMaxHealth() / 10));
            for (int i = 0; i < deck.size(); i++) {
                if(deck.get(i).getCardRarity() == CardRarity.UNCOMMON) {
                    selectedCard = deck.get(i);
                    player.addCardToDeck(selectedCard);
                }
            }
            button2.setVisible(false);
            button1.setVisible(false);
            button3.setVisible(false);

        });
        return button2;
    }

    public Button getButton3(Player player) {
        df = new DeckFactory(player, 1);
        PotionCard potion = df.generatePotion();

        button3.setOnMouseClicked(event -> {
            player.setCurrentHealth((player.getMaxHealth() / 10));
            player.addPotionCard(potion);
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);
        });
        return button3;
    }
}
