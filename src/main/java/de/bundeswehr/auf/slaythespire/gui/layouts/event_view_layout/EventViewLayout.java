package de.bundeswehr.auf.slaythespire.gui.layouts.event_view_layout;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class EventViewLayout {

    Pane layoutPane = new BorderPane(); //TODO: Maybe auch ne Stackpane?
    HBox displayBox = new HBox();
    VBox innerBox = new VBox();
    ImageView img = new ImageView();
    Text title = new Text();
    Text flavorText = new Text();
    Button option1 = new Button();
    Button option2 = new Button();
    Button option3 = new Button();
    Button leave = new Button("[Leave]");

    public EventViewLayout(Image image, String titleString, String flavorString, String buttonText){
        layoutPane.setBackground(new Background(GuiHelper.background("")));
        this.img.setImage(image);
        this.title.setText(titleString);
        this.flavorText.setText(flavorString);
        this.option1.setText("[" + buttonText + "]");
    }

    public static void buildEvent(){

    }
}
