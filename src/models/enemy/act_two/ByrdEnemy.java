package models.enemy.act_two;

import helper.PathAssistent;
import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * Diese Klasse repräsentiert den Gegner "Byrd" im zweiten Akt des Spiels.
 * Byrd hat spezifische Angriffsfähigkeiten, die bei jedem
 * Angriff zufällig ausgewählt werden.
 *
 * @author Warawa Alexander
 */
public class ByrdEnemy extends Enemy {
    public ByrdEnemy() {
        super("Byrd", 25, 31);
        setImagePath(new PathAssistent().toPath(this));
        setBlock(40);
    }

    @Override
    public void attack(GameContext gameContext) {
        Random randi = new Random();
        int randomAttack = randi.nextInt(3);

        switch (randomAttack){
            case 0: attackPeck(gameContext); break;
            case 1: attackSwoop(gameContext); break;
            case 2: attackHeadbutt(gameContext); break;
        }
    }

    private void attackPeck(GameContext gameContext){
        int attackDamage = 5;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Peck", player.getName(), attackDamage);
    }

    private void attackSwoop(GameContext gameContext){
        int attackDamage = 12;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Swoop", player.getName(), attackDamage);
    }

    private void attackHeadbutt(GameContext gameContext){
        int attackDamage = 3;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Headbutt", player.getName(), attackDamage);
    }

}