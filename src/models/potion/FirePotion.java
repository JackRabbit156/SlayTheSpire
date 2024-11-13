package models.potion;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.GameContext;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.potion.potion_structure.PotionCard;

public class FirePotion extends PotionCard {

    public FirePotion() {
        super("Fire Potion", "Deal 20 Damage to target enemy.", 0, CardRarity.POTION, CardGrave.POTION);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = ConsoleAssistent.scannerAutoAim(gameContext.getEnemies().size());

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(20);
    }
}
