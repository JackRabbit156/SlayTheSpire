package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.elite.gremlin_nob;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Rush enemy card.
 *
 * @author OF Daniel Willig
 */
public class RushEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Rush enemy card.
     */
    public RushEnemyCard() {
        super("Rush", "Deals 14 damage.", 14);
        setImagePath(new PathAssistent().toPath(this));
    }

}
