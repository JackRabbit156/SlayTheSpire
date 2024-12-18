package de.bundeswehr.auf.slaythespire.gui;

import de.bundeswehr.auf.slaythespire.gui.events.EventViewEvents;
import de.bundeswehr.auf.slaythespire.gui.layouts.top_bar.TopBarLayout;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

/**
 * View für die Darstellung der Events, dynamisch aufgebaut. Es können Events über den Controller eingefügt werden,
 * diese erhalten dann ein Layout über diese View.
 *
 * @author Loeschner, Marijan
 */
public class EventView implements WithTopBar {

    private static final Background buttonBG = new Background(GuiHelper.backgroundInHD("/images/event/event_layout/disabledButton.png"));
    private static final Background buttonHighlight = new Background(GuiHelper.backgroundInHD("/images/event/event_layout/enabledButton.png"));
    private static final Font labelFont = Font.loadFont(EventView.class.getResourceAsStream(GuiHelper.DEFAULT_FONT_BOLD), 44);
    private static final DropShadow shadow = new DropShadow(8, Color.BLACK);
    private static final Font textFont = Font.loadFont(EventView.class.getResourceAsStream(GuiHelper.DEFAULT_FONT_BOLD), 30);

    private final EventViewEvents eventViewEvents;
    private final Label label = new Label();
    private final BorderPane layoutPane = new BorderPane();
    private final Button leave = new Button("\t[Leave]");
    private final Button option1;
    private final Button option2;
    private final Button option3;
    private final VBox right = new VBox();
    private final Text story;
    private final TopBarLayout top;

    public EventView(Event event, Player player, EventViewEvents eventViewEvents) {
        this.eventViewEvents = eventViewEvents;

        event.setEventView(this);
        label.setText(event.getTitle());
        story = event.getStory();
        option1 = event.getButton1();
        option2 = event.getButton2();
        option3 = event.getButton3();

        layoutPane.setBackground(new Background(GuiHelper.backgroundInHD("/images/backgrounds/greenBg.jpg")));

        ImageView image = new ImageView();
        image.setImage(event.getImage());
        image.setScaleX(0.8);
        image.setScaleY(0.8);
        image.setEffect(shadow);

        VBox left = new VBox();
        left.setAlignment(Pos.CENTER_LEFT);
        left.getChildren().addAll(label, image);
        right.setAlignment(Pos.CENTER_RIGHT);

        HBox center = new HBox();
        center.setBackground(new Background(GuiHelper.backgroundInHD("/images/event/event_layout/panel.png")));
        center.setScaleX(0.8);
        center.setScaleY(0.8);
        center.getChildren().addAll(left, right);
        center.setTranslateY(50);
        layoutPane.setCenter(center);

        top = new TopBarLayout(this, player);
        initUpdater(top);
        layoutPane.setTop(top);
        // TODO Wenn der Player stirbt
    }

    @Override
    public void discard() {
        top.discard();
    }

    public BorderPane display() {
        initStyle();
        right.getChildren().add(story);
        if (option1 != null) {
            right.getChildren().add(option1);
        }
        if (option2 != null) {
            right.getChildren().add(option2);
        }
        if (option3 != null) {
            right.getChildren().add(option3);
        }
        right.getChildren().add(leave);
        return layoutPane;
    }

    public Button getLeave() {
        return leave;
    }

    public Button getOption1() {
        return option1;
    }

    public Button getOption2() {
        return option2;
    }

    public Button getOption3() {
        return option3;
    }

    @Override
    public void onFullScreen() {
        eventViewEvents.clickedOnFullScreen();
    }

    public void showDialog(String text) {
        Image popupImage = new Image(getClass().getResource("/images/popup/popupBg.png").toExternalForm());
        ImageView imageView = new ImageView(popupImage);
        imageView.setScaleX(0.5);
        imageView.setScaleY(0.5);

        StackPane stackPopup = new StackPane();
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setWrapText(true);
        label.setMaxWidth(400);
        label.setStyle("-fx-font-size: 36;" +
                "-fx-font-family: Kreon;");
        label.setTextFill(Color.WHITE);
        stackPopup.getChildren().addAll(imageView, label);

        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.getContent().add(stackPopup);
        Bounds bounds = layoutPane.localToScreen(layoutPane.getBoundsInLocal());
        popup.show(layoutPane.getScene().getWindow(), bounds.getMinX(), bounds.getMinY());
    }

    public void updateTop() {
        top.update();
    }

    private void highlightOnClick(Button button) {
        button.setOnMousePressed(event -> {
            button.setBackground(buttonBG);
            button.setScaleX(button.getScaleX() * 0.99);
            button.setScaleY(button.getScaleY() * 0.9);
        });
        button.setOnMouseReleased(event -> {
            button.setBackground(buttonHighlight);
            button.setScaleX(button.getScaleX() * 1.01);
            button.setScaleY(button.getScaleY() * 1.1);
        });
    }

    private void initStyle() {
        label.setPadding(new Insets(0, 0, 220, 220));
        label.setFont(labelFont);
        label.setTextFill(Color.GOLDENROD);
        label.setEffect(shadow);

        story.setFont(textFont);
        story.setFill(Color.WHITE);
        story.setTextOrigin(VPos.BOTTOM);
        story.setTextAlignment(TextAlignment.CENTER);
        story.setLineSpacing(50);

        if (option1 != null) {
            initialize(option1);
        }
        if (option2 != null) {
            initialize(option2);
        }
        if (option3 != null) {
            initialize(option3);
        }

        initialize(leave);
    }

    private void initUpdater(TopBarLayout top) {
        top.update();
        if (option1 != null) {
            option1.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> top.update());
        }
        if (option2 != null) {
            option2.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> top.update());
        }
        if (option3 != null) {
            option3.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> top.update());
        }
    }

    private void initialize(Button button) {
        button.setPrefSize(1200, 100);
        button.setScaleX(0.8);
        button.setScaleY(0.8);
        button.setFont(textFont);
        button.setAlignment(Pos.BASELINE_LEFT);
        button.setTextFill(Color.WHITE);
        button.setBackground(buttonBG);
        button.setOnMouseEntered(event -> button.setBackground(buttonHighlight));
        button.setOnMouseExited(event -> button.setBackground(buttonBG));
        highlightOnClick(button);
    }

}
