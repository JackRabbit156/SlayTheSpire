package controller.gui;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.event.Event;
import models.event.act_one.BigFish;
import view.gui.EventView;

public class EventController {
    private Stage eventStage;
    Image img = new Image("/images/event/act_one/BigFishEvent.png");
    Event randomEvent;



    public BorderPane getEventView(Stage stage){
        EventView ev = new EventView(randomEvent());

        eventStage = stage;
        return ev.display();
    }

    public Event randomEvent() {
        Event bigFish = new BigFish();

        return randomEvent = bigFish;
    }

}
