package view.gui;

import helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import models.event.Event;
import models.player.player_structure.Player;

/**
 * View für die Darstellung der Events, dynamisch aufgebaut. Es können Events über den Controller eingefügt werden,
 * diese erhalten dann ein Layout über diese View
 *
 * @author Loeschner, Marijan
 */
public class EventView {

    BorderPane layoutPane = new BorderPane();
    Background paneBG = new Background(GuiHelper.background("/images/backgrounds/greenBg.jpg"));
    HBox displayBox = new HBox();
    Background eventBG = new Background(GuiHelper.background("/images/event/event_layout/panel.png"));
    VBox leftBox = new VBox();
    VBox rightBox = new VBox();
    ImageView img = new ImageView();
    Label label = new Label();
    DropShadow dr = new DropShadow(8, Color.BLACK);
    Font labelFont = Font.loadFont(getClass().getResourceAsStream("/font/kreon/static/Kreon-Bold.ttf"), 44);
    Text story = new Text();
    Font textFont = Font.loadFont(getClass().getResourceAsStream("/font/kreon/static/Kreon-Bold.ttf"), 30);
    Button option1;
    Button option2;
    Button option3;
    Button leave = new Button("\t[Leave]");
    Background buttonBG = new Background(GuiHelper.background("/images/event/event_layout/disabledButton.png"));
    Background buttonHighlight = new Background(GuiHelper.background("/images/event/event_layout/enabledButton.png"));
    Event event;
    Player player;


    public EventView(Event event, Player player){
        leftBox.getChildren().clear();
        rightBox.getChildren().clear();
        displayBox.getChildren().clear();
        layoutPane.getChildren().clear();

        this.event = event;
        this.player = player;
        this.label.setText(event.getTitle());
        this.story.setText(event.getStory());
        this.option1 = event.getButton1(player);
        this.option2 = event.getButton2(player);
        this.option3 = event.getButton3(player);

        layoutPane.setBackground(paneBG);
        displayBox.setBackground(eventBG);
        displayBox.setScaleX(0.8);
        displayBox.setScaleY(0.8);

        img.setImage(event.getImage());

        img.setScaleX(0.8);
        img.setScaleY(0.8);
        img.setEffect(dr);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.getChildren().addAll(label, img);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        displayBox.getChildren().addAll(leftBox, rightBox);
        layoutPane.setCenter(displayBox);

    }

    private void initStyle(){
        label.setPadding(new Insets(0, 0, 330, 220));
        label.setFont(labelFont);
        label.setTextFill(Color.GOLDENROD);
        label.setEffect(dr);


        story.setFont(textFont);
        story.setFill(Color.WHITE);
        story.setTextOrigin(VPos.BOTTOM);
        story.setTextAlignment(TextAlignment.CENTER);
        story.setLineSpacing(50);

        if (option1 != null) {
            option1.setPrefSize(1200, 100);
            option1.setScaleX(0.8);
            option1.setScaleY(0.8);
            option1.setFont(textFont);
            option1.setAlignment(Pos.BASELINE_LEFT);
            option1.setTextFill(Color.WHITE);
            option1.setBackground(buttonBG);
            option1.setOnMouseEntered(event1 -> {
                option1.setBackground(buttonHighlight);
            });
            option1.setOnMouseExited(event1 -> {
                option1.setBackground(buttonBG);
            });
            option1.setOnMousePressed(event1 -> {
                option1.setBackground(buttonBG);
                option1.setScaleX(option1.getScaleX() * 0.99);
                option1.setScaleY(option1.getScaleY() * 0.9);
            });
            option1.setOnMouseReleased(event1 -> {
                option1.setBackground(buttonHighlight);
                option1.setScaleX(option1.getScaleX() * 1.01);
                option1.setScaleY(option1.getScaleY() * 1.1);
            });
        }

        if (option2 != null) {
            option2.setPrefSize(1200, 100);
            option2.setScaleX(0.8);
            option2.setScaleY(0.8);
            option2.setFont(textFont);
            option2.setAlignment(Pos.BASELINE_LEFT);
            option2.setTextFill(Color.WHITE);
            option2.setBackground(buttonBG);
            option2.setOnMouseEntered(event1 -> {
                option2.setBackground(buttonHighlight);
            });
            option2.setOnMouseExited(event1 -> {
                option2.setBackground(buttonBG);
            });
            option2.setOnMousePressed(event1 -> {
                option2.setBackground(buttonBG);
                option2.setScaleX(option2.getScaleX() * 0.99);
                option2.setScaleY(option2.getScaleY() * 0.9);
            });
            option2.setOnMouseReleased(event1 -> {
                option2.setBackground(buttonHighlight);
                option2.setScaleX(option2.getScaleX() * 1.01);
                option2.setScaleY(option2.getScaleY() * 1.1);
            });
        }

        if (option3 != null) {
            option3.setPrefSize(1200, 100);
            option3.setScaleX(0.8);
            option3.setScaleY(0.8);
            option3.setFont(textFont);
            option3.setAlignment(Pos.BASELINE_LEFT);
            option3.setTextFill(Color.WHITE);
            option3.setBackground(buttonBG);
            option3.setOnMouseEntered(event1 -> {
                option3.setBackground(buttonHighlight);
            });
            option3.setOnMouseExited(event1 -> {
                option3.setBackground(buttonBG);
            });
            option3.setOnMousePressed(event1 -> {
                option3.setBackground(buttonBG);
                option3.setScaleX(option3.getScaleX() * 0.99);
                option3.setScaleY(option3.getScaleY() * 0.9);
            });
            option3.setOnMouseReleased(event1 -> {
                option3.setBackground(buttonHighlight);
                option3.setScaleX(option3.getScaleX() * 1.01);
                option3.setScaleY(option3.getScaleY() * 1.1);
            });
        }

        leave.setPrefSize(1200, 100);
        leave.setScaleX(0.8);
        leave.setScaleY(0.8);
        leave.setFont(textFont);
        leave.setAlignment(Pos.BASELINE_LEFT);
        leave.setTextFill(Color.WHITE);
        leave.setBackground(buttonBG);
        leave.setOnMouseEntered(event1 -> {
            leave.setBackground(buttonHighlight);
        });
        leave.setOnMousePressed(event1 -> {
            leave.setBackground(buttonBG);
            leave.setScaleX(leave.getScaleX() * 0.99);
            leave.setScaleY(leave.getScaleY() * 0.9);
        });
        leave.setOnMouseReleased(event1 -> {
            leave.setBackground(buttonHighlight);
            leave.setScaleX(leave.getScaleX() * 1.01);
            leave.setScaleY(leave.getScaleY() * 1.1);
        });
        leave.setOnMouseExited(event1 -> {
            leave.setBackground(buttonBG);
        });
        leave.setOnMouseClicked(event1 -> {
            System.exit(1);
        });
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

    public Button getLeave() {
        return leave;
    }

    public BorderPane display() {
        initStyle();
        //TODO: Story müssen so angepasst werden, dass sie sich nach Buttonclick ändern.

        rightBox.getChildren().add(story);
        if (option1 != null) {
            rightBox.getChildren().add(option1);
            event.getButton1(player);
        }
        if (option2 != null) {
            rightBox.getChildren().add(option2);
            event.getButton2(player);
        }
        if (option3 != null) {
            rightBox.getChildren().add(option3);
            event.getButton3(player);
        }
        rightBox.getChildren().add(leave);

        return layoutPane;
    }

}
