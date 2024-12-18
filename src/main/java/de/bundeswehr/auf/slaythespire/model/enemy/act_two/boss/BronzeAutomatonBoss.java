package de.bundeswehr.auf.slaythespire.model.enemy.act_two.boss;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Boss;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.bronze_automaton.BoostEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.bronze_automaton.FlailEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.bronze_automaton.HyperBeamEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class BronzeAutomatonBoss extends Boss {

    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     */
    public BronzeAutomatonBoss() {
        super("Bronze Automaton", 300, 320);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new BoostEnemyCard());
        deck.add(new FlailEnemyCard());
        deck.add(new HyperBeamEnemyCard());

        setEnemyDeck(deck);
    }

}
