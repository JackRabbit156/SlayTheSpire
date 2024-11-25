package view.gui.layouts.battle_view_layouts;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import models.player.player_structure.Player;
import view.gui.BattleView;


/**
 * Das TopSide layout.
 *
 * @author OF Daniel Willig
 */
public class TopSideLayout extends VBox {
    private Player player;
    private BattleView battleView;

    private BarLayout barLayout;

    private VBox topVBox;
    

    public TopSideLayout(BattleView battleView, Player player) {
        this.battleView = battleView;
        this.player = player;

        initTopSide();
    }

    private void initTopSide() {
        barLayout = new BarLayout(battleView,player);
        topVBox = new VBox(barLayout);
        //TODO bottomVBox für die Relics

        getChildren().addAll(topVBox);

        VBox.setVgrow(topVBox, Priority.ALWAYS);
    }

    public void updateTop(){
        barLayout.refreshBar();
        //TODO relicLayout.refreshRelics();
    }
}
