package models.map_elements.field_types;

import models.events.Event;
import models.player.player_structure.Player;


public class EventField extends Field{
    private Event event;

    public EventField(Event event) {
        super("❇");
        this.event = event;
    }

    @Override
    public void doFieldThing(Player player) {
        this.event.startEvent();
    }
}
