package models.potion;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.GameContext;
import models.card.card_structure.CardType;
import models.enemy.Enemy;
import models.potion.potion_structure.PotionCard;

/**
 * Die Fire potion.
 *
 * @author OF Daniel Willig
 */
public class FirePotion extends PotionCard {

    /**
     * Constructor Fire potion.
     */
    public FirePotion() {
        super("Fire Potion", "Deal 20 Damage to target enemy.",  CardRarity.COMMON,  CardType.ATTACK);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(20);
    }
}
