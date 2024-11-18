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
 * Die Iron wave Karte.
 *
 * @author OF Daniel Willig
 */
public class IronWaveCard extends AttackCard {
    /**
     * Constructor Iron wave card.
     */
    public IronWaveCard() {
        super("Iron Wave", "Gain 5 Icon Block Block. Deal 5 damage.", 1, 5, CardRarity.COMMON, CardGrave.DISCARD);
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
        player.increaseBlock(5);
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
