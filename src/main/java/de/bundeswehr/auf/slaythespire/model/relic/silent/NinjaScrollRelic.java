package de.bundeswehr.auf.slaythespire.model.relic.silent;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.EffectContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.attack.ShivCard;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.PlayerTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.relic.structure.StarterTypeRelic;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Resetable;

public class NinjaScrollRelic extends PlayerTypeRelic implements Resetable {

    private int turn;

    public NinjaScrollRelic() {
        super("Ninja Scroll", "Start each combat with 3 Shivs in hand.",
                RelicRarity.UNCOMMON, PlayerType.SILENT, RelicTrigger.BEGIN_OF_TURN);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        if (turn++ == 0) {
            BattleDeck battleDeck = gameContext.getBattleDeck();
            battleDeck.addToHand(new ShivCard());
            battleDeck.addToHand(new ShivCard());
            battleDeck.addToHand(new ShivCard());
        }
    }

    @Override
    public void reset() {
        turn = 0;
    }

    @Override
    public RelicTrigger getResetTrigger() {
        return RelicTrigger.END_OF_COMBAT;
    }
}