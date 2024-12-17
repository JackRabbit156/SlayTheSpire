package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.controller.listener.DeleteEventListener;
import de.bundeswehr.auf.slaythespire.gui.DeleteView;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.load_save.GameSaveManager;
import de.bundeswehr.auf.slaythespire.model.load_save.SaveFilePreview;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.stage.Stage;

import java.util.List;

/**
 * Diese Klasse verwaltet die gespeicherten Spielen im Menü.
 * Sie bietet Funktionen zum Anzeigen von Speicherdateien und löschen von Speicherdateien.
 *
 * @author Warawa Alexander
 */
public class DeleteController implements Controller, DeleteEventListener {

    private final DeleteView deleteView;
    private final GameSaveManager gameSaveManager;
    private final Stage primaryStage;

    public DeleteController(Stage primaryStage) {
        this.primaryStage = primaryStage;

        gameSaveManager = new GameSaveManager();

        List<SaveFilePreview> saveFilePreviewList = saveFilePreviewList();
        deleteView = new DeleteView(this, saveFilePreviewList);
    }

    public DeleteView getDeleteMenuView() {
        return deleteView;
    }

    @Override
    public void onBackButtonClick() {
        GuiHelper.Scenes.startMainMenuScene(primaryStage);
    }

    @Override
    public void onDeleteButtonClick(int id) {
        GameSaveManager saveManager = new GameSaveManager();
        saveManager.deleteSelectedSaveFile(id);

        GuiHelper.Scenes.startMainMenuScene(primaryStage);
    }

    /**
     * Gibt eine Liste der Vorschau-Daten für die Speicherdateien zurück.
     *
     * @return Eine Liste von SaveFilePreview-Objekten.
     */
    public List<SaveFilePreview> saveFilePreviewList() {
        return gameSaveManager.listSaveFiles();
    }

}
