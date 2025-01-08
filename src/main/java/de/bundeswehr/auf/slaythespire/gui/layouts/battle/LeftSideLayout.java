package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import de.bundeswehr.auf.slaythespire.gui.BattleView;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class LeftSideLayout extends VBox {

    private final Player player;
    private final PlayerLayout playerLayout;

    public LeftSideLayout(BattleView battleView, Player player) {
        this.player = player;

        setAlignment(Pos.BOTTOM_RIGHT);
        playerLayout = new PlayerLayout(player, battleView);
        getChildren().add(playerLayout);
    }

    public void discard() {
        playerLayout.handlePlayerDeath();
    }

    public void update() {
        if (!player.isAlive()) {
            getChildren().remove(playerLayout);
        }
    }

}
