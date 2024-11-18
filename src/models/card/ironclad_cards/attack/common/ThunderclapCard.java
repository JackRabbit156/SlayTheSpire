package models.card.ironclad_cards.attack.common;

import helper.PathAssistent;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

/**
 * Die Thunderclap Karte.
 *
 * @author OF Daniel Willig
 */
public class ThunderclapCard extends AttackCard {
    /**
     * Constructor Thunderclap card.
     */
    public ThunderclapCard() {
        super("Thunderclap", "Deal 4 damage and apply 1 Vulnerable to ALL enemies.", 1, 4, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy allEnemy : allEnemies) {
            //TODO Apply Debuff 1 Vulnerable to ALL
        }

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
