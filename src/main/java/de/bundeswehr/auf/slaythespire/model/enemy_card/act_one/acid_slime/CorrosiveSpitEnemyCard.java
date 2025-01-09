package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.acid_slime;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Corrosive spit enemy card.
 *
 * @author OF Daniel Willig
 */
public class CorrosiveSpitEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Corrosive spit enemy card.
     */
    public CorrosiveSpitEnemyCard() {
        super("Corrosive Spit", "Deals 11 damage.", 11);
        setImagePath(new PathAssistent().toPath(this));
    }

}
