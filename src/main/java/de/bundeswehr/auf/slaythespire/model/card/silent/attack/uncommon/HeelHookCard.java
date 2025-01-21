package de.bundeswehr.auf.slaythespire.model.card.silent.attack.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.*;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Heel Hook Karte.
 *
 * @author L Frank Rieger
 */
public class HeelHookCard extends AttackCard {

    public HeelHookCard() {
        super("Heel Hook", "Deal 5 damage. If the enemy is Weak, Gain 1 Energy and draw 1 card.", 1, 5, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);

        Player player = gameContext.getPlayer();
        Enemy enemy = gameContext.getSelectedEnemy();
        BattleDeck battleDeck = gameContext.getBattleDeck();
        if (enemy.getEffectCounter(new WeakDebuff()) > 0) {
            player.gainEnergy(1);
            battleDeck.drawCard(1);
        }
    }

}