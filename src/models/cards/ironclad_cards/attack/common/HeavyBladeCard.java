package models.cards.ironclad_cards.attack.common;

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
 * Die Heavy blade Karte.
 * @author OF Daniel Willig
 */
public class HeavyBladeCard extends AttackCard {
    /**
     * Constructor HeavyBladeCard
     */
    public HeavyBladeCard() {
        super("Heavy Blade", "Deal 14 damage. Strength affects this card 3 times.", 2, 14, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO Buff Strength affects this card 3 times
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
