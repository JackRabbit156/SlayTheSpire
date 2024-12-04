package models.enemy.act_two.elite;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_two.elite.book_of_stabbing.MultiStabEnemyCard;
import models.enemy_card.act_two.elite.book_of_stabbing.SingleStabEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class BookOfStabbingElite extends Enemy {
    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     */
    public BookOfStabbingElite() {
        super("Book of Stabbing",160, 162);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new MultiStabEnemyCard());
        deck.add(new SingleStabEnemyCard());

        setEnemyDeck(deck);
    }

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}
