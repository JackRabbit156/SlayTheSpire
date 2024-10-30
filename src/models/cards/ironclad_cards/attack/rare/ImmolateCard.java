package models.cards.ironclad_cards.attack.rare;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

public class ImmolateCard extends AttackCard {
    public ImmolateCard() {
        super("Immolate", "Deal 21 damage to ALL enemies. Add a Burn into your discard pile.", 2, 21, CardRarity.RARE, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy allEnemy : allEnemies) {
            allEnemy.takeDamage(dealDamage());
        }

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO deck.add burnCard
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
