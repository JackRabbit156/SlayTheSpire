package de.bundeswehr.auf.slaythespire.model.relic.event;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.EventTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.relic.structure.SpecialTypeRelic;

public class BloodyIdolRelic extends EventTypeRelic {

    public BloodyIdolRelic() {
        super("Bloody Idol", "Whenever you gain Gold, heal 5 HP.", RelicTrigger.GAIN_GOLD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.heal(5);
    }

}