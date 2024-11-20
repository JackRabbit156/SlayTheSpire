package models.map_elements.field_types;

import controller.gui.ShopController;
import models.player.player_structure.Player;

public class ShopField extends Field{
    private static final String imagePath = "/images/map_elements/field_types/ShopField.png";

    public ShopField() {
        super(imagePath);
    }

    @Override
    public void doFieldThing(Player player) {
        // TODO Start ShopView over GUIHelper

        ShopController shop = new ShopController(player);
        shop.entryShop();
    }
}
