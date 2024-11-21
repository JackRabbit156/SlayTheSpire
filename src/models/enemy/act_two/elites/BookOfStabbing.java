package models.enemy.act_two.elites;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Random;


/**
 * @author Keil, Vladislav
 */
public class BookOfStabbing  extends Enemy {
    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     */
    public BookOfStabbing() {
        super("Book of Stabbing",160, 162);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        Random randi = new Random();
        int randomAttack = randi.nextInt(2);

        switch (randomAttack) {
            case 0:
                attackMultiStab(gameContext);
                break;
            case 1:
                attackSingleStab(gameContext);
                break;
        }
    }

    private void attackMultiStab(GameContext gameContext) {
        Random randi = new Random();
        int randomAttack = randi.nextInt(3);

        int attackDamage = 6 * randomAttack;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Multi-Stab", player.getName(), attackDamage);
    }


    private void attackSingleStab(GameContext gameContext) {
        int attackDamage = 21;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Single Stab", player.getName(), attackDamage);
    }
}
