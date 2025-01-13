package de.bundeswehr.auf.slaythespire.model.enemy.act_four.elite;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Elite;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.elite.spire_shield.BashEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.elite.spire_shield.FortifyEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.elite.spire_shield.SmashEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class SpireShieldElite extends Elite {

    public SpireShieldElite() {
        super("Spire Shield", 160, 160);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new BashEnemyCard());
        deck.add(new FortifyEnemyCard());
        deck.add(new SmashEnemyCard());

        setEnemyDeck(deck);
    }

}
