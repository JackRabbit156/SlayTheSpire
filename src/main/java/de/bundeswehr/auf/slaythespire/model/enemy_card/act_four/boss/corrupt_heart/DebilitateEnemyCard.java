package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.boss.corrupt_heart;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.status.*;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.VulnerableDebuff;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.MultiAttackEnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Blood shots enemy card.
 *
 * @author OF Daniel Willig
 */
public class DebilitateEnemyCard extends EnemyCard {

    /**
     * Constructor Blood shots enemy card.
     */
    public DebilitateEnemyCard() {
        super("Debilitate", "Applies 2 Vulnerable, and 2 Weak in that order.\n" +
                "Shuffles a Dazed, Slimed, Wound, Burn and a Void into the draw pile.", "2/2");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.addEffect(new VulnerableDebuff(), 2);
        player.addEffect(new WeakDebuff(), 2);

        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.shuffleInDrawPile(new DazedCard());
        battleDeck.shuffleInDrawPile(new SlimedCard());
        battleDeck.shuffleInDrawPile(new WoundCard());
        battleDeck.shuffleInDrawPile(new BurnCard());
        battleDeck.shuffleInDrawPile(new VoidCard());
    }

}
