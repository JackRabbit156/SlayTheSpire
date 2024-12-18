package de.bundeswehr.auf.slaythespire.model.enemy.act_one.elite;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Elite;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.elite.gremlin_nob.RushEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.elite.gremlin_nob.SkullBashEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class GremlinNobElite extends Elite {

    public GremlinNobElite() {
        super("Gremlin Nob", 82, 86);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new RushEnemyCard());
        deck.add(new SkullBashEnemyCard());

        setEnemyDeck(deck);
    }

}
