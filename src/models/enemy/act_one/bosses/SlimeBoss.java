package models.enemy.act_one.bosses;

import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class SlimeBoss extends Enemy {
    // https://slay-the-spire.fandom.com/wiki/Slime_Boss
    private Random rand = new Random();

    public SlimeBoss() {
        super("Slime Boss", 150, 150);
    }

    @Override
    public void attack(GameContext gameContext) {
//      double healthPercent = (this.getMaxHealth() / this.getHealth());
//      if split is in
//        if (rand.nextDouble() < 0.30 && healthPercent < 0.50 ) {
//            split(gameContext);

        if (rand.nextDouble() < 0.50) {
            preparing();
        } else {
            attackSlam(gameContext);
        }
    }

    private void attackSlam(GameContext gameContext){
        int attackDamage = 35;
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!%n", getName(), "Slam", player.getName(), attackDamage);
    }
/*
    // Split in Acid Slime (L)
    // Kann - Splitting in 2 (L), die (L) müssen auf (M) splitten
    private void split(GameContext gameContext){
        gameContext.getEnemies().add(new AcidSlime());
        gameContext.getEnemies().add(new AcidSlime());
        System.out.printf("%s%n", "Slim Boss Split!");
        gameContext.getEnemies().remove(this);
    }
*/

    private void preparing(){
        System.out.printf("%s do %s!%n", getName(), "Nothing");
        // doNothing
    }
}
