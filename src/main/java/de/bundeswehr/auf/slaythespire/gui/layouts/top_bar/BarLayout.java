package de.bundeswehr.auf.slaythespire.gui.layouts.top_bar;

import de.bundeswehr.auf.slaythespire.gui.WithTopBar;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Bar layout.
 * Der graue Balken im Kampf oben auf die alles drauf gepackt wird
 *
 * @author OF Daniel Willig
 */
public class BarLayout extends StackPane {

    private final InfoLayout infoLayout;
    private final Player player;
    private final MiddleBar middleBar;
    private final RelicLayout relic;
    private final SettingsLayout settingsLayout;

    /**
     * Constructor Bar layout.
     *
     * @param view       die zugehörige view
     * @param player     der player
     */
    public BarLayout(WithTopBar view, Player player, MiddleBar middleBar) {
        this.player = player;

        HBox icons = new HBox();
        icons.setPadding(new Insets(0, 30, 20, 30));

        infoLayout = new InfoLayout();
        icons.getChildren().add(infoLayout);

        this.middleBar = middleBar;
        middleBar.setPadding(new Insets(0, 0, 0, 130));
        icons.getChildren().add(middleBar);

        relic = new RelicLayout(player);
        relic.setPadding(new Insets(0, 0, 0, 50));
        icons.getChildren().add(relic);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        icons.getChildren().add(spacer);


        settingsLayout = new SettingsLayout(view);
        icons.getChildren().add(settingsLayout);

        Image background = new Image("/images/view/gui/layouts/topbar/Topbar.png");
        ImageView imageView = new ImageView(background);
        getChildren().addAll(imageView, icons);
    }

    /**
     * Refresh bar, falls eine Änderung passiert, wird alles einmal aktualisiert
     */
    public void refresh() {
        infoLayout.update(player);
        middleBar.refresh();
        relic.refresh();
        settingsLayout.setLibraryText(player);
    }

    public void setDisableMiddleBar(boolean value) {
        middleBar.setDisable(value);
    }

}
