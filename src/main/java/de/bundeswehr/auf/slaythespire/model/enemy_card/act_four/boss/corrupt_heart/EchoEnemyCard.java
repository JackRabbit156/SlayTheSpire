package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.boss.corrupt_heart;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Echo enemy card.
 *
 * @author OF Daniel Willig
 */
public class EchoEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Echo enemy card.
     */
    public EchoEnemyCard() {
        super("Echo", "Deals 40 damage.", 40);
        setImagePath(new PathAssistent().toPath(this));
    }

}
