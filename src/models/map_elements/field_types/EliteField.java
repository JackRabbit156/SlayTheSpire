package models.map_elements.field_types;

import controller.LootViewController;
import models.player.player_structure.Player;

public class EliteField extends Field {
    private LootViewController lootViewController;

    public EliteField() {
        super("\uD83D\uDCA2");
    }

    @Override
    public void doFieldThing(Player player) {



        //TODO Es muss geprüft werden, ob das Battle erfolgreich war.
        lootViewController = new LootViewController(player, "EliteField");
        lootViewController.openLootView(player);
    }
}
