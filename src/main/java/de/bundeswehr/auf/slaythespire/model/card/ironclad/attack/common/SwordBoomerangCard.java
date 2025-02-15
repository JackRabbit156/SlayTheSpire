package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;
import java.util.Random;

/**
 * Die Sword boomerang Karte.
 *
 * @author OF Daniel Willig
 */
public class SwordBoomerangCard extends AttackCard {

    private static final Random rnd = new Random();

    /**
     * Constructor Sword boomerang card.
     */
    public SwordBoomerangCard() {
        super("Sword Boomerang", "Deal 3 damage to a random enemy 3 times.", 1, 3, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        List<Enemy> allEnemies = gameContext.getEnemies();
        int targetIndex = rnd.nextInt(allEnemies.size());
        Enemy enemy = allEnemies.get(targetIndex);
        for (int i = 0; i < 3; i++) {
            player.dealDamage(gameContext, getDamage(gameContext), enemy, this);
        }

        player.decreaseCurrentEnergy(getCost());
    }

}
