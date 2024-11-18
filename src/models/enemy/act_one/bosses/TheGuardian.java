package models.enemy.act_one.bosses;

import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class TheGuardian extends Enemy {
    // https://slay-the-spire.fandom.com/wiki/The_Guardian
    public TheGuardian() {
        super("The Guardian", 240, 240);
    }
    private Random randi = new Random();


    @Override
    public void attack(GameContext gameContext) {
        switch (randi.nextInt(2)) {
            case 0:
                atttackFierceBash(gameContext);
            default:
                atttackWhirlwind(gameContext);
        }
    }

    private void atttackFierceBash(GameContext gameContext){
        int attackDamage = 32;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }

    private void atttackWhirlwind(GameContext gameContext){
        int attackDamage = 20;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }
}
