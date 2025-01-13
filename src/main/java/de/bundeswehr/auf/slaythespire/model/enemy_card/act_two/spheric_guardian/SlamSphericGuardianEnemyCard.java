package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.spheric_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.MultiAttackEnemyCard;

/**
 * Die Slam SphericGuardian enemy card.
 *
 * @author OF Daniel Willig
 */
public class SlamSphericGuardianEnemyCard extends MultiAttackEnemyCard {

    /**
     * Constructor Slam s enemy card.
     */
    public SlamSphericGuardianEnemyCard() {
        super("Slam", "Deals 10 x 2 damage.", 10, 2);
        setImagePath(new PathAssistent().toPath(this));
    }

}
