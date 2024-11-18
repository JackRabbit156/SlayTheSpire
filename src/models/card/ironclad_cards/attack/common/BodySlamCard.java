package models.card.ironclad_cards.attack.common;

import helper.PathAssistent;
import models.battle.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Body slam Karte.
 * @author OF Daniel Willig
 */
public class BodySlamCard extends AttackCard {
    /**
     * Constructor BodySlamCard.
     */
    public BodySlamCard() {
        super("Body Slam", "Deal damage equal to your Block.", 1, 0, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage(gameContext));

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }


    @Override
    public int dealDamage() {
        return 0;
    }

    /**
     * Deal damage int.
     *
     * @param gameContext the game context
     * @return the int
     */
    public int dealDamage(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        return player.getBlock();
    }
}
