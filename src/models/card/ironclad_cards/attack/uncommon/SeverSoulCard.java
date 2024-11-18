package models.card.ironclad_cards.attack.uncommon;

import helper.PathAssistent;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.GameContext;
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
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
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
