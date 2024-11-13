package models.potion;

import helper.PathAssistent;
import models.GameContext;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.potion.potion_structure.PotionCard;

import java.util.List;

public class ExplosivePotion extends PotionCard {

    public ExplosivePotion() {
        super("Explosive Potion", "Deal 10 Damage to all enemies.", 0, CardRarity.POTION, CardGrave.POTION);
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
