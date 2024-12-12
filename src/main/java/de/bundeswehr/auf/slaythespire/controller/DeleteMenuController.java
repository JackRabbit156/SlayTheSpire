package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.controller.listener.DeleteEventListener;
import de.bundeswehr.auf.slaythespire.controller.listener.LoadEventListener;
import de.bundeswehr.auf.slaythespire.gui.DeleteMenuView;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.load_save.GameSaveManager;
import de.bundeswehr.auf.slaythespire.model.load_save.SaveFilePreview;
import de.bundeswehr.auf.slaythespire.model.player.IroncladPlayer;
import de.bundeswehr.auf.slaythespire.model.player.SilentPlayer;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse verwaltet die gespeicherten Spielen im Menü.
 * Sie bietet Funktionen zum Anzeigen von Speicherdateien und löschen von Speicherdateien.
 *
 * @author Warawa Alexander
 */
public class DeleteMenuController implements Controller, LoadEventListener, DeleteEventListener {

    private final DeleteMenuView deleteMenuView;
    private final GameSaveManager gameSaveManager;
    private Player player;
    private final Stage primaryStage;

    public DeleteMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;

        gameSaveManager = new GameSaveManager();

        List<SaveFilePreview> saveFilePreviewList = saveFilePreviewList();
        this.deleteMenuView = new DeleteMenuView(this, saveFilePreviewList);

    }

    public DeleteMenuView getDeleteMenuView() {
        return deleteMenuView;
    }

    @Override
    public void onBackButtonClick() {
        if (player == null) {
            GuiHelper.Scenes.startMainMenuScene(primaryStage);
        }
        else {
            GuiHelper.Scenes.startMapScene(player);
        }

    }

    @Override
    public void onDeleteButtonClick(int id) {
        GameSaveManager saveManager = new GameSaveManager();
        saveManager.deleteSelectedSaveFile(id);

        GuiHelper.Scenes.startMainMenuScene(primaryStage);
    }

    @Override
    public void onSelectedItem(int id) {
        startLoadedGame(id);
    }

    /**
     * Gibt eine Liste der Vorschau-Daten für die Speicherdateien zurück.
     *
     * @return Eine Liste von SaveFilePreview-Objekten.
     */
    public List<SaveFilePreview> saveFilePreviewList() {
        return gameSaveManager.listSaveFiles();
    }

    /**
     * Speichert das aktuelle Spiel des angegebenen Spielers.
     *
     * @param player Der Spieler, dessen Spiel gespeichert werden soll.
     */
    public void saveGame(Player player) {
        deleteSaveFileWithName(GameSettings.lastSession);
        gameSaveManager.saveGame(player);
    }

    /**
     * Löscht die Speicherdatei mit der angegebenen ID.
     *
     * @param id Die ID der zu löschenden Speicherdatei.
     */
    private void deleteSaveFileWithId(int id) {
        gameSaveManager.deleteSelectedSaveFile(id);
    }

    /**
     * Löscht die Speicherdatei mit dem angegebenen Namen.
     *
     * @param nameOfFile Der Name der zu löschenden Speicherdatei.
     */
    private void deleteSaveFileWithName(String nameOfFile) {
        gameSaveManager.deleteSelectedSaveFile(nameOfFile);
    }

    /**
     * Startet das Spiel mit den Daten aus der angegebenen Speicherdatei.
     *
     * @param id Die ID der Speicherdatei, die geladen werden soll.
     */
    private void startLoadedGame(int id) {
        Map<String, String> gameData = gameSaveManager.loadGame(id);
        Player player = null;

        String playerTypeAsString = gameData.get("character");
        switch (PlayerType.valueOf(playerTypeAsString)) {
            case IRONCLAD:
                player = new IroncladPlayer(primaryStage);
                break;
            case SILENT:
                player = new SilentPlayer(primaryStage);
                break;
            default:
                LoggingAssistant.log(Color.RED, "Unknown player type: " + playerTypeAsString);
                return;
        }

        player.setCurrentHealth(Integer.parseInt(gameData.get("currentHealth")));

        player.setCurrentAct(Integer.parseInt(gameData.get("currentAct")));

        player.setCurrentField(gameData.get("field"));
        player.setGold(Integer.parseInt(gameData.get("gold")));

        List<Card> deck = new ArrayList<>();
        for (int i = 0; gameData.get("card" + i) != null; i++) {
            String cardName = gameData.get("card" + i);

            Card card = DeckFactory.assignCard(cardName);
            deck.add(card);
        }

        player.setDeck(deck);


        String difficulty = gameData.get("difficulty");

        GameSettings.setDifficultyLevel(DifficultyLevel.valueOf(difficulty));

        GameSettings.lastSession = gameData.get("lastSession");

        //MapViewController map = new MapViewController(player, true);
        GuiHelper.Scenes.startMapScene(player);

        GameSettings.restartTimer();

        GameSettings.setTimerSeconds(Integer.parseInt(gameData.get("seconds")));
        GameSettings.setTimerMinutes(Integer.parseInt(gameData.get("minutes")));
        GameSettings.setTimerHours(Integer.parseInt(gameData.get("hours")));
    }
}
