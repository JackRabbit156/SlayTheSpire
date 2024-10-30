package models.cards.ironclad_cards.attack.common;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SwordBoomerangCard extends AttackCard {
    public SwordBoomerangCard() {
        super("Sword Boomerang", "Deal 3 damage to a random enemy 3 times.", 1, 3, CardRarity.COMMON, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        Random rand = new Random();
        int targetIndex = rand.nextInt(allEnemies.size());

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        for (int i = 0; i < 3; i++) {
            enemy.takeDamage(dealDamage());
        }

        Player player = gameContext.getPlayer();
        player.loseEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
