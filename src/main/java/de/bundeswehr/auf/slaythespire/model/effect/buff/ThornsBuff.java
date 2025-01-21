package de.bundeswehr.auf.slaythespire.model.effect.buff;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Buff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

public class ThornsBuff extends Buff {

    public ThornsBuff() {
        super("Thorns", "When attacked, deals X damage back.", EffectTrigger.AFTER_ATTACK, StackingBehaviour.INTENSITY);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void apply(GameContext gameContext, Entity target) {
        AttackContext attackContext = gameContext.getAttackContext();
        Object action = attackContext.getAction();
        if (action instanceof AttackCard || action instanceof AttackEnemyCard) {
            int damage = target.getEffectCounter(this);
            Entity source = attackContext.getSource();
            target.dealDamage(gameContext, damage, source, this);
        }
    }

}
