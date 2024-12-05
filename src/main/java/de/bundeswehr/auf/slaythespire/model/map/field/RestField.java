package de.bundeswehr.auf.slaythespire.model.map.field;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

public class RestField extends Field{
    private static final String imagePath = "/images/map/field_types/RestField.png";

    public RestField() {
        super(imagePath); //
    }

    @Override
    public void doFieldThing(Player player) {
        GuiHelper.Scenes.startRestScene(player);
    }
}
