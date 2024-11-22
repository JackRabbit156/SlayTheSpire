package models.enemy.act_two.boss;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class BronzeAutomaton extends Enemy {
    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     */
    public BronzeAutomaton( ) {
        super("Bronze Automaton", 300, 320);
        setImagePath(new PathAssistent().toPath(this));
    }


    @Override
    public void attack(GameContext gameContext) {
        Random randi = new Random();
        int randomAttack = randi.nextInt(2);

        switch (randomAttack) {
            case 0: attackFlail(gameContext); break;
            case 1: attackHyperBeam(gameContext); break;
            default: attackBoost();
        }
    }

    private void attackBoost() {
        int block = 9;
        this.setBlock(block);

        System.out.printf("%s used %s and gained %d Block!\n\n", getName(), "Encourage", block);
    }

    private void attackFlail(GameContext gameContext) {
        int attackDamage = 14;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Flail", player.getName(), attackDamage);
    }


    private void attackHyperBeam(GameContext gameContext) {
        int attackDamage = 45;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "HyperBeam", player.getName(), attackDamage);
    }

}
