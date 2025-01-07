package de.bundeswehr.auf.slaythespire.controller.listener;

import de.bundeswehr.auf.slaythespire.events.PlayerBlockEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerDamageEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerEnergyEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerHealthEvent;

public class EmptyPlayerEventListener implements PlayerEventListener {

    @Override
    public void onBlockReceived(PlayerBlockEvent event) {}

    @Override
    public void onDamageReceived(PlayerDamageEvent event) {}

    @Override
    public void onDamageDealt(PlayerDamageEvent event) {}

    @Override
    public void onEnergyReceived(PlayerEnergyEvent event) {}

    @Override
    public void onHealthReceived(PlayerHealthEvent event) {}

}
