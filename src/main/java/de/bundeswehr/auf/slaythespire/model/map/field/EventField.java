package de.bundeswehr.auf.slaythespire.model.map.field;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;


public class EventField extends Field{
    private static final String imagePath = "/images/map/field_types/UnknownField.png";

    private Event event;

    public EventField() {
        super(imagePath);
    }

    @Override
    public void doFieldThing(Player player) {
        GuiHelper.Scenes.startEventScene(player);
    }
}
