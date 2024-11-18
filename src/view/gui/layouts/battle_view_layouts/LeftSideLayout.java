package view.gui.layouts.battle_view_layouts;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import models.player.player_structure.Player;

public class LeftSideLayout extends VBox {
    private Player player;

    private PlayerLayout playerLayout;

    public LeftSideLayout(Player player) {
        this.player = player;

        setAlignment(Pos.BOTTOM_RIGHT);
        playerLayout = new PlayerLayout(player);
        getChildren().add(playerLayout);
    }

    public void updatePlayer(){
        playerLayout.updatePlayer();
    }

}
