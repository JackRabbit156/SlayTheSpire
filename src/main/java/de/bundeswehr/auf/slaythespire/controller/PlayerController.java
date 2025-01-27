package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.controller.listener.EmptyPlayerEventListener;
import de.bundeswehr.auf.slaythespire.controller.listener.InventoryEventListener;
import de.bundeswehr.auf.slaythespire.events.EffectEvent;
import de.bundeswehr.auf.slaythespire.events.InventoryEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerDamageEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerHealthEvent;
import de.bundeswehr.auf.slaythespire.model.battle.EffectContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.AdditionallyTriggered;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Resetable;

public class PlayerController extends EmptyPlayerEventListener implements InventoryEventListener {

    private final GameContext gameContext;
    private final Player player;

    public PlayerController(GameContext gameContext) {
        this.player = gameContext.getPlayer();

        this.gameContext = gameContext;
        gameContext.getPlayer().addInventoryEventListener(this);
        gameContext.getPlayer().addPlayerEventListener(this);
    }

    @Override
    public void onCardEvent(InventoryEvent event) {

    }

    @Override
    public void onDamageReceived(PlayerDamageEvent event) {
        triggerRelics(RelicTrigger.LOSE_HP);
    }

    @Override
    public void onEffect(EffectEvent event) {
        gameContext.setEffectContext(new EffectContext(null, player, event.getEffect()));
        triggerRelics(RelicTrigger.EFFECT);
    }

    @Override
    public void onGoldEvent(InventoryEvent event) {
        if (event.getDirection() == InventoryEvent.Direction.GAIN) {
            triggerRelics(RelicTrigger.GAIN_GOLD);
        }
    }

    @Override
    public void onHealthReceived(PlayerHealthEvent event) {
        triggerRelics(RelicTrigger.GAIN_HP);
    }

    @Override
    public void onLevelEvent(InventoryEvent event) {

    }

    @Override
    public void onMaxHealthChanged(PlayerHealthEvent event) {

    }

    @Override
    public void onPotionEvent(InventoryEvent event) {
        // currently battle only
    }

    @Override
    public void onRelicEvent(InventoryEvent event) {

    }

    public void triggerRelics(RelicTrigger trigger) {
        for (Relic relic : player.getRelics()) {
            if (relic.getTrigger().equals(trigger)) {
                relic.activate(gameContext);
            }
            else if (relic instanceof AdditionallyTriggered) {
                AdditionallyTriggered additionallyTriggered = (AdditionallyTriggered) relic;
                if (additionallyTriggered.getAdditionalTrigger().equals(trigger)) {
                    additionallyTriggered.onTrigger(gameContext);
                }
            }
        }
        resetRelics(trigger);
    }

    private void resetRelics(RelicTrigger trigger) {
        for (Relic relic : player.getRelics()) {
            if (relic instanceof Resetable) {
                Resetable resetable = (Resetable) relic;
                if (resetable.getResetTrigger() == trigger) {
                    resetable.reset();
                }
            }
        }
    }

}
