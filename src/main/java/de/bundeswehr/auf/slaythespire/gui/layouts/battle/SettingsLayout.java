package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import de.bundeswehr.auf.slaythespire.gui.BattleView;
import de.bundeswehr.auf.slaythespire.gui.components.FullScreenButton;


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

    private final BattleView battleView;
    private ImageView cogIconView;
    private Button fullScreen;
    private ImageView libraryIconView;
    private final Text libraryText = new Text();
    private final Text libraryTextStroke = new Text();
    private ImageView mapIconView;

    public SettingsLayout(BattleView battleView) {
        this.battleView = battleView;
        initMapIcon();
        initLibraryIcon();
        initLibraryText();
        initFullScreenIcon();
        initSettingsIcon();

        StackPane library = new StackPane();
        library.getChildren().addAll(libraryIconView, libraryTextStroke, libraryText);
        library.setAlignment(Pos.CENTER);

        // TODO Funktion von mapIconView und cogIconView
        getChildren().addAll(/*mapIconView, */library, fullScreen/*, cogIconView*/);
        setAlignment(Pos.CENTER);

        setTranslateY(-30);
        setTranslateX(-30);
    }

    public void setLibraryText(int libraryCards) {
        libraryTextStroke.setText(String.valueOf(libraryCards));
        libraryText.setText(String.valueOf(libraryCards));
    }

    private void initFullScreenIcon() {
        fullScreen = new FullScreenButton();
        fullScreen.setOnAction(e -> battleView.clickedOnFullscreen());
    }

    private void initLibraryIcon() {
        String path = "/images/view/gui/layouts/battle/settings/deck.png";
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
        String path = "/images/view/gui/layouts/battle/settings/map.png";
        Image map = new Image(getClass().getResource(path).toExternalForm());
        mapIconView = new ImageView(map);
    }

    private void initSettingsIcon() {
        String path = "/images/view/gui/layouts/battle/settings/cog.png";
        Image cog = new Image(getClass().getResource(path).toExternalForm());
        cogIconView = new ImageView(cog);
    }

}
