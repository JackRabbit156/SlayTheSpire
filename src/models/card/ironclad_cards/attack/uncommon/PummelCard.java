package models.card.ironclad_cards.attack.uncommon;

import helper.PathAssistent;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Pummel Karte.
 *
 * @author OF Daniel Willig
 */
public class PummelCard extends AttackCard {
    /**
     * Constructor Pummel card.
     */
    public PummelCard() {
        super("Pummel", "Deal 2 damage 4 times. Exhaust.", 1, 2, CardRarity.UNCOMMON, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        for (int i = 0; i < 4; i++) {
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
