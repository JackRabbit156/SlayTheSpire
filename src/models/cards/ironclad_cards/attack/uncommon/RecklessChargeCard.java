package models.cards.ironclad_cards.attack.uncommon;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

/**
 * Die Reckless charge Karte.
 *
 * @author OF Daniel Willig
 */
public class RecklessChargeCard extends AttackCard {
    /**
     * Constructor Reckless charge card.
     */
    public RecklessChargeCard() {
        super("Reckless Charge", "Deal 7 damage. Shuffle a Dazed into your draw pile.", 0, 7, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = ConsoleAssistent.scannerAutoAim(gameContext.getEnemies().size());

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO deck.add dazed
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
