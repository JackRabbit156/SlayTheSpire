package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.MultiAttackEnemyCard;

/**
 * Die Execute enemy card.
 *
 * @author OF Daniel Willig
 */
public class ExecuteEnemyCard extends MultiAttackEnemyCard {

    /**
     * Constructor Execute enemy card.
     */
    public ExecuteEnemyCard() {
        super("Execute", "Deals 10 x 2 damage.", 10, 2);
        setImagePath(new PathAssistent().toPath(this));
    }

}
