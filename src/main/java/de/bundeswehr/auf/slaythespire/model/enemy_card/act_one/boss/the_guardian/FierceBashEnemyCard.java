package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.the_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Fierce bash enemy card.
 *
 * @author OF Daniel Willig
 */
public class FierceBashEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Fierce bash enemy card.
     */
    public FierceBashEnemyCard() {
        super("Fierce Bash", "Deals 32 damage.", 32);
        setImagePath(new PathAssistent().toPath(this));
    }

}
