package models.map_elements.field_types;

import controller.RestViewController;
import models.player.player_structure.Player;

public class RestField extends Field{
    public RestField() {
        super("\uD83D\uDCA4"); //
    }

    @Override
    public void doFieldThing(Player player) {
        RestViewController rest = new RestViewController(player);
        rest.startRest();
    }
}
