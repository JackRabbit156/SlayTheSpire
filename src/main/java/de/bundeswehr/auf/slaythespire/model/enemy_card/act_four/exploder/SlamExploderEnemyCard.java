package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.exploder;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Slam enemy card.
 *
 * @author L Frank Rieger
 */
public class SlamExploderEnemyCard extends AttackEnemyCard {

    public SlamExploderEnemyCard() {
        super("Slam", "Deals 9 damage.", 9);
        setImagePath(new PathAssistent().toPath(this));
    }

}
