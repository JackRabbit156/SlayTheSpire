package de.bundeswehr.auf.slaythespire.model.enemy.act_four.elite;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Elite;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.elite.spire_spear.BurnStrikeEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.elite.spire_spear.PiercerEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.elite.spire_spear.SkewerEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class SpireSpearElite extends Elite {

    public SpireSpearElite() {
        super("Spire Spear", 160, 160);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new BurnStrikeEnemyCard());
        deck.add(new PiercerEnemyCard());
        deck.add(new SkewerEnemyCard());

        setEnemyDeck(deck);
    }

}
