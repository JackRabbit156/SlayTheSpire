package models.events;

/** Abstrakte Klasse für die Erstellung eines Events
 * @author Keil, Vladislav
 */
public abstract class Event {
    private String story;
    private String title;
    private String imagePath;

    public Event(String story, String title) {
        this.story = story;
        this.title = title;
    }

    public abstract void startEvent();

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
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

    public void setTitle(String title) {
        this.title = title;
    }
}
