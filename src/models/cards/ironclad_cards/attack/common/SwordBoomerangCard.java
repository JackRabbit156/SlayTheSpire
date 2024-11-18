package models.cards.ironclad_cards.attack.common;

import helper.PathAssistent;
import models.battle.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;
import java.util.Random;

/**
 * Die Sword boomerang Karte.
 *
 * @author OF Daniel Willig
 */
public class SwordBoomerangCard extends AttackCard {
    /**
     * Constructor Sword boomerang card.
     */
    public SwordBoomerangCard() {
        super("Sword Boomerang", "Deal 3 damage to a random enemy 3 times.", 1, 3, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        Random rand = new Random();
        int targetIndex = rand.nextInt(allEnemies.size());

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        for (int i = 0; i < 3; i++) {
            enemy.takeDamage(dealDamage());
        }

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
