package models.card.ironclad_cards.power.rare;

import helper.PathAssistent;
import models.battle.BattleDeck;
import models.battle.GameContext;
import models.card.card_structure.*;
import models.enemy.Enemy;
import models.player.player_structure.Player;

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
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();

        battleDeck.addPowerCards(new JuggernautCard());

        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public void ability(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        Random rand = new Random();
        int targetIndex = rand.nextInt(allEnemies.size());

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(5);
    }
}
