package de.bundeswehr.auf.slaythespire.model.relic.ironclad.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.*;

/**
 * The type Burning blood relic.
 *
 * @author OF Daniel Willig
 */
public class BurningBloodRelic extends StarterTypeRelic {

    /**
     * Constructor Burning blood relic.
     */
    public BurningBloodRelic() {
        super("Burning Blood", "At the end of combat, heal 6 HP.",
            RelicRarity.COMMON, PlayerType.IRONCLAD, RelicTrigger.END_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.heal(6);
    }

}