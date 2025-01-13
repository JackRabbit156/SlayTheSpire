package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.elite.spire_spear;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.status.BurnCard;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.MultiAttackEnemyCard;

/**
 * Die Burn Strike enemy card.
 *
 * @author L Frank Rieger
 */
public class BurnStrikeEnemyCard extends MultiAttackEnemyCard {

    public BurnStrikeEnemyCard() {
        super("Burn Strike", "Deals 5x2 damage. Adds 2 Burns into your discard pile.", 5, 2);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        super.playEnemy(gameContext, enemy);

        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.addToDiscardPile(new BurnCard());
        battleDeck.addToDiscardPile(new BurnCard());
    }
}
