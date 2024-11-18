package view.gui;

import controller.cli.MapViewController;
import controller.gui.MapController;
import controller.listener.LoadEventListener;
import helper.ConsoleAssistent;
import helper.GuiHelper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.card.DeckFactory;
import models.card.card_structure.Card;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.load_save_game_elements.GameSaveManager;
import models.load_save_game_elements.SaveFilePreview;
import models.player.IroncladPlayer;
import models.player.SilentPlayer;
import models.player.player_structure.Player;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        listView.getSelectionModel().selectedIndexProperty().addListener((observable, oldIndex, newIndex) -> {
            if (newIndex.intValue() != -1) { // Check if a valid selection is made
                loadEventListener.onSelectedItem(newIndex.intValue());
            }
        });
    }

    public void showLoadPreview(){
        listView.getItems().addAll(saveFilePreviewList);
    }

}
