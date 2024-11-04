package models.enemy.act_one.elites;

import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

public class Lagavulin extends Enemy {
    public Lagavulin() {
        super("Lagavulin", 109, 111);
    }

    @Override
    public void attack(GameContext gameContext) {
        int attackDamage = 18;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }
}
