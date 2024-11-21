package models.enemy.act_two.boss;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class TheChamp extends Enemy {
    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     */
    public TheChamp() {
        super("The Champ", 420, 440);
        setImagePath(new PathAssistent().toPath(this));

    }

    @Override
    public void attack(GameContext gameContext) {
        Random randi = new Random();
        int randomAttack = randi.nextInt(3);

        switch (randomAttack) {
            case 0: attackExecute(gameContext); break;
            case 1: attackFaceSlap(gameContext); break;
            case 2: attackHeavySlash(gameContext); break;
            default:  attackDefensiveStance();
        }
    }

    private void attackDefensiveStance() {
        int block = 15;
        this.setBlock(block);

        System.out.printf("%s used %s and gained %d Block!\n\n", getName(), "Defensive Stance", block);
    }

    private void attackFaceSlap(GameContext gameContext) {
        int attackDamage = 12;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "FaceSlap", player.getName(), attackDamage);
    }


    private void attackHeavySlash(GameContext gameContext) {
        int attackDamage = 16;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "HeavySlash", player.getName(), attackDamage);
    }

    private void attackExecute(GameContext gameContext) {
        int attackDamage = 20;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Execute", player.getName(), attackDamage);
    }
}
