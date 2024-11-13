package models.card.ironclad_cards.attack.uncommon;

import helper.PathAssistent;
import models.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Reckless charge Karte.
 *
 * @author OF Daniel Willig
 */
public class RecklessChargeCard extends AttackCard {
    /**
     * Constructor Reckless charge card.
     */
    public RecklessChargeCard() {
        super("Reckless Charge", "Deal 7 damage. Shuffle a Dazed into your draw pile.", 0, 7, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO deck.add dazed
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
