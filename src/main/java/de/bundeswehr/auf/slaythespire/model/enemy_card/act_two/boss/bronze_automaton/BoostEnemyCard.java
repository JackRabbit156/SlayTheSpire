package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.bronze_automaton;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.BlockEnemyCard;

/**
 * Die Boost enemy card.
 *
 * @author OF Daniel Willig
 */
public class BoostEnemyCard extends BlockEnemyCard {

    /**
     * Constructor Boost enemy card.
     */
    public BoostEnemyCard() {
        super("Boost", "Gains 9 block", 9);
        setImagePath(new PathAssistent().toPath(this));
    }

}
