package de.bundeswehr.auf.slaythespire.models.enemy_card.act_two.boss.the_champ;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;

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
