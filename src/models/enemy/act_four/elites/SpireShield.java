package models.enemy.act_four.elites;

import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * @author Keil, Vladislav
 */
public class SpireShield extends Enemy {
    public SpireShield() {
        super("Spire Shield",160, 160);
    }

    @Override
    public void attack(GameContext gameContext) {
        attackSmash(gameContext);
    }

    private void attackSmash(GameContext gameContext){
        int attackDamage = 34;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }
}
