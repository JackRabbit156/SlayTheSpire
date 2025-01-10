package de.bundeswehr.auf.slaythespire.gui.layouts.top_bar;

import com.sun.javafx.scene.traversal.Direction;
import de.bundeswehr.auf.slaythespire.controller.listener.EmptyInventoryEventListener;
import de.bundeswehr.auf.slaythespire.events.InventoryEvent;
import de.bundeswehr.auf.slaythespire.gui.View;
import de.bundeswehr.auf.slaythespire.gui.WithTopBar;
import de.bundeswehr.auf.slaythespire.gui.components.PotionIconLayout;
import de.bundeswehr.auf.slaythespire.gui.components.RelicIconLayout;
import de.bundeswehr.auf.slaythespire.helper.Animate;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

/**
 * Die Bar layout.
 * Der graue Balken im Kampf oben auf die alles drauf gepackt wird
 *
 * @author OF Daniel Willig
 */
public class BarLayout extends StackPane implements View {

    private final InfoLayout infoLayout;
    private final MiddleBar middleBar;
    private final RelicLayout relic;
    private final SettingsLayout settingsLayout;

    /**
     * Constructor Bar layout.
     *
     * @param view   die zugehörige view
     * @param player der player
     */
    public BarLayout(WithTopBar view, Player player, MiddleBar middleBar) {
        HBox icons = new HBox();
        icons.setPadding(new Insets(0, 30, 20, 30));

        infoLayout = new InfoLayout(player);
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

        settingsLayout = new SettingsLayout(view, player);
        icons.getChildren().add(settingsLayout);

        Image background = new Image("/images/view/gui/layouts/topbar/Topbar.png");
        ImageView imageView = new ImageView(background);
        getChildren().addAll(imageView, icons);

        middleBar.refresh();
        relic.refresh();
        player.addInventoryEventListener(new EmptyInventoryEventListener() {

            @Override
            public void onPotionEvent(InventoryEvent event) {
                Animate.pathAnimationBelowTarget(new PotionIconLayout((Potion) event.getValue()),
                        middleBar,
                        event.getDirection() == InventoryEvent.Direction.GAIN ? Direction.UP : Direction.DOWN,
                        e -> middleBar.refresh());
            }

            @Override
            public void onRelicEvent(InventoryEvent event) {
                Animate.pathAnimationBelowTarget(new RelicIconLayout((Relic) event.getValue()),
                        relic,
                        Direction.UP,
                        e -> relic.refresh());
            }

        });
    }

    @Override
    public void discard() {
        settingsLayout.discard();
    }

    public void setDisableMiddleBar(boolean value) {
        middleBar.setDisable(value);
    }

}
