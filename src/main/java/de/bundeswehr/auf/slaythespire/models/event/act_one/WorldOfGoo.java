package de.bundeswehr.auf.slaythespire.models.event.act_one;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.models.event.Event;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

import java.util.Random;

/**
 * Der Spieler erhält entweder Gold, oder verliert einen zufälligen Goldwert.
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class WorldOfGoo extends Event {
    private static Image image = new Image("/images/event/act_one/WordOfGooEvent.png");
    private String title = "World of Goo";
    private String story = "\n\nYou fall into a puddle.\n" +
            "IT'S MADE OF SLIME GOO!!\n" +
            "Frantically, you claw yourself out over several minutes as you feel the goop starting to burn.\n" +
            "You can feel goop in your ears, goop in your nose, goop everywhere.\n";
    private Button button1 = new Button("\t[Reach Inside] ");
    private Button button2 = new Button("\t[Leave It] ");
    private Random rand = new Random();

    public WorldOfGoo() {
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
            player.increaseCurrentHealth(-11);
            player.increaseGold(75);
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button1;
    }
    public Button getButton2(Player player) {
        int lostGold = rand.nextInt(50 - 20);
        button2.setOnMouseClicked(event -> {
            player.decreaseGold(lostGold);
            button2.setVisible(false);
            button1.setVisible(false);
        });
        return button2;
    }
}