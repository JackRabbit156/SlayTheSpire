package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.gui.BattleView;


/**
 * Das TopSide layout.
 *
 * @author OF Daniel Willig
 */
public class TopSideLayout extends VBox {

    private BarLayout barLayout;
    private final BattleView battleView;
    private final Player player;

    public TopSideLayout(BattleView battleView, Player player) {
        this.battleView = battleView;
        this.player = player;

        initTopSide();
    }

    public void setDisablePotions(boolean value) {
        barLayout.setDisablePotions(value);
    }

    public void update() {
        barLayout.refreshBar();
    }

    private void initTopSide() {
        barLayout = new BarLayout(battleView, player);
        getChildren().addAll(barLayout);

        VBox.setVgrow(barLayout, Priority.ALWAYS);
    }

}
