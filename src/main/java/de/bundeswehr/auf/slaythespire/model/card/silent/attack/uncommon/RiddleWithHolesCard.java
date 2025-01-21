package de.bundeswehr.auf.slaythespire.model.card.silent.attack.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Riddle With Holes Karte.
 *
 * @author L Frank Rieger
 */
public class RiddleWithHolesCard extends AttackCard {

    public RiddleWithHolesCard() {
        super("Riddle With Holes", "Deal 3 damage 5 times.", 2, 3, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        Enemy enemy = gameContext.getSelectedEnemy();
        for (int i = 0; i < 5; i++) {
            player.dealDamage(gameContext, getDamage(gameContext), enemy, this);
        }

        player.decreaseCurrentEnergy(getCost());
    }

}