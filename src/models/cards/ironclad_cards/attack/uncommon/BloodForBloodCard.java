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
 * Die Blood for blood Karte.
 *
 * @author OF Daniel Willig
 */
public class BloodForBloodCard extends AttackCard {
    /**
     * Constructor Blood for blood card.
     */
    public BloodForBloodCard() {
        super("Blood for Blood", "Costs 1 less Energy for each time you lose HP this combat.Deal 18 damage.", 4, 18, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost()); //TODO minus each time you lost HP this combat
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
