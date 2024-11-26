package models.event.act_one;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import models.card.DeckFactory;
import models.event.Event;
import models.player.player_structure.Player;

/**
 * Der Spieler kann  im austausch gegen Gold 1. die MaxHP erhöhen, oder 2.Eine Karte aus dem Spielerdeck entfernen
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class TheCleric extends Event {
    DeckFactory df;
    private static Image image = new Image("/images/event/act_one/TheClericEvent.png");
    private String title = "The Clercic";
    private String story = "\n\nA strange blue humanoid with a golden helm(?) approaches you with a huge smile.\n" +
                            "\"Hello friend! I am Cleric! Are you interested in my services?!\"\n" +
                            "\"the creature shouts, loudly.\"\n";
    private Button button1 = new Button("\t[Heal] Lose 35 Gold. Heal 25% of your Max HP. ");
    private Button button2 = new Button("\t[Purify] Lose 50 Gold. A random Card from your deck.");

    public TheCleric() {
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
            player.increaseCurrentHealth(player.getMaxHealth() / 4);
            player.decreaseGold(35);
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button1;
    }

    @Override
    public Button getButton2(Player player) {
        button2.setOnMouseClicked(event -> {
            df = new DeckFactory(player, 1);
            df.removeRandomCard(player);
            player.decreaseGold(50);
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button2;
    }
}
