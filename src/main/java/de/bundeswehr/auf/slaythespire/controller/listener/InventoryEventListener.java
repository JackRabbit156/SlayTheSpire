package de.bundeswehr.auf.slaythespire.controller.listener;

import de.bundeswehr.auf.slaythespire.events.InventoryEvent;

public interface InventoryEventListener {

    void onCardEvent(InventoryEvent event);

    void onGoldEvent(InventoryEvent event);

    void onLevelEvent(InventoryEvent event);

    void onPotionEvent(InventoryEvent event);

    void onRelicEvent(InventoryEvent event);

}
