package de.bundeswehr.auf.slaythespire.gui;

import de.bundeswehr.auf.slaythespire.controller.listener.LoadEventListener;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import de.bundeswehr.auf.slaythespire.model.load_save.SaveFilePreview;

import java.util.List;

/**
 * Die Klasse 'LoadView' stellt eine GUI zum Laden gespeicherter Spielstände bereit.
 * Sie zeigt eine Liste von 'SaveFilePreview' Objekten an und ermöglicht es dem Benutzer,
 * durch Doppelklick einen Spielstand zu laden oder zum vorherigen Menü zurückzukehren.
 *
 * @author Warawa Alexander
 */
public class LoadView extends VBox {

    private List<SaveFilePreview> saveFilePreviewList;

    private ListView<SaveFilePreview> listView = new ListView<>();
    private Label header;

    private LoadEventListener loadEventListener;

    /**
     * Konstruktor für die 'LoadView'.
     *
     * @param loadEventListener Ein Listener für Load-Ereignisse.
     * @param saveFilePreviewList Eine Liste von 'SaveFilePreview' Objekten.
     */
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
}
