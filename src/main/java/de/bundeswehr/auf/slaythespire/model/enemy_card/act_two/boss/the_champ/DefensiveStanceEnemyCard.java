package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.BlockEnemyCard;

/**
 * Die Defensive stance enemy card.
 *
 * @author OF Daniel Willig
 */
public class DefensiveStanceEnemyCard extends BlockEnemyCard {

    /**
     * Constructor Defensive stance enemy card.
     */
    public DefensiveStanceEnemyCard() {
        super("Defensive Stance", "Gains 15 block.", 15);
        setImagePath(new PathAssistent().toPath(this));
    }

}
