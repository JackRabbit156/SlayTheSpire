package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.spiker;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.ThornsBuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

/**
 * Die Spike enemy card.
 *
 * @author L Frank Rieger
 */
public class SpikeEnemyCard extends EnemyCard {

    public SpikeEnemyCard() {
        super("Spike", "Gains 2 Thorns.", "2");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        enemy.addEffect(new ThornsBuff(), 2);
    }

}
