package models.cards.ironclad_cards.attack.common;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.battle.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Clothesline Karte.
 * @author OF Daniel Willig
 */
public class ClotheslineCard extends AttackCard {
    /**
     * Constructor ClotheslineCard
     */
    public ClotheslineCard() {
        super("Clothesline", "Deal 12 damage. Apply 2 Icon Weak Weak.", 2, 12, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = ConsoleAssistent.scannerAutoAim(gameContext.getEnemies().size());

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO Apply Debuff 2 Weak
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
