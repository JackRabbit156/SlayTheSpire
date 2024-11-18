package models.enemy.act_one.bosses;

import helper.PathAssistent;
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
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        attackSlam(gameContext);
    }

    private void attackSlam(GameContext gameContext){
        int attackDamage = 35;
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!%n", getName(), "Slam", player.getName(), attackDamage);
    }
}
