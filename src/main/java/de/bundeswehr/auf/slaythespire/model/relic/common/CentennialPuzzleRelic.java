package de.bundeswehr.auf.slaythespire.model.relic.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.ThornsBuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Resetable;

public class CentennialPuzzleRelic extends Relic implements Resetable {

    private boolean lostHp;

    public CentennialPuzzleRelic() {
        super("Centennial Puzzle", "The first time you lose HP each combat, draw 3 cards.",
                RelicRarity.COMMON, RelicTrigger.LOSE_HP);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        if (!lostHp) {
            BattleDeck battleDeck = gameContext.getBattleDeck();
            battleDeck.drawCard(3);
            lostHp = true;
        }
    }

    @Override
    public void reset() {
        lostHp = false;
    }

}