package view.gui.layouts.shop_layout;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import models.player.player_structure.Player;
import view.gui.ShopView;


/**
 * Das TopSide layout.
 *
 * @author OF Daniel Willig
 */
public class TopSideLayout extends VBox {

    private BarLayout barLayout;
    private final ShopView shopView;
    private final Player player;

    public TopSideLayout(ShopView shopView, Player player) {
        this.shopView = shopView;
        this.player = player;

        initTopSide();
    }

    public void update() {
        barLayout.refreshBar();
    }

    private void initTopSide() {
        barLayout = new BarLayout(shopView, player);
        getChildren().addAll(barLayout);

        VBox.setVgrow(barLayout, Priority.ALWAYS);
    }

}
