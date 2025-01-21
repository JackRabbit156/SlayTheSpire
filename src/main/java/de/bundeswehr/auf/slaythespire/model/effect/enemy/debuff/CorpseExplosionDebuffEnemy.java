package de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Debuff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;

import java.util.List;

public class CorpseExplosionDebuffEnemy extends Debuff {

    public CorpseExplosionDebuffEnemy() {
        super("Corpse Explosion", "When the enemy dies, deal damage equal to its max HP to ALL enemies.", EffectTrigger.AFTER_ATTACK, StackingBehaviour.NON_STACKING);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void apply(GameContext gameContext, Entity target) {
        if (!target.isAlive()) {
            List<Enemy> allEnemies = gameContext.getEnemies();
            for (Enemy enemy : allEnemies) {
                if (enemy != target && enemy.isAlive()) {
                    gameContext.setAttackContext(new AttackContext(target, enemy, target.getMaxHealth(), this));
                    enemy.takeDamage(gameContext);
                }
            }
        }
    }

}
