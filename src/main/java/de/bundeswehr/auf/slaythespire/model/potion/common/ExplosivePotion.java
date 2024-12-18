package de.bundeswehr.auf.slaythespire.model.potion.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.potion.structure.AttackPotion;

import java.util.List;

/**
 * Die Explosive potion.
 *
 * @author OF Daniel Willig
 */
public class ExplosivePotion extends AttackPotion {

    /**
     * Constructor Explosive potion.
     */
    public ExplosivePotion() {
        super("Explosive Potion", "Deal 10 Damage to all enemies.", 10, CardRarity.COMMON);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> enemies = gameContext.getEnemies();
        for (Enemy enemy : enemies) {
            enemy.takeDamage(getDamage());
        }
    }

}
