package models.event.act_two;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import models.card.DeckFactory;
import models.event.Event;
import models.player.player_structure.Player;
import view.gui.EventView;

public class MaskedBandits extends Event {
    DeckFactory df;
    Player player;
    private static Image image = new Image("/images/event/act_two/beggar.jpg");
    private String title = "Lab";
    private String story = "\n\nYou encounter a group of bandits wearing large red masks.\n" +
            "Romeo: \"Hello, pay up to pass... a reasonable fee of ALL your gold will do! Heh heh!\"\n";
    private Button button1 = new Button("\t[Search] Optaion potions");
    private Button button2 = new Button("\t[Fight] Enter a combat against 3 Enemies");

    //TODO: Leave Button entfernen
    public MaskedBandits() {
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
            player.decreaseGold(player.getGold());
            button1.setVisible(false);
            button2.setVisible(false);
            //TODO: back to MapView
        });
        return button1;
    }
    public Button getButton2(Player player) {

        button2.setOnMouseClicked(event -> {
            //TODO: Start an Encounter
            button2.setVisible(false);
        });
        return button2;
    }

}
