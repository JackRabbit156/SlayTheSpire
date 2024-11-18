package models.enemy.act_two;

import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * Diese Klasse repräsentiert den Gegner "Spheric Guardian" im zweiten Akt des Spiels.
 * Spheric Guardian hat spezifische Angriffsfähigkeiten, die bei jedem
 * Angriff zufällig ausgewählt werden.
 *
 * @author Warawa Alexander
 */
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
     *  Die Attacke heißt "Slam"
     * @param gameContext
     */
    private void attackSlam(GameContext gameContext){
        int attackDamage = 20;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Slam", player.getName(), attackDamage);
    }

    /**
     *  Die Attacke heißt "Activate"
     * @param gameContext
     */
    private void abilityActivate(GameContext gameContext){
        int block = 25;
        this.addBlock(block);
        System.out.printf("%s used %s and gained %d Block!\n\n", getName(), "Activate", block);
    }

    /**
     * Die Attacke heißt "Harden"
     * Fügt 10 Damage dem Spieler zu und gibt sich selbst 25 Block.
     * @param gameContext Informationen für den Kampf
     */
    private void attackHarden(GameContext gameContext){
        int attackDamage = 10;
        int block = 15;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);

        this.addBlock(15);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Harden", player.getName(), attackDamage);
        System.out.printf("%s used %s and gained %d Block!\n\n", getName(), "Harden", block);
    }
}
