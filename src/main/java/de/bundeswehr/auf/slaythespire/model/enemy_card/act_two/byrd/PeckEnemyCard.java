package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.byrd;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.MultiAttackEnemyCard;

/**
 * Die Peck enemy card.
 *
 * @author OF Daniel Willig
 */
public class PeckEnemyCard extends MultiAttackEnemyCard {

    /**
     * Constructor Peck enemy card.
     */
    public PeckEnemyCard() {
        super("Peck", "Deals 1 x 5 damage.", 1, 5);
        setImagePath(new PathAssistent().toPath(this));
    }

}
