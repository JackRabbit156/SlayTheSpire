package models.map_elements.field_types;

import controller.gui.RestController;
import helper.GuiHelper;
import models.player.player_structure.Player;

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
