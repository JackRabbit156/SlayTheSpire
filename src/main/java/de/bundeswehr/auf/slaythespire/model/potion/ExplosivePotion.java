package de.bundeswehr.auf.slaythespire.model.potion;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardType;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.potion.structure.PotionCard;

import java.util.List;

/**
 * Die Explosive potion.
 *
 * @author OF Daniel Willig
 */
public class ExplosivePotion extends PotionCard {

    /**
     * Constructor Explosive potion.
     */
    public ExplosivePotion() {
        super("Explosive Potion", "Deal 10 Damage to all enemies.",  CardRarity.COMMON,  CardType.ATTACK);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> enemies = gameContext.getEnemies();
        for (Enemy enemy : enemies) {
            enemy.takeDamage(10);
        }
    }
}
