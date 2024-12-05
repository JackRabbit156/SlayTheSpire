package de.bundeswehr.auf.slaythespire.models.enemy.act_two.boss;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.act_two.boss.bronze_automaton.BoostEnemyCard;
import de.bundeswehr.auf.slaythespire.models.enemy_card.act_two.boss.bronze_automaton.FlailEnemyCard;
import de.bundeswehr.auf.slaythespire.models.enemy_card.act_two.boss.bronze_automaton.HyperBeamEnemyCard;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class BronzeAutomatonBoss extends Enemy {
    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     */
    public BronzeAutomatonBoss( ) {
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


    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}
