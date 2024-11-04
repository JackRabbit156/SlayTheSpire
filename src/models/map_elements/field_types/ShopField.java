package models.map_elements.field_types;

import controller.ShopViewController;
import models.player.player_structure.Player;

public class ShopField extends Field{
    private ShopViewController shop;
    public ShopField() {
        super("\uD83D\uDC5C");
    }

    @Override
    public void doFieldThing(Player player) {
        shop = new ShopViewController(player);
        shop.entryShop();
    }
}
