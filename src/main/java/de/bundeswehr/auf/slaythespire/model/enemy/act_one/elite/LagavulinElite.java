package de.bundeswehr.auf.slaythespire.model.enemy.act_one.elite;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Elite;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.elite.lagavulin.AttackEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class LagavulinElite extends Elite {

    public LagavulinElite() {
        super("Lagavulin", 109, 111);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new AttackEnemyCard());

        setEnemyDeck(deck);
    }

}
