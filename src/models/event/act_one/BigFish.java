package models.event.act_one;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import models.event.Event;
import models.player.player_structure.Player;

import java.util.Scanner;
/**
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class BigFish extends Event{

    private static Image image = new Image("/images/event/act_one/BigFishEvent.png");
    private String title = "Big Fish";
    private String story = "\n\nAs you make your way down a long corridor you see a banana, a donut, and a box floating about. \n" +
            "No... upon closer inspection they are tied to strings coming from holes in the ceiling.\n " +
            "There is a quiet cackling from above as you approach the objects.\n";
    private Button banana = new Button("\t[Banana] Heal 1/3 of your max HP.");
    private Button donut = new Button("\t[Donut] Max HP +5.");
    private Button box = new Button("\t[Box] We don't have a relic, stop looking!");

    public BigFish() {
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
        banana.setOnMouseClicked(event -> {
            player.increaseCurrentHealth(player.getMaxHealth() / 3);
        });
        return banana;
    }

    public Button getButton2(Player player){
        donut.setOnMouseClicked(event -> {
            player.increaseMaxHealth(5);
        });
        return donut;
    }

    @Override
    public Button getButton3(Player player) {
        box.setOnMouseClicked(event -> {
            //Hier würde ich meine Relics aufbewahren. WENN ICH WELCHE HÄTTE!
        });
        return box;
    }
}
