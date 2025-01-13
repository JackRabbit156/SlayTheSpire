package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.slime_boss;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.status.SlimedCard;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

/**
 * Die Slam enemy card.
 *
 * @author OF Daniel Willig
 */
public class GoopSprayEnemyCard extends EnemyCard {

    /**
     * Constructor Slam enemy card.
     */
    public GoopSprayEnemyCard() {
        super("Goop Spray", "Shuffles 3 Slimed to your discard pile.", "3");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.addToDiscardPile(new SlimedCard());
        battleDeck.addToDiscardPile(new SlimedCard());
        battleDeck.addToDiscardPile(new SlimedCard());
    }

}
