package models.potion;

import helper.PathAssistent;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.GameContext;
import models.card.card_structure.CardType;
import models.enemy.Enemy;
import models.potion.potion_structure.PotionCard;

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
