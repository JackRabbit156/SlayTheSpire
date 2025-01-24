package de.bundeswehr.auf.slaythespire.model.relic.event;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.EventTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public class GremlinVisageRelic extends EventTypeRelic {

    public GremlinVisageRelic() {
        super("Gremlin Visage", "Start each combat with 1 Weak.", RelicTrigger.START_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.addEffect(new WeakDebuff(), 1);
    }

}