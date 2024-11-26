package models.enemy.act_two.elites;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_one.cultist_enemy_cards.DarkStrikeEnemyCard;
import models.enemy_card.act_two.elites.gremlin_leader_elite_cards.EncourageEnemyCard;
import models.enemy_card.act_two.elites.gremlin_leader_elite_cards.StabGEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class GremlinLeaderElite extends Enemy {
    private Random randi = new Random();

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

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}
