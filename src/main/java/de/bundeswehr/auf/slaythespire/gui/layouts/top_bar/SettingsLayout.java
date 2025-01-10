package de.bundeswehr.auf.slaythespire.gui.layouts.top_bar;

import com.sun.javafx.scene.traversal.Direction;
import de.bundeswehr.auf.slaythespire.controller.listener.EmptyInventoryEventListener;
import de.bundeswehr.auf.slaythespire.events.InventoryEvent;
import de.bundeswehr.auf.slaythespire.gui.View;
import de.bundeswehr.auf.slaythespire.gui.WithTopBar;
import de.bundeswehr.auf.slaythespire.gui.components.*;
import de.bundeswehr.auf.slaythespire.gui.components.animation.CardIconLayout;
import de.bundeswehr.auf.slaythespire.gui.components.buttons.FullScreenButton;
import de.bundeswehr.auf.slaythespire.gui.components.buttons.MapButton;
import de.bundeswehr.auf.slaythespire.gui.components.buttons.SettingsButton;
import de.bundeswehr.auf.slaythespire.helper.Animate;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


/**
 * Das Settings layout. Rein kosmetisch
 * Die drei kleinen Icons auf der rechten Seite der grauen Leiste.
 * Das Deck gibt an wie viel Karten gerade im Deck sind.
 *
 * @author OF Daniel Willig
 */
public class SettingsLayout extends HBox implements View {

    private Button fullScreen;
    private ImageView libraryIconView;
    private final StrokedText libraryText = new StrokedText(20, Color.WHITE, Color.BLACK, 3);
    private Button map;
    private Button settings;
    private ImageView timerIconView;
    private TimerText timerText;
    private final WithTopBar view;

    public SettingsLayout(WithTopBar view, Player player) {
        this.view = view;

        HBox.setHgrow(this, Priority.ALWAYS);

        initTimerIcon();
        initTimerText();
        getChildren().addAll(timerIconView, timerText);
        addSpacer();

        if (view.showLibrary()) {
            initLibraryIcon();
            StackPane library = new StackPane();
            library.getChildren().addAll(libraryIconView, libraryText);
            library.setAlignment(Pos.CENTER);
            getChildren().add(library);
            addSpacer();
            setLibraryText(player);
            player.addInventoryEventListener(new EmptyInventoryEventListener() {

                @Override
                public void onCardEvent(InventoryEvent event) {
                    Animate.pathAnimationBelowTarget(new CardIconLayout((Card) event.getValue()),
                            library,
                            event.getDirection() == InventoryEvent.Direction.GAIN ? Direction.UP : Direction.DOWN,
                            e -> setLibraryText(player));
                }

            });
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
    }

    @Override
    public void discard() {
        timerText.discard();
    }

    private void addSpacer() {
        Pane spacer = new Pane();
        spacer.setPrefWidth(30);
        getChildren().add(spacer);
    }

    private void initFullScreenIcon() {
        fullScreen = new FullScreenButton();
        fullScreen.setOnAction(e -> view.onFullScreen());
    }

    private void initLibraryIcon() {
        String path = "/images/gui/settings/deck.png";
        Image deck = new Image(path);
        libraryIconView = new ImageView(deck);
    }

    private void initMapIcon() {
        map = new MapButton();
        map.setOnAction(event -> view.onMap());
    }

    private void initSettingsIcon() {
        settings = new SettingsButton();
        settings.setOnAction(e -> view.onSettings());
    }

    private void initTimerIcon() {
        String path = "/images/gui/settings/timer.png";
        Image timer = new Image(path);
        timerIconView = new ImageView(timer);
    }

    private void initTimerText() {
        timerText = new TimerText();
    }

    private void setLibraryText(Player player) {
        libraryText.setText(String.valueOf(player.getDeck().size()));
    }

}
