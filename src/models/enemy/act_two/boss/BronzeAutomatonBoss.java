package models.enemy.act_two.boss;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_one.cultist_enemy_cards.DarkStrikeEnemyCard;
import models.enemy_card.act_two.boss.bronze_automaton_boss_cards.BoostEnemyCard;
import models.enemy_card.act_two.boss.bronze_automaton_boss_cards.FlailEnemyCard;
import models.enemy_card.act_two.boss.bronze_automaton_boss_cards.HyperBeamEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
