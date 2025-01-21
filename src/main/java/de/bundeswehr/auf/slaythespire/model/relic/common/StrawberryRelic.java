package de.bundeswehr.auf.slaythespire.model.relic.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public class StrawberryRelic extends Relic {

    public StrawberryRelic() {
        super("Strawberry", "Upon pickup, raise your Max HP by 7.",
                RelicRarity.COMMON, RelicTrigger.PICKUP);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.gainMaxHp(7);
    }

}