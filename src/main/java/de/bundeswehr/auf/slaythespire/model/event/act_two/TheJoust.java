package de.bundeswehr.auf.slaythespire.model.event.act_two;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.Random;
/**
 * Der Spieler kann wetten und mit einer bestimmten Wahrscheinlichkeit Gold gewinnen
 *
 * @author  Loeschner, Marijan
 */
public class TheJoust extends Event {
    private static Image image = new Image("/images/event/act_two/joust.jpg");
    private String title = "Dead Adventurer";
    private String story = "\n\nAs you make your way through the large buildings you come across a long narrow bridge \n" +
            "and spot knights on either side, facing one another. You approach…\n" +
            "Knight: \"HALT!\"\n";
    private Button button1 = new Button("\t[Murderer] Bet 50 Gold. Win 100 Gold.");
    private Button button2 = new Button("\t[Owner] bet 50 Gold. Win 250 Gold.");
    private Random rand = new Random();
    private boolean chance;

    public TheJoust() {
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

    public Button getButton1(Player player){

        button1.setOnMouseClicked(event -> {
            chance = rand.nextInt(100) <= 70;
            if (chance) {
                player.increaseGold(50);
            }
            else {
                player.decreaseGold(50);
            }
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button1;
    }

    public Button getButton2(Player player) {
        chance = rand.nextInt(100) <= 30;
        button2.setOnMouseClicked(event -> {
            if (chance) {
                player.increaseGold(200);
            }
            else {
                player.decreaseGold(50);
            }
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button2;
    }
}
