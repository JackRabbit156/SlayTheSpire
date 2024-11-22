package models.enemy.act_two.boss;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_two.boss.bronze_automaton_boss_cards.BoostEnemyCard;
import models.enemy_card.act_two.boss.bronze_automaton_boss_cards.FlailEnemyCard;
import models.enemy_card.act_two.boss.bronze_automaton_boss_cards.HyperBeamEnemyCard;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class BronzeAutomatonBoss extends Enemy {
    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     */
    public BronzeAutomatonBoss( ) {
        super("Bronze Automaton", 300, 320);
        setImagePath(new PathAssistent().toPath(this));
    }


    @Override
    public void attack(GameContext gameContext) {
        Random randi = new Random();
        int randomAttack = randi.nextInt(2);

        switch (randomAttack) {
            case 0: new FlailEnemyCard().play(gameContext); break;
            case 1: new HyperBeamEnemyCard().play(gameContext); break;
            default: new BoostEnemyCard().play(gameContext, this);
        }
    }
}
