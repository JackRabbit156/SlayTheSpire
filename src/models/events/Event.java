package models.events;

/**
 * @author Keil, Vladislav
 */
public abstract class Event {
    String story;
    String title;

    public Event(String story, String title) {
        this.story = story;
        this.title = title;
    }

    public abstract void startEvent();


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
