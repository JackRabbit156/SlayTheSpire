package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Debuff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.Map;

/**
 * Die Face slap enemy card.
 *
 * @author OF Daniel Willig
 */
public class AngerEnemyCard extends EnemyCard {

    /**
     * Constructor Face slap enemy card.
     */
    public AngerEnemyCard() {
        super("Anger", "Removes all debuffs, gains 6 Strength.", "6");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        for (Map.Entry<Effect, Integer> entry : enemy.getEffects().entrySet()) {
            if (entry.getKey() instanceof Debuff) {
                entry.setValue(0);
            }
        }
        enemy.addEffect(new StrengthBuff(), 6);
    }

}
