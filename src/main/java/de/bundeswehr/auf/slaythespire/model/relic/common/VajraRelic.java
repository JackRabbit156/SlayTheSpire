package de.bundeswehr.auf.slaythespire.model.relic.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public class VajraRelic extends Relic {

    public VajraRelic() {
        super("Vajra", "At the start of each combat, gain 1 Strength.",
                RelicRarity.COMMON, RelicTrigger.START_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.addEffect(new StrengthBuff(), 1);
    }

}