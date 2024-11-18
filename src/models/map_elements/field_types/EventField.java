package models.map_elements.field_types;

import models.event.Event;
import models.player.player_structure.Player;


public class EventField extends Field{
    private static final String imagePath = "/images/map_elements/field_types/UnknownField.png";

    private Event event;

    public EventField(Event event) {
        super(imagePath);
        this.event = event;
    }

    @Override
    public void doFieldThing(Player player) {
        this.event.startEvent();
    }
}
