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
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}
