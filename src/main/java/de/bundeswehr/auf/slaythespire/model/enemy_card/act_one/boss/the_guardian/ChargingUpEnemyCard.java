package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.the_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.BlockEnemyCard;

/**
 * Die Charging up enemy card.
 *
 * @author OF Daniel Willig
 */
public class ChargingUpEnemyCard extends BlockEnemyCard {

    /**
     * Constructor Charging up enemy card.
     */
    public ChargingUpEnemyCard() {
        super("Charging Up", "Gains 9 block.", 9);
        setImagePath(new PathAssistent().toPath(this));
    }

}
