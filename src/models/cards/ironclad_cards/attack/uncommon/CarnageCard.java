package models.cards.ironclad_cards.attack.uncommon;

import helper.ConsoleAssistent;
import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

/**
 * Die Carnage Karte.
 *
 * @author OF Daniel Willig
 */
public class CarnageCard extends AttackCard {
    /**
     * Constructor Carnage card.
     */
    public CarnageCard() {
        super("Carnage", "Ethereal. Deal 20 damage.", 2, 20, CardRarity.UNCOMMON, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = ConsoleAssistent.scannerAutoAim(gameContext.getEnemies().size());

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO Ethereal
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
