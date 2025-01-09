package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.elite.book_of_stabbing;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Single stab enemy card.
 *
 * @author OF Daniel Willig
 */
public class SingleStabEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Single stab enemy card.
     */
    public SingleStabEnemyCard() {
        super("Corrosive Spit", "Deals 21 damage.", 21);
        setImagePath(new PathAssistent().toPath(this));
    }

}
