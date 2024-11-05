package models.cards.ironclad_cards.attack.rare;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

/**
 * Die Feed Karte.
 *
 * @author OF Daniel Willig
 */
public class FeedCard extends AttackCard {
    /**
     * Constructor Feed card.
     */
    public FeedCard() {
        super("Feed", "Deal 10 damage. If Fatal, raise your Max HP by 3. Exhaust.", 1, 10, CardRarity.RARE, CardGrave.EXHAUST);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        if (!enemy.isAlive()) {
            player.increaseMaxHealth(3);
        }
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
