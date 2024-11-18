package models.potion;

import models.battle.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.potion.potion_structure.PotionCard;

import java.util.List;

public class ExplosivePotion extends PotionCard {

    public ExplosivePotion() {
        super("Explosive Potion", "Deal 10 Damage to all enemies.", 0, CardRarity.POTION, CardGrave.POTION);
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> enemies = gameContext.getEnemies();
        for (Enemy enemy : enemies) {
            enemy.takeDamage(10);
        }
    }
}
