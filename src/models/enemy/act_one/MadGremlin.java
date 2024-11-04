package models.enemy.act_one;

import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

public class MadGremlin extends Enemy {
    public MadGremlin() {
        super("Mad Gremlin", 20, 24);
    }


    @Override
    public void attack(GameContext gameContext) {
        attackScratch(gameContext);
    }

    private void attackScratch(GameContext gameContext){
        int attackDamage = 4;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage,false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Scratch", player.getName(), attackDamage);
    }
}