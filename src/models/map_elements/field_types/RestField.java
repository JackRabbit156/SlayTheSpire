package models.map_elements.field_types;

import controller.cli.RestViewController;
import controller.gui.RestController;
import models.player.player_structure.Player;

public class RestField extends Field{
    private static final String imagePath = "/images/map_elements/field_types/RestField.png";

    public RestField() {
        super(imagePath); //
    }

    @Override
    public void doFieldThing(Player player) {
        if(isFieldBeaten())
            return;

        // TODO Start RestView  over GUIHelper
//        new RestController(player);


        setFieldBeaten();
    }
}
