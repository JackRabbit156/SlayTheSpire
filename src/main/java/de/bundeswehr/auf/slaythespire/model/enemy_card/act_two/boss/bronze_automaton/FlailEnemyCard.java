package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.bronze_automaton;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.MultiAttackEnemyCard;

/**
 * Die Flail enemy card.
 *
 * @author OF Daniel Willig
 */
public class FlailEnemyCard extends MultiAttackEnemyCard {

    /**
     * Constructor Flail enemy card.
     */
    public FlailEnemyCard() {
        super("Flail", "Deals 7 x 2 damage.", 7, 2);
        setImagePath(new PathAssistent().toPath(this));
    }

}
