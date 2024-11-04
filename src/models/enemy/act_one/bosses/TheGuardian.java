package models.enemy.act_one.bosses;

import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

public class TheGuardian extends Enemy {
    // https://slay-the-spire.fandom.com/wiki/The_Guardian
    public TheGuardian() {
        super("The Guardian", 240, 240);
    }
    //TODO KANN Erstellung des Bosses

    @Override
    public void attack(GameContext gameContext) {
        //System.out.printf("%s used ");

//        atttackCorrosiveSpit(gameContext);
    }


//    private void atttackCorrosiveSpit(GameContext gameContext){
//        int attackDamage = 11;
//        Player player = gameContext.getPlayer();
//
//        player.decreaseCurrentHealth(attackDamage, false);
//
//    }
}
