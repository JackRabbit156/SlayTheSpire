package de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Debuff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;

public class PoisonDebuffEnemy extends Debuff {

    public PoisonDebuffEnemy() {
        super("Poison", "At the beginning of its turn, the target loses X HP and 1 stack of poison.", EffectTrigger.BEGIN_OF_TURN, StackingBehaviour.INTENSITY);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void apply(GameContext gameContext, Entity target) {
        gameContext.setAttackContext(new AttackContext(null, target, target.getEffectCounter(this), this));
        target.looseHp(gameContext);

        target.reduceEffectCounter(this, 1);
    }

}
