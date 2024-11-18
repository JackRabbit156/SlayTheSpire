package models.cards.ironclad_cards.attack.common;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.battle.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
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
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = ConsoleAssistent.scannerAutoAim(gameContext.getEnemies().size());

        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy allEnemy : allEnemies) {
            //TODO Apply Debuff 1 Vulnerable to ALL
        }

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
