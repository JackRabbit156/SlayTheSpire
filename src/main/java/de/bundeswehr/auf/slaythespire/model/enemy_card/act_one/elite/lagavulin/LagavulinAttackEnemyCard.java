package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.elite.lagavulin;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Attack enemy card.
 *
 * @author OF Daniel Willig
 */
public class LagavulinAttackEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Attack enemy card.
     */
    public LagavulinAttackEnemyCard() {
        super("Attack", "Deal 18 damage.", 18);
        setImagePath(new PathAssistent().toPath(this));
    }

}
