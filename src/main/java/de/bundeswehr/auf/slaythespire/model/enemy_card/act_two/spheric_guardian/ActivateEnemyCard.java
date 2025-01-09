package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.spheric_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.BlockEnemyCard;

/**
 * Die Activate enemy card.
 *
 * @author OF Daniel Willig
 */
public class ActivateEnemyCard extends BlockEnemyCard {

    /**
     * Constructor Activate enemy card.
     */
    public ActivateEnemyCard() {
        super("Activate", "Gains 25 block.", 25);
        setImagePath(new PathAssistent().toPath(this));
    }

}
