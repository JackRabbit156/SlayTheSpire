package de.bundeswehr.auf.slaythespire.controller.listener;

import de.bundeswehr.auf.slaythespire.events.InventoryEvent;

public class EmptyInventoryEventListener implements InventoryEventListener {

    @Override
    public void onCardEvent(InventoryEvent event) {}

    @Override
    public void onGoldEvent(InventoryEvent event) {}

    @Override
    public void onLevelEvent(InventoryEvent event) {}

    @Override
    public void onPotionEvent(InventoryEvent event) {}

    @Override
    public void onRelicEvent(InventoryEvent event) {}

}
