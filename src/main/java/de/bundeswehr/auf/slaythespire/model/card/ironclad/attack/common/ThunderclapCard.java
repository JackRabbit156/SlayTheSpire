package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.VulnerableDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;

/**
 * Die Thunderclap Karte.
 *
 * @author OF Daniel Willig
 */
public class ThunderclapCard extends AttackCard {

    /**
     * Constructor Thunderclap card.
     */
    public ThunderclapCard() {
        super("Thunderclap", "Deal 4 damage and apply 1 Vulnerable to all enemies.", 1, 4, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            player.dealDamage(gameContext, getDamage(gameContext), enemy, this);
            enemy.addEffect(new VulnerableDebuff(), 1);
        }

        player.decreaseCurrentEnergy(getCost());
    }
}
