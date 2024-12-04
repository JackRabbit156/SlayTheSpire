package models.card.ironclad.attack.uncommon;

import helper.PathAssistent;
import models.battle.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Hemokinesis Karte.
 *
 * @author OF Daniel Willig
 */
public class HemokinesisCard extends AttackCard {
    /**
     * Constructor Hemokinesis card.
     */
    public HemokinesisCard() {
        super("Hemokinesis", "Lose 2 HP. Deal 15 damage.", 1, 15, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        player.decreaseCurrentHealth(2, true);
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
