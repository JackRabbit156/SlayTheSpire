package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.byrd;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Headbutt enemy card.
 *
 * @author OF Daniel Willig
 */
public class HeadbuttEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Headbutt enemy card.
     */
    public HeadbuttEnemyCard() {
        super("Headbutt", "Deals 3 damage.", 3);
        setImagePath(new PathAssistent().toPath(this));
    }

}
