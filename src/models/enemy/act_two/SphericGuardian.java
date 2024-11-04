package models.enemy.act_two;

import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Random;

public class SphericGuardian extends Enemy {
    public SphericGuardian() {
        super("Spheric Guardian", 20, 20);
        setBlock(40);
    }


    @Override
    public void attack(GameContext gameContext) {
        Random randi = new Random();
        int randomAttack = randi.nextInt(3);

        switch (randomAttack){
            case 0: attackSlam(gameContext); break;
            case 1: abilityActivate(gameContext); break;
            case 2: attackHarden(gameContext); break;
        }
    }

    /**
     *  Name of the attack "Slam"
     * @param gameContext
     */
    private void attackSlam(GameContext gameContext){
        int attackDamage = 20;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Slam", player.getName(), attackDamage);
    }

    /**
     *  Name of the attack "Activate"
     * @param gameContext
     */
    private void abilityActivate(GameContext gameContext){
        int block = 25;
        this.addBlock(block);
        System.out.printf("%s used %s and gained %d Block!\n\n", getName(), "Activate", block);
    }

    /**
     * Name of the attack "Harden"
     * Deals 10 damage to the player and adds 25 block to the enemy
     * @param gameContext important Information for the Enemy to be able to do his abilities
     */
    private void attackHarden(GameContext gameContext){
        int attackDamage = 10;
        int block = 15;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage);

        this.addBlock(15);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Harden", player.getName(), attackDamage);
        System.out.printf("%s used %s and gained %d Block!\n\n", getName(), "Harden", block);
    }
}
