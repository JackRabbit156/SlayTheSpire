package models.map_elements.field_types;

import controller.TreasureViewController;
import models.player.player_structure.Player;

/**
 * @author Keil, Vladislav
 */
public class TreasureField extends Field {
    TreasureViewController treasureViewController;
    public TreasureField() {
        super("T");
    }

    @Override
    public void doFieldThing(Player player) {
        new TreasureViewController(player);
    }
}
