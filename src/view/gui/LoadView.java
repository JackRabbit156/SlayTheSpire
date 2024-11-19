package view.gui;

import controller.listener.LoadEventListener;
import helper.GuiHelper;
import javafx.geometry.Insets;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import models.load_save_game_elements.SaveFilePreview;

import java.util.List;

public class LoadView extends VBox {

    private List<SaveFilePreview> saveFilePreviewList;

    private ListView<SaveFilePreview> listView = new ListView<>();
    private Label header = new Label("Save States");


    private LoadEventListener loadEventListener;

    public LoadView(LoadEventListener loadEventListener, List<SaveFilePreview> saveFilePreviewList) {
        initMain();

        this.loadEventListener = loadEventListener;
        this.saveFilePreviewList = saveFilePreviewList;

        for(SaveFilePreview preview : saveFilePreviewList){
            listView.getItems().add(preview);
        }

        initListeners();

        getChildren().addAll(header, listView);
    }

    private void initMain(){
        setBackground(new Background(GuiHelper.background("/images/loadView/background.jpg")));

        setSpacing(10);
        setPadding(new Insets(30));
    }

    private void initListeners(){
        listView.setOnMouseClicked(event -> handleMouseClick(event));
        /*listView.getSelectionModel().selectedIndexProperty().addListener((observable, oldIndex, newIndex) -> {
            if (newIndex.intValue() != -1) { // Check if a valid selection is made
                loadEventListener.onSelectedItem(newIndex.intValue());
            }
        });*/
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
        listView.getItems().addAll(saveFilePreviewList);
    }

}
