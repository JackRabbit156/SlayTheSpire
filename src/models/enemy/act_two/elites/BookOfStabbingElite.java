package models.enemy.act_two.elites;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_two.elites.book_of_stabbing_elite_cards.MultiStabEnemyCard;
import models.enemy_card.act_two.elites.book_of_stabbing_elite_cards.SingleStabEnemyCard;
import models.player.player_structure.Player;

import java.util.Random;


/**
 * @author Keil, Vladislav
 */
public class BookOfStabbingElite extends Enemy {
    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     */
    public BookOfStabbingElite() {
        super("Book of Stabbing",160, 162);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        Random randi = new Random();
        int randomAttack = randi.nextInt(2);

        switch (randomAttack) {
            case 0: new MultiStabEnemyCard().play(gameContext); break;
            case 1: new SingleStabEnemyCard().play(gameContext); break;
        }
    }
}
