package models.cards.ironclad_cards.attack.rare;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.battle.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Fiend fire Karte.
 *
 * @author OF Daniel Willig
 */
public class FiendFireCard extends AttackCard {
    /**
     * Constructor Fiend fire card.
     */
    public FiendFireCard() {
        super("Fiend Fire", "Exhaust all cards in your hand. Deal 7 damage for each Exhausted card.Exhaust.", 2, 7, CardRarity.RARE, CardGrave.EXHAUST);
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

        //TODO Exhaust for
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
