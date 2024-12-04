package view.gui.layouts.shop_layout;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import models.player.player_structure.Player;
import view.gui.ShopView;

/**
 * Die Bar layout.
 * Der graue Balken im Kampf oben auf die alles drauf gepackt wird
 *
 * @author OF Daniel Willig
 */
public class BarLayout extends StackPane {

    private final InfoLayout infoLayout;
    private final Player player;
    private final PotionLayout potionLayout;

    /**
     * Constructor Bar layout.
     *
     * @param battleView die battleview
     * @param player     der player
     */
    public BarLayout(ShopView shopView, Player player) {
        this.player = player;
        infoLayout = new InfoLayout();
        potionLayout = new PotionLayout(player.getPotionCards());
        SettingsLayout settingsLayout = new SettingsLayout(shopView);

        HBox icons = new HBox();
        HBox.setHgrow(potionLayout, Priority.ALWAYS);
        HBox.setHgrow(settingsLayout, Priority.ALWAYS);

        infoLayout.setAlignment(Pos.CENTER_LEFT);
        potionLayout.setAlignment(Pos.CENTER_LEFT);
        settingsLayout.setAlignment(Pos.CENTER_RIGHT);

        icons.getChildren().addAll(infoLayout, potionLayout, settingsLayout);

        Image topbarIcon = new Image(getClass().getResource("/images/view/gui/layouts/battle_view_layouts/topbar_layout/Topbar.png").toExternalForm());
        ImageView topbarIconView = new ImageView(topbarIcon);
        getChildren().addAll(topbarIconView, icons);
    }

    /**
     * Refresh bar.
     * falls eine Änderung passiert, wird alles einmal aktualisiert
     */
    public void refreshBar() {
        infoLayout.setPlayerText(player.getName());
        infoLayout.setMoneyText(player.getGold());
        infoLayout.setHealthText(player.getCurrentHealth(), player.getMaxHealth());
        potionLayout.refreshPotions();
    }

}
