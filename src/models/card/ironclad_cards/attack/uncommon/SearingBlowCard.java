package models.card.ironclad_cards.attack.uncommon;

import helper.PathAssistent;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

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
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
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
