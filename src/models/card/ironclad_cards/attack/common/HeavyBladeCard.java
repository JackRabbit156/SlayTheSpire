package models.card.ironclad_cards.attack.common;

import helper.PathAssistent;
import models.battle.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
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
        Enemy enemy = gameContext.getSelectedEnemy();
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
