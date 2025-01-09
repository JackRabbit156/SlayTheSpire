package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.slime_boss;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Slam enemy card.
 *
 * @author OF Daniel Willig
 */
public class SlamEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Slam enemy card.
     */
    public SlamEnemyCard() {
        super("Slam", "Deals 35 damage.", 35);
        setImagePath(new PathAssistent().toPath(this));
    }

}
