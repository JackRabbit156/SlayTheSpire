package models.card.ironclad_cards.attack.uncommon;

import helper.PathAssistent;
import models.battle.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Dropkick Karte.
 *
 * @author OF Daniel Willig
 */
public class DropkickCard extends AttackCard {
    /**
     * Constructor Dropkick card.
     */
    public DropkickCard() {
        super("Dropkick", "Deal 5 damage. If the enemy has Vulnerable, gain Energy and draw 1 card.", 1, 5, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO if (enemy.hasEffect.Vulnerable) {player.increaseEnergy(1); player.drawCard(1);

    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
