package models.card.ironclad_cards.attack.rare;

import helper.PathAssistent;
import models.battle.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

/**
 * Die Immolate Karte.
 *
 * @author OF Daniel Willig
 */
public class ImmolateCard extends AttackCard {
    /**
     * Constructor Immolate card.
     */
    public ImmolateCard() {
        super("Immolate", "Deal 21 damage to ALL enemies.", 2, 21, CardRarity.RARE, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy allEnemy : allEnemies) {
            allEnemy.takeDamage(dealDamage());
        }

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
