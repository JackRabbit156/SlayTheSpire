package de.bundeswehr.auf.slaythespire.model.relic.event;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.effect.player.debuff.StrengthDownDebuffPlayer;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.EventTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public class MutagenicStrengthRelic extends EventTypeRelic {

    public MutagenicStrengthRelic() {
        super("Mutagenic Strength", "Start each combat with 3 Strength that is lost at the end of your turn.", RelicTrigger.START_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.addEffect(new StrengthBuff(), 3);
        player.addEffect(new StrengthDownDebuffPlayer(), 3);
    }

}