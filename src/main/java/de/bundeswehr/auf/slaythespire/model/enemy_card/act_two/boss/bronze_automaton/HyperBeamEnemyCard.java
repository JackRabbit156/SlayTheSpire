package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.bronze_automaton;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Hyper beam enemy card.
 *
 * @author OF Daniel Willig
 */
public class HyperBeamEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Hyper beam enemy card.
     */
    public HyperBeamEnemyCard() {
        super("Hyper Beam", "Deals 45 damage.", 45);
        setImagePath(new PathAssistent().toPath(this));
    }

}
