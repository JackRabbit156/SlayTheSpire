package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.repulsor;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.status.DazedCard;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

/**
 * Die Repulse enemy card.
 *
 * @author L Frank Rieger
 */
public class RepulseEnemyCard extends EnemyCard {

    public RepulseEnemyCard() {
        super("Repulse", "Shuffles 2 Dazed into the draw pile.", "");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.shuffleInDrawPile(new DazedCard());
        battleDeck.shuffleInDrawPile(new DazedCard());
    }
}
