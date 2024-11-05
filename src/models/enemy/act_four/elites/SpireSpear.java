package models.enemy.act_four.elites;

import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * @author Keil, Vladislav
 */
public class SpireSpear extends Enemy {
    public SpireSpear() {
        super("Spire Spear",160, 160);
    }

    @Override
    public void attack(GameContext gameContext) {
        attackSkewer(gameContext);
    }

    private void attackSkewer(GameContext gameContext){
        int attackDamage = 30;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }
}
