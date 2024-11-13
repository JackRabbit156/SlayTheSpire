package models.cards.ironclad_cards.attack.uncommon;

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
 * Die Uppercut Karte.
 *
 * @author OF Daniel Willig
 */
public class UppercutCard extends AttackCard {
    /**
     * Constructor Uppercut card.
     */
    public UppercutCard() {
        super("Uppercut", "Deal 13 damage. Apply 1 Weak.Apply 1 Vulnerable.", 2, 13, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO Weak, Vulnerable
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
