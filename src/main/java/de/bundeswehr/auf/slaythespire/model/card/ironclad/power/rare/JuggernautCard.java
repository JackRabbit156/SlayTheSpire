package de.bundeswehr.auf.slaythespire.model.card.ironclad.power.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.*;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;
import java.util.Random;

/**
 * Die Juggernaut Karte.
 *
 * @author OF Daniel Willig
 */
public class JuggernautCard extends PowerCard {

    /**
     * Constructor Juggernaut card.
     */
    public JuggernautCard() {
        super("Juggernaut", "Whenever you gain Block, deal 5 damage to a random enemy.", 2, CardRarity.RARE, CardGrave.NONE, CardTrigger.GAIN_BLOCK);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        Random rand = new Random();
        int targetIndex = rand.nextInt(allEnemies.size());

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(5);
    }

}
