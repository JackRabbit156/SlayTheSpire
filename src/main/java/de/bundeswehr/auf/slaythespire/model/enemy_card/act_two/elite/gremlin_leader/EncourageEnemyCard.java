package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.elite.gremlin_leader;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

/**
 * Die Encourage enemy card.
 *
 * @author OF Daniel Willig
 */
public class EncourageEnemyCard extends EnemyCard {
    /**
     * Constructor Encourage enemy card.
     */
    public EncourageEnemyCard() {
        super("Encourage", "All enemies gain 6 Block.", "");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        for (Enemy singleEnemy : gameContext.getEnemies()) {
            singleEnemy.addBlock(6);
        }
    }
}
