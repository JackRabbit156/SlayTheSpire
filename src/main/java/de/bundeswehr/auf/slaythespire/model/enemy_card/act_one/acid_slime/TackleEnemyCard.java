package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.acid_slime;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.status.SlimedCard;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Tackle enemy card.
 *
 * @author L Frank Rieger
 */
public class TackleEnemyCard extends AttackEnemyCard {

    public TackleEnemyCard() {
        super("Tackle", "Deals 16 damage.", 16);
        setImagePath(new PathAssistent().toPath(this));
    }



}
