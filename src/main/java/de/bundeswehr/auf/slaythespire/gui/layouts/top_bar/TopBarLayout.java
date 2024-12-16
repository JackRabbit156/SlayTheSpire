package de.bundeswehr.auf.slaythespire.gui.layouts.top_bar;

import de.bundeswehr.auf.slaythespire.gui.WithTopBar;
import de.bundeswehr.auf.slaythespire.gui.layouts.MiddleBar;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;


/**
 * Das TopSide layout.
 *
 * @author OF Daniel Willig
 */
public class TopBarLayout extends VBox {

    private final BarLayout barLayout;

    public TopBarLayout(WithTopBar view, Player player) {
        this(view, player, new PotionLayout(player.getPotionCards()));
    }

    public TopBarLayout(WithTopBar view, Player player, MiddleBar middleBar) {
        barLayout = new BarLayout(view, player, middleBar);
        getChildren().add(barLayout);
        VBox.setVgrow(barLayout, Priority.ALWAYS);
    }

    public void setDisableMiddleBar(boolean value) {
        barLayout.setDisableMiddleBar(value);
    }

    public void update() {
        barLayout.refresh();
    }

}
