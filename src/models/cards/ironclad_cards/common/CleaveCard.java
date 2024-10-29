package models.cards.ironclad_cards.common;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

public class CleaveCard extends AttackCard {
    public CleaveCard() {
        super("Cleave", "Deal 8 damage to ALL enemies.", 1, 8, CardRarity.COMMON);
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy allEnemy : allEnemies) {
            allEnemy.takeDamage(dealDamage());
        }

        Player player = gameContext.getPlayer();
        player.loseEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
