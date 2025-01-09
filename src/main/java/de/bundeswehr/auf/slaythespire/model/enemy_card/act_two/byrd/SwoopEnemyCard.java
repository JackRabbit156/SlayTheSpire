package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.byrd;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Swoop enemy card.
 *
 * @author OF Daniel Willig
 */
public class SwoopEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Swoop enemy card.
     */
    public SwoopEnemyCard() {
        super("Swoop", "Deals 12 damage.", 12);
        setImagePath(new PathAssistent().toPath(this));
    }

}
