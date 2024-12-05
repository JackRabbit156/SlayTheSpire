package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;

/**
 * Die Reaper Karte.
 *
 * @author OF Daniel Willig
 */
public class ReaperCard extends AttackCard {
    /**
     * Constructor Reaper card.
     */
    public ReaperCard() {
        super("Reaper", "Deal 4 damage to ALL enemies. Heal HP equal to unblocked damage. Exhaust.", 2, 4, CardRarity.RARE, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        int bonusHp = 0;
        for (Enemy enemy : allEnemies) {
            int enemyHealthOld = enemy.getHealth();
            enemy.takeDamage(dealDamage());
            if (enemy.getHealth() < enemyHealthOld) {
                bonusHp += (enemyHealthOld - enemy.getHealth());
            }
        }

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        player.increaseCurrentHealth(bonusHp);
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
