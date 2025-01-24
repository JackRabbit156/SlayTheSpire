package de.bundeswehr.auf.slaythespire.model.event;

import de.bundeswehr.auf.slaythespire.gui.EventView;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.util.Random;

/**
 * Abstrakte Klasse für die Erstellung eines Events
 *
 * @author Loeschner, Marijan
 */
public abstract class Event {

    protected static final Random rnd = new Random();

    private EventView eventView;
    private Image image;
    private final Player player;
    private final Text story;
    private final String title;

    public Event(Player player, String title, String story) {
        this.player = player;
        this.title = title;
        this.story = new Text(story);
    }

    public void setImage(String imagePath) {
        this.image = new Image(imagePath);
    }

    public abstract Button getButton1();

    public Button getButton2() {
        return null;
    }

    public Button getButton3() {
        return null;
    }

    public Image getImage() {
        return image;
    }

    public Player getPlayer() {
        return player;
    }

    public Text getStory() {
        return story;
    }

    public String getTitle() {
        return title;
    }

    public EventView getEventView() {
        return eventView;
    }

    public void setEventView(EventView eventView) {
        this.eventView = eventView;
    }

}
