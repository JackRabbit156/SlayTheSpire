package de.bundeswehr.auf.slaythespire.models.map.field;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

public class RestField extends Field{
    private static final String imagePath = "/images/map_elements/field_types/RestField.png";

    public RestField() {
        super(imagePath); //
    }

    @Override
    public void doFieldThing(Player player) {
        GuiHelper.Scenes.startRestScene(player);
    }
}
