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
 * Die Searing blow Karte.
 *
 * @author OF Daniel Willig
 */
public class SearingBlowCard extends AttackCard {
    /**
     * Constructor Searing blow card.
     */
    public SearingBlowCard() {
        super("Searing Blow", "Deal 12 damage. Can be Upgraded any number of times.", 2, 12, CardRarity.UNCOMMON, CardGrave.DISCARD);
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

        //TODO upgrading
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
