package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.boss.corrupt_heart;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.MultiAttackEnemyCard;

/**
 * Die Blood shots enemy card.
 *
 * @author OF Daniel Willig
 */
public class BloodShotsEnemyCard extends MultiAttackEnemyCard {

    /**
     * Constructor Blood shots enemy card.
     */
    public BloodShotsEnemyCard() {
        super("Blood Shots", "Deal 2 x 12 damage.", 2, 12);
        setImagePath(new PathAssistent().toPath(this));
    }

}
