package models.cards.ironclad_cards.attack.rare;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

/**
 * Die Feed Karte.
 *
 * @author OF Daniel Willig
 */
public class FeedCard extends AttackCard {
    /**
     * Constructor Feed card.
     */
    public FeedCard() {
        super("Feed", "Deal 10 damage. If Fatal, raise your Max HP by 3. Exhaust.", 1, 10, CardRarity.RARE, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        if (!enemy.isAlive()) {
            player.increaseMaxHealth(3);
        }
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
