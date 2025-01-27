package de.bundeswehr.auf.slaythespire.model.relic.boss.ironclad;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.status.WoundCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.AdditionallyTriggered;
import de.bundeswehr.auf.slaythespire.model.relic.structure.PlayerTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public class MarkOfPainRelic extends PlayerTypeRelic implements AdditionallyTriggered {

    public MarkOfPainRelic() {
        super("Mark of Pain", "Gain 1 Energy at the start of each turn. Start combats with 2 Wounds in your draw pile.",
                RelicRarity.BOSS, PlayerType.IRONCLAD, RelicTrigger.BEGIN_OF_TURN);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.gainEnergy(1);
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.addToDrawPile(new WoundCard());
        battleDeck.addToDrawPile(new WoundCard());
    }

    @Override
    public RelicTrigger getAdditionalTrigger() {
        return RelicTrigger.START_OF_COMBAT;
    }

}
