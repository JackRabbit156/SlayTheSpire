package de.bundeswehr.auf.slaythespire.model.enemy.act_two.elite;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Elite;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.elite.book_of_stabbing.MultiStabEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.elite.book_of_stabbing.SingleStabEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class BookOfStabbingElite extends Elite {

    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     */
    public BookOfStabbingElite() {
        super("Book of Stabbing", 160, 162);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new MultiStabEnemyCard());
        deck.add(new SingleStabEnemyCard());

        setEnemyDeck(deck);
    }

}
