package de.bundeswehr.auf.slaythespire.model.effect.debuff;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Debuff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;

public class WeakDebuff extends Debuff {

    public WeakDebuff() {
        super("Weak", "Target deals 25% less attack damage.", EffectTrigger.BEFORE_ATTACK_SOURCE, StackingBehaviour.DURATION);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void apply(GameContext gameContext, Entity target) {
        if (target.getEffectCounter(this) > 0) {
            gameContext.getAttackContext().multiplyDamage(0.75);
        }
    }
}
