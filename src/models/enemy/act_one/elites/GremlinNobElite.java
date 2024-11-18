package models.enemy.act_one.elites;

import helper.PathAssistent;
import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;
import java.util.Random;
/**
 * @author Keil, Vladislav
 */
public class GremlinNobElite extends Enemy {
    public GremlinNobElite() {
        super("Gremlin Nob", 82, 86);
        setImagePath(new PathAssistent().toPath(this));
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
