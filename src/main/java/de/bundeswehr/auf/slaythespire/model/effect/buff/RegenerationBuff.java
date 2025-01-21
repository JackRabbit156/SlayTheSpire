package de.bundeswehr.auf.slaythespire.model.effect.buff;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Buff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;

public class RegenerationBuff extends Buff {

    public RegenerationBuff() {
        super("Regeneration", "At the end of your turn, heal X HP and reduce Regen by 1.", EffectTrigger.END_OF_TURN, StackingBehaviour.INTENSITY);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void apply(GameContext gameContext, Entity target) {
        target.heal(target.getEffectCounter(this));
        target.addEffect(this, target.getEffectCounter(this) - 1);
    }

}
