package models.cards.ironclad_cards.power.rare;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.*;
import models.player.player_structure.Player;

public class JuggernautCard extends PowerCard {
    public JuggernautCard() {
        super("Juggernaut", "Whenever you gain Block, deal 5 damage to a random enemy.", 2, CardRarity.RARE, CardGrave.NONE, CardTrigger.GAIN_BLOCK);
    }


    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();

        battleDeck.getCurrentPowerCards().add(new JuggernautCard());

        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public void ability(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        player.increaseBlock(5);
    }
}
