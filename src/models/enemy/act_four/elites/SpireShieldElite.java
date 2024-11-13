package models.enemy.act_four.elites;

import helper.PathAssistent;
import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * @author Keil, Vladislav
 */
public class SpireShieldElite extends Enemy {
    public SpireShieldElite() {
        super("Spire Shield",160, 160);
        setImagePath(new PathAssistent().toPath(this));
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
