package de.bundeswehr.auf.slaythespire.model.event.act_one;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.Random;

/**
 * Der Spieler kann mit einer bestimmten Wahrscheinlichkeit Gold finden
 *
 * @author  Loeschner, Marijan
 */
public class DeadAdventurer extends Event {
    private static Image image = new Image("/images/event/act_one/DeadAdventurerEvent.png");
    private String title = "Dead Adventurer";
    private String story = "\n\nYou come across a dead adventurer on the floor.\n" +
            "His pants have been stolen! Also, it looks as though he's been gouged and trampled by a horned beast. \n" +
            "Though his possessions are still intact, you're in no mind to find out what happened here...\n";
    private Button search = new Button("\t[Search] Find Loot. ");
    private Random rand = new Random();

    public DeadAdventurer() {
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

        search.setOnMouseClicked(event -> {
            boolean chance = rand.nextInt(100) <= 50;
            if (chance) {
                player.increaseGold(30);
                search.setVisible(false);
            }
            else {
                search.setVisible(false);
            }
        });
        return search;
    }
}
