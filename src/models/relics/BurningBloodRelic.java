package models.relics;

import models.GameContext;
import models.player.player_structure.Player;
import models.player.player_structure.PlayerType;
import models.relics.relic_structure.Relic;
import models.relics.relic_structure.RelicType;

//TODO adding flavor-text to all relics?
public class BurningBloodRelic extends Relic {
    protected BurningBloodRelic(String name, String description, RelicType rarity, PlayerType playerType) {
        super("Burning Blood", "At the end of combat, heal 6 HP.", RelicType.STARTER);
    }

    @Override
    public void getsUsed(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseCurrentHealth(6);
    }
}
