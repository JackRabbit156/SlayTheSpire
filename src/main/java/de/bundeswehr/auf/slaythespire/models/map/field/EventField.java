package de.bundeswehr.auf.slaythespire.models.map.field;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.models.event.Event;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;


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
