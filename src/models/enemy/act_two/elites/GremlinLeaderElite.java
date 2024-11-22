package models.enemy.act_two.elites;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_two.elites.gremlin_leader_elite_cards.EncourageEnemyCard;
import models.enemy_card.act_two.elites.gremlin_leader_elite_cards.StabGEnemyCard;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * @author Keil, Vladislav
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
    }

    @Override
    public void attack(GameContext gameContext) {
        switch (randi.nextInt(2)) {
            case 0: new StabGEnemyCard().play(gameContext); break;
            default: new EncourageEnemyCard().play(gameContext, this);
        }
    }
}
