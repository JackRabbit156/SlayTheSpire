package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.acid_slime;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.status.SlimedCard;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Corrosive spit enemy card.
 *
 * @author OF Daniel Willig
 */
public class CorrosiveSpitEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Corrosive spit enemy card.
     */
    public CorrosiveSpitEnemyCard() {
        super("Corrosive Spit", "Deals 11 damage, shuffles 2 Slimed into the discard pile.", 11);
        setImagePath(new PathAssistent().toPath(this));
    }


    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        super.playEnemy(gameContext, enemy);

        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.addToDiscardPile(new SlimedCard());
        battleDeck.addToDiscardPile(new SlimedCard());
    }


}
