package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.elite.gremlin_leader;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.MultiAttackEnemyCard;

/**
 * Die Stab GremlinLeader enemy card.
 *
 * @author OF Daniel Willig
 */
public class StabGEnemyCard extends MultiAttackEnemyCard {

    /**
     * Constructor Stab g enemy card.
     */
    public StabGEnemyCard() {
        super("Stab", "Deals 6 x 3 damage.", 6, 3);
        setImagePath(new PathAssistent().toPath(this));
    }

}
