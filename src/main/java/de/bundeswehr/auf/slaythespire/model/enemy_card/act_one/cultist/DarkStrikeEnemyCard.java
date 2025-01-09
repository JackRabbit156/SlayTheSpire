package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.cultist;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Dark strike enemy card.
 *
 * @author OF Daniel Willig
 */
public class DarkStrikeEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Dark strike enemy card.
     */
    public DarkStrikeEnemyCard() {
        super("Dark Strike", "Deal 6 damage.", 6);
        setImagePath(new PathAssistent().toPath(this));
    }

}
