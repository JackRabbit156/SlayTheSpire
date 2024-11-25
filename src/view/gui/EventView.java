package view.gui;

import helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import models.event.Event;
import models.event.act_one.BigFish;

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
    Button option1 = new Button();
    Button option2 = new Button();
    Button option3 = new Button();
    Button leave = new Button("\t[Leave]");
    Background buttonBG = new Background(GuiHelper.background("/images/event/event_layout/disabledButton.png"));
    Background buttonHighlight = new Background(GuiHelper.background("/images/event/event_layout/enabledButton.png"));
    Event event;


    public EventView(Event event){
        leftBox.getChildren().clear();
        rightBox.getChildren().clear();
        displayBox.getChildren().clear();
        layoutPane.getChildren().clear();

        this.event = event;
        this.label.setText(event.getTitle());
        this.story.setText(event.getStory());
        this.option1 = event.getButton1();
        this.option2 = event.getButton2();
        this.option3 = event.getButton3();

        layoutPane.setBackground(paneBG);
        displayBox.setBackground(eventBG);
        displayBox.setScaleX(0.8);
        displayBox.setScaleY(0.8);

        img.setImage(event.getImage());

        img.setScaleX(0.8);
        img.setScaleY(0.8);
        img.setX(100.0);
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

        option1.setPrefSize(1200, 100);
        option1.setScaleX(0.8);
        option1.setScaleY(0.8);
        option1.setFont(textFont);
        option1.setAlignment(Pos.BASELINE_LEFT);
        option1.setTextFill(Color.WHITE);
        option1.setBackground(buttonBG);

        option2.setPrefSize(1200, 100);
        option2.setScaleX(0.8);
        option2.setScaleY(0.8);
        option2.setFont(textFont);
        option2.setAlignment(Pos.BASELINE_LEFT);
        option2.setTextFill(Color.WHITE);
        option2.setBackground(buttonBG);

        option3.setPrefSize(1200, 100);
        option3.setScaleX(0.8);
        option3.setScaleY(0.8);
        option3.setFont(textFont);
        option3.setAlignment(Pos.BASELINE_LEFT);
        option3.setTextFill(Color.WHITE);
        option3.setBackground(buttonBG);

        leave.setPrefSize(1200, 100);
        leave.setScaleX(0.8);
        leave.setScaleY(0.8);
        leave.setFont(textFont);
        leave.setAlignment(Pos.BASELINE_LEFT);
        leave.setTextFill(Color.WHITE);
        leave.setBackground(buttonBG);
    }

    public BorderPane display() {
        initStyle();

        rightBox.getChildren().addAll(story, option1, option2, option3);
        rightBox.getChildren().add(leave);

        return layoutPane;
    }

}
