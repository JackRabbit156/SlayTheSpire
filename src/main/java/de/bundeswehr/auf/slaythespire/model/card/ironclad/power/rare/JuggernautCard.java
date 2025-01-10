package de.bundeswehr.auf.slaythespire.model.card.ironclad.power.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.*;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;
import java.util.Random;

/**
 * Die Juggernaut Karte.
 *
 * @author OF Daniel Willig
 */
public class JuggernautCard extends PowerCard {

    private static final Random rnd = new Random();

    /**
     * Constructor Juggernaut card.
     */
    public JuggernautCard() {
        super("Juggernaut", "Whenever you gain Block, deal 5 damage to a random enemy.", 2, CardRarity.RARE, CardGrave.NONE, CardTrigger.GAIN_BLOCK);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        List<Enemy> allEnemies = gameContext.getEnemies();
        int targetIndex = rnd.nextInt(allEnemies.size());
        Enemy enemy = allEnemies.get(targetIndex);
        player.dealDamage(gameContext, 5, enemy, this);
    }

}
