package de.bundeswehr.auf.slaythespire.models.relic;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.PlayerType;
import de.bundeswehr.auf.slaythespire.models.relic.relic_structure.PlayerTypeRelic;
import de.bundeswehr.auf.slaythespire.models.relic.relic_structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.models.relic.relic_structure.RelicType;

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
        super("Burning Blood", "At the end of combat, heal 6 HP.", RelicType.STARTER, PlayerType.IRONCLAD, RelicTrigger.END_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void getsUsed(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseCurrentHealth(6);
    }

}