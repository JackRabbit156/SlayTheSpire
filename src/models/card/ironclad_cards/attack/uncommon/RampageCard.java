package models.card.ironclad_cards.attack.uncommon;

import helper.PathAssistent;
import models.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Rampage Karte.
 *
 * @author OF Daniel Willig
 */
public class RampageCard extends AttackCard {
    /**
     * Constructor Rampage card.
     */
    public RampageCard() {
        super("Rampage", "Deal 8 damage. Increase this card's damage by 5 this combat.", 1, 8, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO I don't know how to save how many times it was used THIS combat
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
