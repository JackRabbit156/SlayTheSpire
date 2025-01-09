package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Heavy slash enemy card.
 *
 * @author OF Daniel Willig
 */
public class HeavySlashEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Heavy slash enemy card.
     */
    public HeavySlashEnemyCard() {
        super("Heavy Slash", "Deals 16 damage.", 16);
        setImagePath(new PathAssistent().toPath(this));
    }

}
