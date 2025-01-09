package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.the_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.MultiAttackEnemyCard;

/**
 * Die Whirlwind enemy card.
 *
 * @author OF Daniel Willig
 */
public class WhirlwindEnemyCard extends MultiAttackEnemyCard {

    /**
     * Constructor Whirlwind enemy card.
     */
    public WhirlwindEnemyCard() {
        super("Whirlwind", "Deals 5 x 4 damage.", 5, 4);
        setImagePath(new PathAssistent().toPath(this));
    }

}
