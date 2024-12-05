package models.event.act_one;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import models.card.DeckFactory;
import models.event.Event;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;
/**
 * Der Spieler kann Potions erhalten
 *
 * @author  Loeschner, Marijan
 */
public class Lab extends Event {
    DeckFactory df;
    private static Image image = new Image("/images/event/generalevents/LabEvent.jpg");
    private String title = "Lab";
    private String story = "\n\nYou find yourself in a room filled with racks of \n" +
            "test tubes, beakers, flasks, forceps, pinch clamps, stirring rods, tongs, \n" +
            "goggles, funnels, pipets, cylinders, condensers, and even a rare spiral tube of glass.\n" +
            "Why do you know the name of all these tools? It doesn't matter, you take a look around.\n";
    private Button button1 = new Button("\t[Search] Optaion potions");

    public Lab() {
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
        df = new DeckFactory(player, 3);
        PotionCard potion = df.generatePotion();
        button1.setOnMouseClicked(event -> {
            player.addPotionCard(potion);
            button1.setVisible(false);
        });
        return button1;
    }

}
