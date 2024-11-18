package models.enemy.act_one.elites;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * @author Keil, Vladislav
 */
public class LagavulinElite extends Enemy {
    public LagavulinElite() {
        super("Lagavulin", 109, 111);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        int attackDamage = 18;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }
}
