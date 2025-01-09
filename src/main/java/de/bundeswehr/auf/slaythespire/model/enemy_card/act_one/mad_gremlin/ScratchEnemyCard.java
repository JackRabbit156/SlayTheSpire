package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.mad_gremlin;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Scratch enemy card.
 *
 * @author OF Daniel Willig
 */
public class ScratchEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Scratch enemy card.
     */
    public ScratchEnemyCard() {
        super("Scratch", "Deals 4 damage.", 4);
        setImagePath(new PathAssistent().toPath(this));
    }

}
