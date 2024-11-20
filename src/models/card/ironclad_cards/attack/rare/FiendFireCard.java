package models.card.ironclad_cards.attack.rare;

import helper.PathAssistent;
import models.battle.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Fiend fire Karte.
 *
 * @author OF Daniel Willig
 */
public class FiendFireCard extends AttackCard {
    /**
     * Constructor Fiend fire card.
     */
    public FiendFireCard() {
        super("Fiend Fire", "Exhaust all cards in your hand. Deal 7 damage for each Exhausted card.Exhaust.", 2, 7, CardRarity.RARE, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO Exhaust for
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
