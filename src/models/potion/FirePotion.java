package models.potion;

import helper.ConsoleAssistent;
import models.battle.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.potion.potion_structure.PotionCard;

public class FirePotion extends PotionCard {

    public FirePotion() {
        super("Fire Potion", "Deal 20 Damage to target enemy.", 0, CardRarity.POTION, CardGrave.POTION);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = ConsoleAssistent.scannerAutoAim(gameContext.getEnemies().size());

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(20);
    }
}
