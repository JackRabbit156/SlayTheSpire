package models.card.ironclad_cards.attack.rare;

import helper.PathAssistent;
import models.battle.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Bludgeon Karte.
 *
 * @author OF Daniel Willig
 */
public class BludgeonCard extends AttackCard {
    /**
     * Constructor Bludgeon card.
     */
    public BludgeonCard() {
        super("Bludgeon", "Deal 32 damage.", 3, 32, CardRarity.RARE, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
