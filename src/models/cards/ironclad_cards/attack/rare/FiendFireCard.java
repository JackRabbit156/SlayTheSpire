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
        targetIsRequired();
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
