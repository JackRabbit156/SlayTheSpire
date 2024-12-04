package models.card.ironclad.attack.uncommon;

import helper.PathAssistent;
import models.battle.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

/**
 * Die Whirlwind Karte.
 *
 * @author OF Daniel Willig
 */
public class WhirlwindCard extends AttackCard {
    /**
     * Constructor Whirlwind card.
     */
    public WhirlwindCard() {
        super("Whirlwind", "Deal 8 damage to ALL enemies X times.", 1, 5, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> enemies = gameContext.getEnemies();
        Player player = gameContext.getPlayer();

        for (int i = 0; i < player.getCurrentEnergy(); i++) {
            for (Enemy enemy : enemies) {
                enemy.takeDamage(dealDamage());
            }
        }

        player.decreaseCurrentEnergy(player.getCurrentEnergy());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
