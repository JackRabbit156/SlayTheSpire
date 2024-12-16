package de.bundeswehr.auf.slaythespire.gui.layouts.top_bar;

import de.bundeswehr.auf.slaythespire.gui.WithTopBar;
import de.bundeswehr.auf.slaythespire.gui.components.MapButton;
import de.bundeswehr.auf.slaythespire.gui.components.SettingsButton;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import de.bundeswehr.auf.slaythespire.gui.components.FullScreenButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 * Das Settings layout. Rein kosmetisch
 * Die drei kleinen Icons auf der rechten Seite der grauen Leiste.
 * Das Deck gibt an wie viel Karten gerade im Deck sind.
 *
 * @author OF Daniel Willig
 */
public class SettingsLayout extends HBox {

    private final static Font font = Font.font("Kreon", FontWeight.BOLD, 20);
    private final static FontSmoothingType smoothingType = FontSmoothingType.GRAY;
    private final static String strokeColor = "#000000";
    private final static int strokeWidth = 3;
    private final static String textColor = "#ffffff";

    private Button settings;
    private Button fullScreen;
    private ImageView libraryIconView;
    private final Text libraryText = new Text();
    private final Text libraryTextStroke = new Text();
    private Button map;
    private final WithTopBar view;

    public SettingsLayout(WithTopBar view) {
        this.view = view;

        HBox.setHgrow(this, Priority.ALWAYS);

        if (view.showLibrary()) {
            initLibraryIcon();
            initLibraryText();
            StackPane library = new StackPane();
            library.getChildren().addAll(libraryIconView, libraryTextStroke, libraryText);
            library.setAlignment(Pos.CENTER);
            getChildren().add(library);
            Pane spacer = new Pane();
            spacer.setPrefWidth(50);
            getChildren().add(spacer);
        }

        if (view.showMap()) {
            initMapIcon();
            getChildren().add(map);
        }

        initFullScreenIcon();
        getChildren().add(fullScreen);

        if (view.showSettings()) {
            initSettingsIcon();
            getChildren().add(settings);
        }

        setAlignment(Pos.CENTER_RIGHT);
        setTranslateY(-30);
    }

    public void setLibraryText(Player player) {
        libraryTextStroke.setText(String.valueOf(player.getDeck().size()));
        libraryText.setText(String.valueOf(player.getDeck().size()));
    }

    private void initFullScreenIcon() {
        fullScreen = new FullScreenButton();
        fullScreen.setOnAction(e -> view.onFullScreen());
    }

    private void initLibraryIcon() {
        String path = "/images/view/gui/layouts/settings/deck.png";
        Image deck = new Image(getClass().getResource(path).toExternalForm());
        libraryIconView = new ImageView(deck);
    }

    private void initLibraryText() {
        libraryTextStroke.setStroke(Paint.valueOf(strokeColor));
        libraryTextStroke.setStrokeWidth(strokeWidth);
        libraryText.setFill(Paint.valueOf(textColor));
        libraryTextStroke.setFont(font);
        libraryTextStroke.setFontSmoothingType(smoothingType);
        libraryText.setFont(font);
        libraryText.setFontSmoothingType(smoothingType);
    }

    private void initMapIcon() {
        map = new MapButton();
        map.setOnAction(event -> view.onMap());
    }

    private void initSettingsIcon() {
        settings = new SettingsButton();
        settings.setOnAction(e -> view.onSettings());
    }

}
