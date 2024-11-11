package models.map_elements.field_types;

import controller.cli.RestViewController;
import models.player.player_structure.Player;

public class RestField extends Field{
    private static final String imagePath = "/images/map/rest.png";

    public RestField() {
        super(imagePath); //
    }

    @Override
    public void doFieldThing(Player player) {
        if(isFieldBeaten())
            return;

        RestViewController rest = new RestViewController(player);
        rest.startRest();


        setFieldBeaten();
    }
}
