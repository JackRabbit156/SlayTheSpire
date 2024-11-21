package models.enemy.act_four.elites;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * @author Keil, Vladislav
 */
public class SpireSpearElite extends Enemy {
    public SpireSpearElite() {
        super("Spire Spear",160, 160);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        attackSkewer(gameContext);
    }

    private void attackSkewer(GameContext gameContext){
        int attackDamage = 30;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Skewer", player.getName(), attackDamage);
    }
}
