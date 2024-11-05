package models.cards.ironclad_cards.attack.uncommon;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

/**
 * Die Hemokinesis Karte.
 *
 * @author OF Daniel Willig
 */
public class HemokinesisCard extends AttackCard {
    /**
     * Constructor Hemokinesis card.
     */
    public HemokinesisCard() {
        super("Hemokinesis", "Lose 2 HP. Deal 15 damage.", 1, 15, CardRarity.UNCOMMON, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        player.decreaseCurrentHealth(2, true);
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
