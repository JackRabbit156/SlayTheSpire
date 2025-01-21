package de.bundeswehr.auf.slaythespire.model.card.silent.attack.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;

/**
 * Die Die Die Die Karte.
 *
 * @author L Frank Rieger
 */
public class DieDieDieCard extends AttackCard {

    public DieDieDieCard() {
        super("Die Die Die", "Deal 13 damage to ALL enemies. Exhaust.", 1, 13, CardRarity.RARE, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            player.dealDamage(gameContext, getDamage(gameContext), enemy, this);
        }

        player.decreaseCurrentEnergy(getCost());
    }

}