package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Face slap enemy card.
 *
 * @author OF Daniel Willig
 */
public class FaceSlapEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Face slap enemy card.
     */
    public FaceSlapEnemyCard() {
        super("Face Slap", "Deals 12 damage.", 12);
        setImagePath(new PathAssistent().toPath(this));
    }

}
