package models.enemy.act_four.boss;

import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class CorruptHeart extends Enemy {
    private Random rand = new Random();

    public CorruptHeart() {
        super("CorruptHeart",750, 750);
    }

    @Override
    public void attack(GameContext gameContext) {
        switch (rand.nextInt(2)) {
            case 0:
                attackBloodShots(gameContext);
            case 1:
                attackEcho(gameContext);
        }
    }

    private void attackBloodShots(GameContext gameContext){
        int attackDamage = 24;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }

    private void attackEcho(GameContext gameContext){
        int attackDamage = 40;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }
}
