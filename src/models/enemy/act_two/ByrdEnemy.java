package models.enemy.act_two;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_two.byrd_enemy_cards.HeadbuttEnemyCard;
import models.enemy_card.act_two.byrd_enemy_cards.PeckEnemyCard;
import models.enemy_card.act_two.byrd_enemy_cards.SwoopEnemyCard;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * Diese Klasse repräsentiert den Gegner "Byrd" im zweiten Akt des Spiels.
 * Byrd hat spezifische Angriffsfähigkeiten, die bei jedem
 * Angriff zufällig ausgewählt werden.
 *
 * @author Warawa Alexander
 * @author OF Daniel Willig
 */
public class ByrdEnemy extends Enemy {
    public ByrdEnemy() {
        super("Byrd", 25, 31);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }

}