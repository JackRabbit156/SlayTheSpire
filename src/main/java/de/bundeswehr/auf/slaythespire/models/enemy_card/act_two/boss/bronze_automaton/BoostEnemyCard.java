package de.bundeswehr.auf.slaythespire.models.enemy_card.act_two.boss.bronze_automaton;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;

/**
 * Die Boost enemy card.
 *
 * @author OF Daniel Willig
 */
public class BoostEnemyCard extends EnemyCard {
    /**
     * Constructor Boost enemy card.
     */
    public BoostEnemyCard() {
        super("Boost", "Gains 9 block", "");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        enemy.addBlock(9);
    }
}
