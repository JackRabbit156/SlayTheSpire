package controller.gui;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.gui.EventView;

public class EventController {
    private Stage eventStage;
    Image img = new Image("/images/event/act_one/BigFishEvent.png");
    private EventView ev = new EventView(img, "Big Fish", "Bla Bla Bla");

    public BorderPane getEvent(Stage stage){

        eventStage = stage;
        return ev.display();
    }
}
