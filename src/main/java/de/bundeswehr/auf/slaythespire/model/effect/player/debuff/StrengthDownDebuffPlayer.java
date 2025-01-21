package de.bundeswehr.auf.slaythespire.model.effect.player.debuff;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Debuff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

public class StrengthDownDebuffPlayer extends Debuff {

    public StrengthDownDebuffPlayer() {
        super("Strength Down", "Reduce the user's Strength by X when their turn ends.", EffectTrigger.END_OF_TURN, StackingBehaviour.INTENSITY);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void apply(GameContext gameContext, Entity target) {
        Player player = gameContext.getPlayer();
        player.addEffect(new StrengthBuff(), -player.getEffectCounter(this));
        player.reduceEffectCounter(this, player.getEffectCounter(this));
    }

}
