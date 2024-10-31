package models.map_elements.field_types;

import models.player.player_structure.Player;

public class UnknownField extends Field {
    public UnknownField() {
        super("❓");
    }

    @Override
    public void doFieldThing(Player player) {

    }
}
