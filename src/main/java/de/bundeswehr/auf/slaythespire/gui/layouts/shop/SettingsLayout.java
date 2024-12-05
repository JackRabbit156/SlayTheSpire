package de.bundeswehr.auf.slaythespire.gui.layouts.shop;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import de.bundeswehr.auf.slaythespire.gui.ShopView;
import de.bundeswehr.auf.slaythespire.gui.components.FullScreenButton;


/**
 * Das Settings layout. Rein kosmetisch
 * Die drei kleinen Icons auf der rechten Seite der grauen Leiste.
 * Das Deck gibt an wie viel Karten gerade im Deck sind.
 *
 * @author OF Daniel Willig
 */
public class SettingsLayout extends HBox {

    private ImageView cogIconView;
    private Button fullScreen;
    private ImageView mapIconView;
    private final ShopView shopView;

    public SettingsLayout(ShopView shopView) {
        this.shopView = shopView;
        initMapIcon();
        initFullScreenIcon();
        initSettingsIcon();

        // TODO Funktion von mapIconView und cogIconView
        getChildren().addAll(/*mapIconView, */fullScreen/*, cogIconView*/);
        setAlignment(Pos.CENTER);

        setTranslateY(-30);
        setTranslateX(-30);
    }

    private void initFullScreenIcon() {
        fullScreen = new FullScreenButton();
        fullScreen.setOnAction(e -> shopView.clickedOnFullscreen());
    }

    private void initMapIcon() {
        String path = "/images/view/gui/layouts/battle_view_layouts/settings_layout/map.png";
        Image map = new Image(getClass().getResource(path).toExternalForm());
        mapIconView = new ImageView(map);
    }

    private void initSettingsIcon() {
        String path = "/images/view/gui/layouts/battle_view_layouts/settings_layout/cog.png";
        Image cog = new Image(getClass().getResource(path).toExternalForm());
        cogIconView = new ImageView(cog);
    }

}
