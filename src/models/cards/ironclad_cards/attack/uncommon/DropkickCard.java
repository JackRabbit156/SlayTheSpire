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
 * Die Dropkick Karte.
 *
 * @author OF Daniel Willig
 */
public class DropkickCard extends AttackCard {
    /**
     * Constructor Dropkick card.
     */
    public DropkickCard() {
        super("Dropkick", "Deal 5 damage. If the enemy has Vulnerable, gain Energy and draw 1 card.", 1, 5, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO if (enemy.hasEffect.Vulnerable) {player.increaseEnergy(1); player.drawCard(1);

    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
