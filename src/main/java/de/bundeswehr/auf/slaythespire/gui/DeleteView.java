package de.bundeswehr.auf.slaythespire.gui;

import de.bundeswehr.auf.slaythespire.controller.listener.DeleteEventListener;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.load_save.SaveFilePreview;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Die Klasse 'DeleteMenuView' repräsentiert eine grafische Benutzeroberfläche (GUI)
 * für das Löschen von gespeicherten Spielständen. Sie zeigt eine Liste von Vorschauen gespeicherter
 * Dateien an und ermöglicht dem Benutzer das Löschen eines ausgewählten Spielstands oder das Zurückkehren
 * zum vorherigen Menü.
 *
 * <p>Die Klasse erbt von 'VBox' und enthält eine Liste von 'SaveFilePreview',
 * die die gespeicherten Dateien darstellen. Mit dieser GUI kann der Benutzer die gespeicherten Dateien
 * anzeigen und eine auswählen, um sie zu löschen.</p>
 *
 * <p>Die Klasse benötigt einen 'DeleteEventListener', um auf Ereignisse wie das Klicken
 * auf die Schaltflächen "Zurück" und "Löschen" zu reagieren.</p>
 *
 * @author Warawa Alexander
 */
public class DeleteView extends VBox {

    private final DeleteEventListener deleteEventListener;
    private final ListView<SaveFilePreview> listView = new ListView<>();
    private int selectedIndex = 0;

    /**
     * Konstruktor für die Klasse 'DeleteMenuView'.
     *
     * @param deleteEventListener Ein Listener für delete und back Ereignisse.
     * @param saveFilePreviewList Eine Liste von 'SaveFilePreview' Objekten, die die
     *                            gespeicherten Spielstände repräsentieren.
     */
    public DeleteView(DeleteEventListener deleteEventListener, List<SaveFilePreview> saveFilePreviewList) {
        setAlignment(Pos.TOP_CENTER);

        initMain();

        Label header = new Label("Delete Menu");
        header.getStyleClass().add("header-label");

        HBox bottomBox = bottomSelection();

        this.deleteEventListener = deleteEventListener;

        for (SaveFilePreview preview : saveFilePreviewList) {
            listView.getItems().add(preview);
        }

        initListeners();

        getChildren().addAll(header, listView, bottomBox);
    }

    private HBox bottomSelection() {
        HBox bottom = new HBox();
        Button backButton = new Button("Back");
        Button deleteButton = new Button("Delete");
        backButton.getStyleClass().add("back-button");
        deleteButton.getStyleClass().add("back-button");

        backButton.setOnAction(event -> deleteEventListener.onBackButtonClick());
        deleteButton.setOnAction(event -> deleteEventListener.onDeleteButtonClick(selectedIndex));

        bottom.getChildren().addAll(backButton, deleteButton);
        return bottom;
    }

    private void handleMouseClick(MouseEvent event) {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            selectedIndex = index;
        }
    }

    private void initListeners() {
        listView.setOnMouseClicked(this::handleMouseClick);
    }

    private void initMain() {
        setBackground(new Background(GuiHelper.backgroundInHD("/images/backgrounds/load.png")));

        setSpacing(10);
        setPadding(new Insets(30));
    }

}
