package models.enemy.act_one;

import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

public class Cultist extends Enemy {
    public Cultist() {
        super("Cultist", 48, 54);
    }

    @Override
    public void attack(GameContext gameContext) {
        attackDarkStrike(gameContext);
    }

    private void attackDarkStrike(GameContext gameContext){
        int attackDamage = 6;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }

}
