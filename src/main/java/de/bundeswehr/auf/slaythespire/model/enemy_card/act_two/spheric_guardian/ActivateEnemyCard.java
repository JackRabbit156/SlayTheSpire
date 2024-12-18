package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.spheric_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

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
