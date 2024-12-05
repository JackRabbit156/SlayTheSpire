package models.card.ironclad.attack.uncommon;

import helper.PathAssistent;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Uppercut Karte.
 *
 * @author OF Daniel Willig
 */
public class UppercutCard extends AttackCard {
    /**
     * Constructor Uppercut card.
     */
    public UppercutCard() {
        super("Uppercut", "Deal 13 damage. Apply 1 Weak.Apply 1 Vulnerable.", 2, 13, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
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
