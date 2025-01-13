package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.repulsor;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Bash enemy card.
 *
 * @author L Frank Rieger
 */
public class BashRepulsorEnemyCard extends AttackEnemyCard {

    public BashRepulsorEnemyCard() {
        super("Bash", "Deals 11 damage.", 11);
        setImagePath(new PathAssistent().toPath(this));
    }

}
