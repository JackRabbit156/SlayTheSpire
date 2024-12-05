package de.bundeswehr.auf.slaythespire.model.event;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/** Abstrakte Klasse für die Erstellung eines Events
 * @author Loeschner, Marijan
 */
public abstract class Event {
    private String title;
    private String story;
    private Image image;
    private Button button1;
    private Button button2;
    private Button button3;

    public Event(){
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getTitle() {
        return title;
    }

    public String setTitle(String title) {
        return this.title = title;
    }

    public Button getButton1(Player player) {
        return button1;
    }

    public void setButton1(Button button1) {
        this.button1 = button1;
    }

    public Button getButton2(Player player) {
        return button2;
    }

    public void setButton2(Button button2) {
        this.button2 = button2;
    }

    public Button getButton3(Player player) {
        return button3;
    }

    public void setButton3(Button button3) {
        this.button3 = button3;
    }
}
