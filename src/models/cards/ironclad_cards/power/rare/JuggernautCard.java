package models.cards.ironclad_cards.power.rare;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.*;
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
