package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.controller.listener.DeleteEventListener;
import de.bundeswehr.auf.slaythespire.controller.listener.LoadEventListener;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;
import de.bundeswehr.auf.slaythespire.model.load_save.GameSaveManager;
import de.bundeswehr.auf.slaythespire.model.load_save.SaveFilePreview;
import de.bundeswehr.auf.slaythespire.model.player.IroncladPlayer;
import de.bundeswehr.auf.slaythespire.model.player.SilentPlayer;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.gui.DeleteMenuView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse verwaltet die gespeicherten Spielen im Menü.
 * Sie bietet Funktionen zum Anzeigen von Speicherdateien und löschen von Speicherdateien.
 *
 * @author Warawa Alexander
 */
public class DeleteMenuController implements LoadEventListener, DeleteEventListener {

    private GameSaveManager gameSaveManager;
    private DeleteMenuView deleteMenuView;

    private Stage primaryStage;
    private Player player;

    public DeleteMenuController(Stage primaryStage){
        this.primaryStage = primaryStage;

        gameSaveManager = new GameSaveManager();

        List<SaveFilePreview> saveFilePreviewList = saveFilePreviewList();
        this.deleteMenuView = new DeleteMenuView(this, saveFilePreviewList);

    }

    /**
     * Startet das Spiel mit den Daten aus der angegebenen Speicherdatei.
     *
     * @param id Die ID der Speicherdatei, die geladen werden soll.
     */
    private void startLoadedGame(int id){
        Map<String, String> gameData = gameSaveManager.loadGame(id);
        Player player = null;

        String playerTypeAsString = gameData.get("character");
        switch (playerTypeAsString){
            case "IRONCLAD": player = new IroncladPlayer(primaryStage); break;
            case "SILENT": player = new SilentPlayer(primaryStage); break;
            default:
                System.out.println("Weird...");return;
        }

        player.setCurrentHealth(Integer.parseInt(gameData.get("currentHealth")));

        player.setCurrentAct(Integer.parseInt(gameData.get("currentAct")));

        player.setCurrentField(gameData.get("field"));
        player.setGold( Integer.parseInt(gameData.get("gold")));

        List<Card> deck = new ArrayList<>();
        for(int i = 0; gameData.get("card"+i) != null; i++){
            String cardName = gameData.get("card"+i);

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

    /**
     * Gibt eine Liste der Vorschau-Daten für die Speicherdateien zurück.
     *
     * @return Eine Liste von SaveFilePreview-Objekten.
     */
    public List<SaveFilePreview> saveFilePreviewList(){
        return gameSaveManager.listSaveFiles();
    }

    public DeleteMenuView getDeleteMenuView(){
        return deleteMenuView;
    }

    /**
     * Speichert das aktuelle Spiel des angegebenen Spielers.
     *
     * @param player Der Spieler, dessen Spiel gespeichert werden soll.
     */
    public void saveGame(Player player){
        deleteSaveFileWithName(GameSettings.lastSession);
        gameSaveManager.saveGame(player);
    }

    /**
     * Löscht die Speicherdatei mit dem angegebenen Namen.
     *
     * @param nameOfFile Der Name der zu löschenden Speicherdatei.
     */
    private void deleteSaveFileWithName(String nameOfFile){
        gameSaveManager.deleteSelectedSaveFile(nameOfFile);
    }

    /**
     * Löscht die Speicherdatei mit der angegebenen ID.
     *
     * @param id Die ID der zu löschenden Speicherdatei.
     */
    private void deleteSaveFileWithId(int id){
        gameSaveManager.deleteSelectedSaveFile(id);
    }

    @Override
    public void onSelectedItem(int id) {
        startLoadedGame(id);
    }

    @Override
    public void onDeleteButtonClick(int id) {
        GameSaveManager saveManager = new GameSaveManager();
        saveManager.deleteSelectedSaveFile(id);

        GuiHelper.Scenes.startMainMenuScene(primaryStage);
    }

    @Override
    public void onBackButtonClick() {
        if(player == null) {
            GuiHelper.Scenes.startMainMenuScene(primaryStage);
        } else {
            GuiHelper.Scenes.startMapScene(player);
        }

    }
}
