package de.bundeswehr.auf.slaythespire.model.effect.buff;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Buff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;

public class ThornsBuff extends Buff {

    public ThornsBuff() {
        super("Thorns", "When attacked, deals X damage back.", EffectTrigger.AFTER_ATTACK, StackingBehaviour.INTENSITY);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void apply(GameContext gameContext, Entity target) {
        int damage = target.getEffectCounter(this);
        Entity source = gameContext.getAttackContext().getSource();
        target.dealDamage(gameContext, damage, source, this);
    }

}
