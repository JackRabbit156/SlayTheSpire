package controller.gui;

import javafx.scene.layout.BorderPane;
import models.event.Event;
import models.event.act_one.*;
import models.event.generelevents.BonfireSpirits;
import models.event.generelevents.GoldenShrine;
import models.event.generelevents.NoteForYourself;
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
        Event theCleric = new TheCleric();
        Event theSerpent = new TheSssssserpent();
        Event noteforyou = new NoteForYourself();
        Event scrapOoze = new ScrapOoze();
        Event wolrdOfGoo = new WorldOfGoo();
        Event bonfireSpirits = new BonfireSpirits();
        Event goldenShrine = new GoldenShrine();
        //TODO: randomizer so anpassen dass die acts berücksichtigt werden
        return randomEvent = goldenShrine;
    }

}
