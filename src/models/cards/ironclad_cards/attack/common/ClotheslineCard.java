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
 * Clothesline Karte.
 * @author OF Daniel Willig
 */
public class ClotheslineCard extends AttackCard {
    /**
     * Constructor ClotheslineCard
     */
    public ClotheslineCard() {
        super("Clothesline", "Deal 12 damage. Apply 2 Icon Weak Weak.", 2, 12, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO Apply Debuff 2 Weak
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
