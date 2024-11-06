package models.cards.ironclad_cards.attack.common;

import helper.ConsoleAssistent;
import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

/**
 * Die Pommel strike Karte.
 *
 * @author OF Daniel Willig
 */
public class PommelStrikeCard extends AttackCard {
    /**
     * Constructor Pommel strike card.
     */
    public PommelStrikeCard() {
        super("Pommel Strike", "Deal 9 damage. Draw 1 card.", 1, 9, CardRarity.COMMON, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = ConsoleAssistent.scannerAutoAim(gameContext.getEnemies().size());

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(1);
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
