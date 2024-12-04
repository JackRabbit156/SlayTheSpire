package models.enemy_card.act_two.spheric_guardian;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;

/**
 * Die Activate enemy card.
 *
 * @author OF Daniel Willig
 */
public class ActivateEnemyCard extends EnemyCard {
    /**
     * Constructor Activate enemy card.
     */
    public ActivateEnemyCard() {
        super("Activate", "Gains 25 block.", "");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        enemy.addBlock(25);
    }

}
