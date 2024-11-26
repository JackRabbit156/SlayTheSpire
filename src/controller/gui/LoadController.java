package controller.gui;

import controller.listener.LoadEventListener;
import helper.GuiHelper;
import javafx.scene.Scene;
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
import models.potion.potion_structure.PotionCard;
import view.gui.LoadView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse verwaltet den Ladevorgang von gespeicherten Spielen im Menü.
 * Sie bietet Funktionen zum Anzeigen von Speicherdateien und Laden von Spielen
 *  von Speicherdateien.
 *
 * @author Warawa Alexander
 */
public class LoadController implements LoadEventListener {

    private GameSaveManager gameSaveManager;
    private LoadView loadView;

    private Stage primaryStage;
    private Player player;

    public LoadController(Stage primaryStage){
        this.primaryStage = primaryStage;

        gameSaveManager = new GameSaveManager();

        List<SaveFilePreview> saveFilePreviewList = saveFilePreviewList();
        this.loadView = new LoadView(this, saveFilePreviewList);

        if(saveFilePreviewList.isEmpty()){
            // TODO: View if there are no files
            return;
        }
    }

    public LoadController(Player player){
        this.player = player;
        this.primaryStage = player.getPrimaryStage();

        gameSaveManager = new GameSaveManager();

        List<SaveFilePreview> saveFilePreviewList = saveFilePreviewList();
        this.loadView = new LoadView(this, saveFilePreviewList);

        if(saveFilePreviewList.isEmpty()){
            // TODO: View if there are no files
            return;
        }
    }

    /**
     * Startet das Spiel mit den Daten aus der angegebenen Speicherdatei.
     *
     * @param id Die ID der Speicherdatei, die geladen werden soll.
     */
    private void startLoadedGame(int id){
        Map<String, String> gameData = gameSaveManager.loadGame(id);
        Player player = null;

        // Getting the Character
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

        // reading Deck
        List<Card> deck = new ArrayList<>();
        for(int i = 0; gameData.get("card"+i) != null; i++){
            String cardName = gameData.get("card"+i);

            Card card = DeckFactory.assignCard(cardName);
            deck.add(card);
        }

        player.setDeck(deck);

        // reading Potions
        List<PotionCard> potions = new ArrayList<>();
        for(int i = 0; gameData.get("potion"+i) != null; i++){
            String potionName = gameData.get("potion"+i);

            PotionCard potion = DeckFactory.assignPotion(potionName);
            potions.add(potion);
        }

        player.setPotionCards(potions);


        // assigning the right difficulty
        String difficulty = gameData.get("difficulty");

        switch (difficulty){
            case "SUPEREASY" : GameSettings.setDifficultyLevel(DifficultyLevel.SUPEREASY); break;
            case "EASY" : GameSettings.setDifficultyLevel(DifficultyLevel.EASY); break;
            case "NORMAL" : GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL); break;
            case "HARD" : GameSettings.setDifficultyLevel(DifficultyLevel.HARD); break;
            case "IMPOSSIBLE" : GameSettings.setDifficultyLevel(DifficultyLevel.IMPOSSIBLE); break;
        }

        GameSettings.lastSession = gameData.get("lastSession");

        // Setting stats
        int receivedGoldStats = Integer.parseInt(gameData.get("receivedGoldStats"));
        int receivedDamageStats = Integer.parseInt(gameData.get("receivedDamageStats"));
        int distributedDamageStats = Integer.parseInt(gameData.get("distributedDamageStats"));
        int energySpentStats = Integer.parseInt(gameData.get("energySpentStats"));;

        GameSettings.setStats(receivedGoldStats, receivedDamageStats, distributedDamageStats, energySpentStats);


        GuiHelper.Scenes.startMapScene(player);

        // Setting played Time
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

    public LoadView getLoadView(){
        return loadView;
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
    public void onBackButtonClick() {
        if(player == null) {
            GuiHelper.Scenes.startMainMenuScene(primaryStage);
        } else {
            GuiHelper.Scenes.startMapScene(player);
        }

    }
}
