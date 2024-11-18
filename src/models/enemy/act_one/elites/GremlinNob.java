package models.enemy.act_one.elites;

import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;
import java.util.Random;
/**
 * @author Keil, Vladislav
 */
public class GremlinNob extends Enemy {
    public GremlinNob() {
        super("Gremlin Nob", 82, 86);
    }
    private Random randi = new Random();

    @Override
    public void attack(GameContext gameContext) {
        switch (randi.nextInt(2)) {
            case 0:
                attackRush(gameContext);
            default:
                attackSkullBash(gameContext);
        }
    }

    private void attackRush(GameContext gameContext) {
        int attackDamage = 14;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }

    private void attackSkullBash(GameContext gameContext) {
        int attackDamage = 6;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }
}
