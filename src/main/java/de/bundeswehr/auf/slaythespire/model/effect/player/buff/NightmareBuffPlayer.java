package de.bundeswehr.auf.slaythespire.model.effect.player.buff;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Buff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Debuff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.Objects;

public class NightmareBuffPlayer extends Buff {

    private final Card card;

    public NightmareBuffPlayer(Card card) {
        super("Nightmare", "Add X of a chosen card into your hand next turn.", EffectTrigger.BEGIN_OF_TURN, StackingBehaviour.INTENSITY);
        setImagePath(new PathAssistent().toPath(this));
        this.card = card;
    }

    @Override
    public void apply(GameContext gameContext, Entity target) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();
        try {
            for (int i = 0; i < player.getEffectCounter(this); i++) {
                battleDeck.addToHand(card.getClass().newInstance());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            LoggingAssistant.log(e, Color.RED);
        }
        player.reduceEffectCounter(this, player.getEffectCounter(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        NightmareBuffPlayer that = (NightmareBuffPlayer) o;
        return Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), card);
    }

}
