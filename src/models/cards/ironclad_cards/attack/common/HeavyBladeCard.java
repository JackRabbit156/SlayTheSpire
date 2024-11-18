package models.cards.ironclad_cards.attack.common;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.battle.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Heavy blade Karte.
 * @author OF Daniel Willig
 */
public class HeavyBladeCard extends AttackCard {
    /**
     * Constructor HeavyBladeCard
     */
    public HeavyBladeCard() {
        super("Heavy Blade", "Deal 14 damage. Strength affects this card 3 times.", 2, 14, CardRarity.COMMON, CardGrave.DISCARD);
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

        //TODO Buff Strength affects this card 3 times
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
