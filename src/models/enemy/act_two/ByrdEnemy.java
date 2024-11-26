package models.enemy.act_two;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_one.cultist_enemy_cards.DarkStrikeEnemyCard;
import models.enemy_card.act_two.byrd_enemy_cards.HeadbuttEnemyCard;
import models.enemy_card.act_two.byrd_enemy_cards.PeckEnemyCard;
import models.enemy_card.act_two.byrd_enemy_cards.SwoopEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;
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
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new PeckEnemyCard());
        deck.add(new SwoopEnemyCard());
        deck.add(new HeadbuttEnemyCard());

        setEnemyDeck(deck);
    }

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }

}