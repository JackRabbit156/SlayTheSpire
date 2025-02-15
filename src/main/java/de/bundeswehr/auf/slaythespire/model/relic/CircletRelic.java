package de.bundeswehr.auf.slaythespire.model.relic;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.relic.structure.SpecialTypeRelic;

/**
 * The Circlet Relic starts to appear once you've exhausted the pool of obtainable Relics.
 * They stack in your Relic inventory when collecting them.
 */
public class CircletRelic extends SpecialTypeRelic {

    public CircletRelic() {
        super("Circlet", "Collect as many as you can.", RelicTrigger.NONE);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        // This relic has no effect.
    }

}