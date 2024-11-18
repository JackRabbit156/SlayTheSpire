package models.enemy.act_four;

import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;


/**
 * @author Keil, Vladislav
 */
public class Shapes extends Enemy {
    public Shapes() {
        super("Shapes",42, 56);
    }

    @Override
    public void attack(GameContext gameContext) {
        attackCut(gameContext);
    }

    private void attackCut(GameContext gameContext){
        int attackDamage = 7;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }
}
