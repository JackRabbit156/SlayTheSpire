package models.cards.ironclad_cards.attack.common;

import helper.ConsoleAssistent;
import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

/**
 * Bash Karte.
 * @author OF Daniel Willig
 */
public class BashCard extends AttackCard {
    /**
     * Constructor BashCard
     */
    public BashCard() {
        super("Bash", "Deal 2 Damage. Apply 2 Vulnerable.", 2, 8, CardRarity.COMMON, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = ConsoleAssistent.scannerInt();

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
        //TODO Apply Debuff 2 Vulnerable
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}