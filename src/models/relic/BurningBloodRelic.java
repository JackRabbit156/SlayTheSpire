package models.relic;

import helper.PathAssistent;
import models.battle.GameContext;
import models.player.player_structure.Player;
import models.player.player_structure.PlayerType;
import models.relic.relic_structure.PlayerTypeRelic;
import models.relic.relic_structure.RelicType;

/**
 * The type Burning blood relic.
 *
 * @author OF Daniel Willig
 */
public class BurningBloodRelic extends PlayerTypeRelic {

    /**
     * Constructor Burning blood relic.
     */
    public BurningBloodRelic() {
        super("Burning Blood", "At the end of combat, heal 6 HP.", RelicType.STARTER, PlayerType.IRONCLAD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void getsUsed(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseCurrentHealth(6);
    }

}