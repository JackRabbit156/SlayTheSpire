package models.cards.silent_cards;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.battle.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Neutralize Karte.
 *
 * @author OF Daniel Willig
 */
public class NeutralizeCard extends AttackCard {
    /**
     * Constructor Neutralize card.
     */
    public NeutralizeCard() {
        super("Neutralize", "Deal 3 damage. Apply 1 Weak.", 0, 3, CardRarity.COMMON, CardGrave.DISCARD);
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

        //TODO Apply Debuff 1 Weak
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}