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
 * Die Carnage Karte.
 *
 * @author OF Daniel Willig
 */
public class CarnageCard extends AttackCard {
    /**
     * Constructor Carnage card.
     */
    public CarnageCard() {
        super("Carnage", "Ethereal. Deal 20 damage.", 2, 20, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO Ethereal
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
