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
 * Die Hemokinesis Karte.
 *
 * @author OF Daniel Willig
 */
public class HemokinesisCard extends AttackCard {
    /**
     * Constructor Hemokinesis card.
     */
    public HemokinesisCard() {
        super("Hemokinesis", "Lose 2 HP. Deal 15 damage.", 1, 15, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        player.decreaseCurrentHealth(2, true);
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
