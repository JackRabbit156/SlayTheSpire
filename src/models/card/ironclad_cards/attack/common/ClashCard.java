package models.card.ironclad_cards.attack.common;

import helper.PathAssistent;
import models.battle.GameContext;
import models.card.card_structure.*;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Clash Karte.
 * @author OF Daniel Willig
 */
public class ClashCard extends AttackCard {
    /**
     * Constructor ClashCard.
     */
    public ClashCard() {
        super("Clash", "Can only be played if every card in your hand is an Attack. Deal 14 damage.", 0, 14, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());


        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
