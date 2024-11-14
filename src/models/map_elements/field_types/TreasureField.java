package models.map_elements.field_types;

import controller.cli.TreasureViewController;
import models.player.player_structure.Player;

/**
 * @author Keil, Vladislav
 */
public class TreasureField extends Field {
    private static final String imagePath = "/images/map_elements/field_types/TreasureField.png";
    TreasureViewController treasureViewController;
    public TreasureField() {
        super(imagePath);
    }

    @Override
    public void doFieldThing(Player player) {
        new TreasureViewController(player);
    }
}
