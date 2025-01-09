package de.bundeswehr.auf.slaythespire.controller.listener;

import de.bundeswehr.auf.slaythespire.events.*;

public class EmptyPlayerEventListener implements PlayerEventListener {

    @Override
    public void onBlockReceived(PlayerBlockEvent event) {}

    @Override
    public void onDamageReceived(PlayerDamageEvent event) {}

    @Override
    public void onEffect(EffectEvent event) {}

    @Override
    public void onDamageDealt(PlayerDamageEvent event) {}

    @Override
    public void onEnergyReceived(PlayerEnergyEvent event) {}

    @Override
    public void onHealthReceived(PlayerHealthEvent event) {}

    @Override
    public void onMaxHealthChanged(PlayerHealthEvent event) {}

}
