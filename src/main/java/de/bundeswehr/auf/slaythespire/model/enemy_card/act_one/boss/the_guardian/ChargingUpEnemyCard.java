package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.the_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

/**
 * Die Charging up enemy card.
 *
 * @author OF Daniel Willig
 */
public class ChargingUpEnemyCard extends EnemyCard {
    /**
     * Constructor Charging up enemy card.
     */
    public ChargingUpEnemyCard() {
        super("Charging Up", "Gains 9 block.", "");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        enemy.addBlock(15);
    }
}
