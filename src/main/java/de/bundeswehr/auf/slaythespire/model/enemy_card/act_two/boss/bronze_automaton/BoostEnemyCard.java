package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.bronze_automaton;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.BlockEnemyCard;

/**
 * Die Boost enemy card.
 *
 * @author OF Daniel Willig
 */
public class BoostEnemyCard extends BlockEnemyCard {

    /**
     * Constructor Boost enemy card.
     */
    public BoostEnemyCard() {
        super("Boost", "Gain 3 Strength and 9 Block.", 9);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        enemy.addEffect(new StrengthBuff(), 3);

        super.playEnemy(gameContext, enemy);
    }

}
