package models.event.act_one;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import models.card.DeckFactory;
import models.event.Event;
import models.player.player_structure.Player;

import java.util.Random;
/**
 * Der Spieler kann einen Preis gewinnen
 *
 * @author  Loeschner, Marijan
 */
public class WheelOfChange extends Event {
    DeckFactory df;
    private static Image image = new Image("/images/event/generalevents/WheelOfChangeEvent.jpg");
    private String title = "Wheel of Change";
    private String story = "\n\nYou come upon a dapper looking, cheery gremlin.\n" +
            "Gremlin: \"It's time to spin the wheel! Are you R E A D Y ? Of course you are!\"\n";
    private Button button1 = new Button("\t[Play] Spin the Wheel! ");
    private Random rand = new Random();
    private int gold = 100;

    public WheelOfChange() {
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
            df = new DeckFactory(player, 1);
            button1.setVisible(false);
            int randInt = rand.nextInt(4);
            switch(randInt){
                case 1:
                    if(player.getCurrentAct() ==  2 ){gold = 200;}
                    player.increaseGold(100);
                case 2:
                    df.removeRandomCard(player);
                case 3:
                    player.setCurrentHealth((int) (player.getCurrentHealth() * 0.9));
                default:
                    player.setCurrentHealth(player.getMaxHealth());
            }
        });
        return button1;
    }

}
