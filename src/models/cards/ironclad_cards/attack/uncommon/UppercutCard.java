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
 * Die Uppercut Karte.
 *
 * @author OF Daniel Willig
 */
public class UppercutCard extends AttackCard {
    /**
     * Constructor Uppercut card.
     */
    public UppercutCard() {
        super("Uppercut", "Deal 13 damage. Apply 1 Weak.Apply 1 Vulnerable.", 2, 13, CardRarity.UNCOMMON, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = ConsoleAssistent.scannerAutoAim(gameContext.getEnemies().size());

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO Weak, Vulnerable
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
