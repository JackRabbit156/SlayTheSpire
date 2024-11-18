package models.enemy.act_four;

import helper.PathAssistent;
import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;


/**
 * @author Keil, Vladislav
 */
public class SpikerEnemy extends Enemy {
    public SpikerEnemy() {
        super("Shapes",42, 56);
        setImagePath(new PathAssistent().toPath(this));
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
