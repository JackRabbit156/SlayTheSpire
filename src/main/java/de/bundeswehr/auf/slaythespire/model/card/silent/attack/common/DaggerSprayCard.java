package de.bundeswehr.auf.slaythespire.model.card.silent.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;

/**
 * Die Dagger Spray Karte.
 *
 * @author L Frank Rieger
 */
public class DaggerSprayCard extends AttackCard {

    public DaggerSprayCard() {
        super("Dagger Spray", "Deal 4 damage to ALL enemies twice.", 1, 4, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (int i = 0; i < 2; i++) {
            for (Enemy enemy : allEnemies) {
                player.dealDamage(gameContext, getDamage(gameContext), enemy, this);
            }
        }

        player.decreaseCurrentEnergy(getCost());
    }

}