package models.map_elements.field_types;

import helper.GuiHelper;
import models.event.Event;
import models.player.player_structure.Player;


public class EventField extends Field{
    private static final String imagePath = "/images/map_elements/field_types/UnknownField.png";

    private Event event;

    public EventField() {
        super(imagePath);
    }

    @Override
    public void doFieldThing(Player player) {
        GuiHelper.Scenes.startEventScene(player);
    }
}
