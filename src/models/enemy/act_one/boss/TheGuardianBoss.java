package models.enemy.act_one.boss;

import models.battle.GameContext;
import helper.PathAssistent;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class TheGuardianBoss extends Enemy {
    // https://slay-the-spire.fandom.com/wiki/The_Guardian
    public TheGuardianBoss() {
        super("The Guardian", 240, 240);
        setImagePath(new PathAssistent().toPath(this));
    }
    private Random randi = new Random();


    @Override
    public void attack(GameContext gameContext) {
        switch (randi.nextInt(3)) {
            case 0:  attackFierceBash(gameContext); break;
            case 1: attackChargingUp(); break;
            default: attackWhirlwind(gameContext);
        }
    }

    private void attackChargingUp(){
        int block = 9;
        this.addBlock(15);

        System.out.printf("%s used %s and gained %d Block!\n\n", getName(), "Charging Up", block);
    }

    private void attackFierceBash(GameContext gameContext){
        int attackDamage = 32;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }

    private void attackWhirlwind(GameContext gameContext){
        int attackDamage = 20;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }
}
