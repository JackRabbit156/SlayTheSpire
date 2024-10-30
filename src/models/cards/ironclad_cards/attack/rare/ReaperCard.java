package models.cards.ironclad_cards.attack.rare;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

public class ReaperCard extends AttackCard {
    public ReaperCard() {
        super("Reaper", "Deal 4 damage to ALL enemies. Heal HP equal to unblocked damage. Exhaust.", 2, 4, CardRarity.RARE, CardGrave.EXHAUST);
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        int bonusHp = 0;
        for (Enemy allEnemy : allEnemies) {
            int enemyHealthOld = allEnemy.getHealth();
            allEnemy.takeDamage(dealDamage());
            if (allEnemy.getHealth() < enemyHealthOld) {
                bonusHp += (allEnemy.getHealth() - enemyHealthOld);
            }
        }

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        player.increaseCurrentHealth(bonusHp);
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
