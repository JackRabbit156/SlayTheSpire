package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.elite.spire_spear;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.MultiAttackEnemyCard;

/**
 * Die Skewer enemy card.
 *
 * @author OF Daniel Willig
 */
public class SkewerEnemyCard extends MultiAttackEnemyCard {

    /**
     * Constructor Skewer enemy card.
     */
    public SkewerEnemyCard() {
        super("Skewer", "Deals 10 x 3 damage.", 10, 3);
        setImagePath(new PathAssistent().toPath(this));
    }

}
