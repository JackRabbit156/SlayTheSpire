package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.elite.gremlin_nob;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Skull bash enemy card.
 *
 * @author OF Daniel Willig
 */
public class SkullBashEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Skull bash enemy card.
     */
    public SkullBashEnemyCard() {
        super("Skull Bash", "Deals 6 damage.", 6);
        setImagePath(new PathAssistent().toPath(this));
    }

}
