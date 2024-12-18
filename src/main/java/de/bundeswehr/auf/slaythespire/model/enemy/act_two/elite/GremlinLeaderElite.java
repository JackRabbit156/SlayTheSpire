package de.bundeswehr.auf.slaythespire.model.enemy.act_two.elite;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Elite;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.elite.gremlin_leader.EncourageEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.elite.gremlin_leader.StabGEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class GremlinLeaderElite extends Elite {

    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     */
    public GremlinLeaderElite() {
        super("Gremlin Leader", 140, 148);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new EncourageEnemyCard());
        deck.add(new StabGEnemyCard());

        setEnemyDeck(deck);
    }

}
