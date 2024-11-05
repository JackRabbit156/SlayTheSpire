package models.cards.ironclad_cards.attack.uncommon;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

/**
 * Die Rampage Karte.
 *
 * @author OF Daniel Willig
 */
public class RampageCard extends AttackCard {
    /**
     * Constructor Rampage card.
     */
    public RampageCard() {
        super("Rampage", "Deal 8 damage. Increase this card's damage by 5 this combat.", 1, 8, CardRarity.UNCOMMON, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO I don't know how to save how many times it was used THIS combat
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
