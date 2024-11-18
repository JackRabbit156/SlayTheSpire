package models.cards.ironclad_cards.attack.uncommon;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.battle.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Sever soul Karte.
 *
 * @author OF Daniel Willig
 */
public class SeverSoulCard extends AttackCard {
    /**
     * Constructor Sever soul card.
     */
    public SeverSoulCard() {
        super("Sever Soul", "Exhaust all non-Attack cards in your hand. Deal 16 damage.", 2, 16, CardRarity.UNCOMMON, CardGrave.DISCARD);
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

        //TODO hand, for (!AttackCards) exhaust
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
