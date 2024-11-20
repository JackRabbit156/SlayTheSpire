package view.gui;

import controller.listener.LoadEventListener;
import helper.GuiHelper;
import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.load_save_game_elements.SaveFilePreview;

import java.util.List;

public class LoadView extends VBox {

    private List<SaveFilePreview> saveFilePreviewList;

    private ListView<SaveFilePreview> listView = new ListView<>();
    private Label header;

    private LoadEventListener loadEventListener;



    public LoadView(LoadEventListener loadEventListener, List<SaveFilePreview> saveFilePreviewList) {
        setAlignment(Pos.TOP_CENTER);

        initMain();

        header = new Label("Save States");
        header.getStyleClass().add("header-label");

        HBox bottomBox = bottomSelection();


        this.loadEventListener = loadEventListener;
        this.saveFilePreviewList = saveFilePreviewList;

        for(SaveFilePreview preview : saveFilePreviewList){
            listView.getItems().add(preview);
        }

        initListeners();

        getChildren().addAll(header, listView, bottomBox);
    }

    private void initMain(){
        setBackground(new Background(GuiHelper.background("/images/backgrounds/loadViewBackground.png")));

        setSpacing(10);
        setPadding(new Insets(30));
    }

    private HBox bottomSelection() {
        HBox bottom = new HBox();
        Button backButton = new Button("Back");
        backButton.getStyleClass().add("back-button");

        backButton.setOnAction(event -> loadEventListener.onBackButtonClick());

        bottom.getChildren().addAll(backButton);
        return bottom;
    }


    private void initListeners(){
        listView.setOnMouseClicked(event -> handleMouseClick(event));
    }
    private void handleMouseClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Überprüfen auf Doppelklick
            int index = listView.getSelectionModel().getSelectedIndex();
            if (index >= 0) { // Überprüfen, ob ein gültiger Index ausgewählt ist
                loadEventListener.onSelectedItem(index); // Doppelklick-Logik
            }
        }
    }

    public void showLoadPreview(){

        for(SaveFilePreview listItem : saveFilePreviewList){


        }

        listView.getItems().addAll(saveFilePreviewList);
    }

}
