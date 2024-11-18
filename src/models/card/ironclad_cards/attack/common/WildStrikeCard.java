package models.card.ironclad_cards.attack.common;

import helper.PathAssistent;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Wild strike Karte.
 *
 * @author OF Daniel Willig
 */
public class WildStrikeCard extends AttackCard {
    /**
     * Constructor Wild strike card.
     */
    public WildStrikeCard() {
        super("Wild Strike", "Deal 12 damage. Shuffle a Wound into your draw pile.", 1, 12, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
// TODO - Sobald Wound existiert einfügen
//        Wound wound = new Wound();
//        player.getDeck().add(wound);
//        o.ä.
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
