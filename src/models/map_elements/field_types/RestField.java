package models.map_elements.field_types;

import controller.BattleViewController;
import controller.RestViewController;
import models.player.player_structure.Player;

public class RestField extends Field{
    public RestField() {
        super("\uD83D\uDCA4"); //
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
