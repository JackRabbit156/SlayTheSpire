package view.gui.layouts.battle_view_layouts;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import models.player.player_structure.Player;


/**
 * Das Settings layout. Rein kosmetisch
 * Die drei kleinen Icons auf der rechten Seite der grauen Leiste.
 * Das Deck gibt an wie viel Karten gerade im Deck sind.
 *
 * @author OF Daniel Willig
 */
public class SettingsLayout extends HBox {
    private final Text libraryTextStroke = new Text();
    private final Text libraryText = new Text();

    private ImageView mapIconView;
    private ImageView libraryIconView;
    private ImageView cogIconView;


    private final String strokeColor = "#000000";
    private final int strokeWidth = 3;

    private final String textColor = "#ffffff";

    private final Font font = Font.font("Kreon", FontWeight.BOLD, 20);
    private final FontSmoothingType smoothingType = FontSmoothingType.GRAY;

    public SettingsLayout() {
        initMapIcon();
        initLibraryIcon();
        initSettingsIcon();

        initLibraryText();

        StackPane library = new StackPane();
        library.getChildren().addAll(libraryIconView, libraryTextStroke, libraryText);

        library.setAlignment(Pos.CENTER);

        getChildren().addAll(mapIconView, library, cogIconView);
        setAlignment(Pos.CENTER);

        setTranslateY(-30);
        setTranslateX(-30);
    }

    public void setLibraryText(int libraryCards) {
        libraryTextStroke.setText(String.valueOf(libraryCards));
        libraryText.setText(String.valueOf(libraryCards));
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

    private void initSettingsIcon() {
        String path = "/images/view/gui/layouts/battle_view_layouts/settings_layout/cog.png";
        Image cog = new Image(getClass().getResource(path).toExternalForm());
        cogIconView = new ImageView(cog);
    }

    private void initLibraryIcon() {
        String path = "/images/view/gui/layouts/battle_view_layouts/settings_layout/deck.png";
        Image deck = new Image(getClass().getResource(path).toExternalForm());
        libraryIconView = new ImageView(deck);
    }

    private void initMapIcon() {
        String path = "/images/view/gui/layouts/battle_view_layouts/settings_layout/map.png";
        Image map = new Image(getClass().getResource(path).toExternalForm());
        mapIconView = new ImageView(map);
    }


}
