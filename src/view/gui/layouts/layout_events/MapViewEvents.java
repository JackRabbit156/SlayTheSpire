package view.gui.layouts.layout_events;

import models.map_elements.Node;
import models.player.player_structure.Player;

public interface MapViewEvents {
    void onValidFieldClick(Player player, Node node);

}
