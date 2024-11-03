package models.map_elements.field_types;

import controller.LootViewController;
import models.player.player_structure.Player;

public class BossField extends Field{
    private LootViewController lootViewController;

    public BossField() {
        super("\uD83D\uDCAA");
    }

    @Override
    public void doFieldThing(Player player) {


        //TODO Add here LootViewController(player, FieldType)
        lootViewController = new LootViewController(player, "BossField");
        lootViewController.openLootView(player);
    }
}
