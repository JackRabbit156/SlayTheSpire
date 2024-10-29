package models.cards.ironclad_cards.attack.common;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;
import java.util.Scanner;

public class ThunderclapCard extends AttackCard {
    public ThunderclapCard() {
        super("Thunderclap", "Deal 4 damage and apply 1 Vulnerable to ALL enemies.", 1, 4, CardRarity.COMMON);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy allEnemy : allEnemies) {
            //TODO allEnemy.getEffect Vulnerable
        }

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.loseEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
