package view.gui;

import helper.GuiHelper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class EventView {

    BorderPane layoutPane = new BorderPane(); //TODO: Maybe auch ne Stackpane?
    HBox displayBox = new HBox();
    VBox leftBox = new VBox();
    VBox rightBox = new VBox();
    ImageView img = new ImageView();
    Label label = new Label();
    Text story = new Text();
    Button option1 = new Button();
    Button option2 = new Button();
    Button option3 = new Button();
    Button leave = new Button("[Leave]");


    public EventView(Image image, String title, String flavor){
        layoutPane.setBackground(new Background(GuiHelper.background("/images/backgrounds/greenBg.jpg")));
        this.img.setImage(image);
        this.label.setText(title);
        this.story.setText(flavor);

    }
    public BorderPane display() {


        return layoutPane;
    }

}
