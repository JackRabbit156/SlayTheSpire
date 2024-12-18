package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

/**
 * Die Defensive stance enemy card.
 *
 * @author OF Daniel Willig
 */
public class DefensiveStanceEnemyCard extends EnemyCard {
    /**
     * Constructor Defensive stance enemy card.
     */
    public DefensiveStanceEnemyCard() {
        super("Defensive Stance", "Gains 15 block.", "");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        enemy.addBlock(15);
    }

}
