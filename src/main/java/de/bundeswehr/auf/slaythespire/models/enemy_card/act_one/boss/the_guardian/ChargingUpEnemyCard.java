package de.bundeswehr.auf.slaythespire.models.enemy_card.act_one.boss.the_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;

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
