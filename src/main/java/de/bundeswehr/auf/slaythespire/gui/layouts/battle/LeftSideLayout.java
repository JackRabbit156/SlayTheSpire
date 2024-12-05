package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.gui.BattleView;

public class LeftSideLayout extends VBox {

    private final Player player;
    private final PlayerLayout playerLayout;

    public LeftSideLayout(BattleView battleView, Player player) {
        this.player = player;

        setAlignment(Pos.BOTTOM_RIGHT);
        playerLayout = new PlayerLayout(player, battleView);
        getChildren().add(playerLayout);
    }

    public void update() {
        if (!player.isAlive()) {
            getChildren().remove(playerLayout);
        }
        playerLayout.updatePlayer();
    }

}
