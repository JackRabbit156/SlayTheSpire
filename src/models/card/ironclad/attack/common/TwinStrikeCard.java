package models.card.ironclad.attack.common;

import helper.PathAssistent;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Twin strike Karte.
 *
 * @author OF Daniel Willig
 */
public class TwinStrikeCard extends AttackCard {
    /**
     * Constructor Twin strike card.
     */
    public TwinStrikeCard() {
        super("Twin Strike", "Deal 5 damage twice.", 1, 5, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        for (int i = 0; i < 2; i++) {
            enemy.takeDamage(dealDamage());
        }

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
