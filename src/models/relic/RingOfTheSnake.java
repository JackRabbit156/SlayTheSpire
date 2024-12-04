package models.relic;

import helper.PathAssistent;
import models.battle.BattleDeck;
import models.battle.GameContext;
import models.player.player_structure.PlayerType;
import models.relic.relic_structure.PlayerTypeRelic;
import models.relic.relic_structure.RelicTrigger;
import models.relic.relic_structure.RelicType;

/**
 * The type Ring of the snake.
 *
 * @author OF Daniel Willig
 */
public class RingOfTheSnake extends PlayerTypeRelic {

    /**
     * Constructor Ring of the snake.
     */
    public RingOfTheSnake() {
        super("Ring of the Snake", "At the start of each combat, draw 2 additional cards.", RelicType.STARTER, PlayerType.SILENT, RelicTrigger.START_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void getsUsed(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(2);
    }

}