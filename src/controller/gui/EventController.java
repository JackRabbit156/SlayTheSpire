package controller.gui;

import javafx.scene.layout.BorderPane;
import models.event.Event;
import models.event.act_one.BigFish;
import models.event.act_one.DeadAdventurer;
import models.player.player_structure.Player;
import view.gui.EventView;

public class EventController {

    public BorderPane getEventView(Player player){
        EventView ev = new EventView(randomEvent(), player);

        ev.getLeave().setOnMouseClicked(event -> {
            //TODO: zurück zur Map boii
        });
        return ev.display();
    }
    //TODO: random Event auswählen lassen.
    public Event randomEvent() {
        Event randomEvent;
        Event bigFish = new BigFish();
        Event deadAdventurer = new DeadAdventurer();

        //TODO: randomizer so anpassen dass die acts berücksichtigt werden
        return randomEvent = deadAdventurer;
    }

}
