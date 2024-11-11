package models.map_elements.field_types;

import controller.cli.ShopViewController;
import models.player.player_structure.Player;

public class ShopField extends Field{
    private static final String imagePath = "/images/map/shop.png";

    private ShopViewController shop;
    public ShopField() {
        super(imagePath);
    }

    @Override
    public void doFieldThing(Player player) {
        ShopViewController shop = new ShopViewController(player);
        shop.entryShop();
    }
}
