package de.bundeswehr.auf.slaythespire.model.effect.debuff;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Debuff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;

public class VulnerableDebuff extends Debuff {

    public VulnerableDebuff() {
        super("Vulnerable", "Target takes 50% more damage from attacks.", EffectTrigger.BEFORE_ATTACK_TARGET, StackingBehaviour.DURATION);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void apply(GameContext gameContext, Entity target) {
        if (target.getEffectCounter(this) > 0) {
            gameContext.getAttackContext().multiplyDamage(1.5);
        }
    }

}
