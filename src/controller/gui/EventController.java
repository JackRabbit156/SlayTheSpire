package controller.gui;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.event.Event;
import models.event.act_one.BigFish;
import models.player.player_structure.Player;
import view.gui.EventView;

public class EventController {
    private Stage eventStage;
    private Event randomEvent;



    public BorderPane getEventView(Player player){
        EventView ev = new EventView(randomEvent(), player);

        return ev.display();
    }
    //TODO: random Event auswählen lassen.
    public Event randomEvent() {
        Event bigFish = new BigFish();

        return randomEvent = bigFish;
    }

}
