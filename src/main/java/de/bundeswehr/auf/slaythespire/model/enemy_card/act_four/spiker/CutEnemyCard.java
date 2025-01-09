package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.spiker;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Cut enemy card.
 *
 * @author OF Daniel Willig
 */
public class CutEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Cut enemy card.
     */
    public CutEnemyCard() {
        super("Cut", "Deals 7 damage.", 7);
        setImagePath(new PathAssistent().toPath(this));
    }

}
