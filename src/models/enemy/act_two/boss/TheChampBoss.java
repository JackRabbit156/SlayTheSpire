package models.enemy.act_two.boss;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_two.boss.the_champ_boss_cards.DefensiveStanceEnemyCard;
import models.enemy_card.act_two.boss.the_champ_boss_cards.ExecuteEnemyCard;
import models.enemy_card.act_two.boss.the_champ_boss_cards.FaceSlapEnemyCard;
import models.enemy_card.act_two.boss.the_champ_boss_cards.HeavySlashEnemyCard;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class TheChampBoss extends Enemy {
    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     */
    public TheChampBoss() {
        super("The Champ", 420, 440);
        setImagePath(new PathAssistent().toPath(this));

    }

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}
