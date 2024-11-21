package models.enemy.act_two.elites;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class GremlinLeader extends Enemy {
    private Random randi = new Random();

    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     */
    public GremlinLeader() {
        super("Gremlin Leader", 140, 148);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        switch (randi.nextInt(2)) {
            case 0: attackStab(gameContext); break;
            default: attackEncourage(gameContext);
        }
    }

    private void attackEncourage(GameContext gameContext){
        int block = 6;
        for (Enemy enemy : gameContext.getEnemies()) {
            enemy.addBlock(block);
        }

        System.out.printf("%s used %s and gained %d Block!\n\n", getName(), "Encourage", block);
    }

    private void attackStab(GameContext gameContext){
        int attackDamage = 18;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Stab", player.getName(), attackDamage);
    }
}
