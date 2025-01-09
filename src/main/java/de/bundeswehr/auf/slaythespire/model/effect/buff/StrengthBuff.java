package de.bundeswehr.auf.slaythespire.model.effect.buff;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Buff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;

public class StrengthBuff extends Buff {

    public StrengthBuff() {
        super("Strength", "Increases attack damage by X (per hit).", EffectTrigger.BEFORE_ATTACK_SOURCE, StackingBehaviour.INTENSITY);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void apply(GameContext gameContext, Entity target) {
        Entity receiver;
        if (target == gameContext.getPlayer()) {
            receiver = gameContext.getSelectedEnemy();
        }
        else {
            receiver = gameContext.getPlayer();
        }
        receiver.addDamageModifier(target.getEffectCounter(this));
    }

}
