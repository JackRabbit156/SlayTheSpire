package models.relics;

import models.GameContext;
import models.player.player_structure.Player;
import models.player.player_structure.PlayerType;
import models.relics.relic_structure.PlayerTypeRelic;
import models.relics.relic_structure.RelicType;

//TODO adding flavor-text to all relics?
public class BurningBloodRelic extends PlayerTypeRelic {
    // * Constructor *
    public BurningBloodRelic() {
        super("Burning Blood", "At the end of combat, heal 6 HP.", RelicType.STARTER, PlayerType.IRONCLAD);
    }

    // * Methods *
    @Override
    public void getsUsed(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseCurrentHealth(6);
    }
}