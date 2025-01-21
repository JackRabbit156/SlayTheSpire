package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.controller.listener.LoadEventListener;
import de.bundeswehr.auf.slaythespire.gui.LoadView;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.Model;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.load_save.GameSaveManager;
import de.bundeswehr.auf.slaythespire.model.load_save.SaveFilePreview;
import de.bundeswehr.auf.slaythespire.model.player.IroncladPlayer;
import de.bundeswehr.auf.slaythespire.model.player.SilentPlayer;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.potion.PotionFactory;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.RelicFactory;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse verwaltet den Ladevorgang von gespeicherten Spielen im Menü.
 * Sie bietet Funktionen zum Anzeigen von Speicherdateien und Laden von Spielen
 * von Speicherdateien.
 *
 * @author Warawa Alexander
 */
public class LoadController implements Controller, LoadEventListener {

    private final GameSaveManager gameSaveManager;
    private final LoadView loadView;
    private Player player;
    private final Stage primaryStage;

    public LoadController(Stage primaryStage) {
        this.primaryStage = primaryStage;

        gameSaveManager = new GameSaveManager();

        List<SaveFilePreview> saveFilePreviewList = saveFilePreviewList();
        this.loadView = new LoadView(this, saveFilePreviewList);
    }

    public LoadController(Player player) {
        this.player = player;
        this.primaryStage = player.getPrimaryStage();

        gameSaveManager = new GameSaveManager();

        List<SaveFilePreview> saveFilePreviewList = saveFilePreviewList();
        this.loadView = new LoadView(this, saveFilePreviewList);
    }

    public LoadView getLoadView() {
        return loadView;
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

    private void readDeck(Map<String, String> gameData, Player player) {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; gameData.get("card" + i) != null; i++) {
            String cardName = gameData.get("card" + i);
            Card card = DeckFactory.cardFor(cardName);
            deck.add(card);
        }
        player.setDeck(deck);
    }

    private void readGameSettings(Map<String, String> gameData) {
        String difficulty = gameData.get("difficulty");

        GameSettings.setDifficultyLevel(Model.ofDifficultyLevel(difficulty));

        GameSettings.lastSession = gameData.get("lastSession");
    }

    private Player readPlayer(Map<String, String> gameData) {
        Player player;
        // Getting the Character
        String playerTypeAsString = gameData.get("character");
        switch (PlayerType.valueOf(playerTypeAsString)) {
            case IRONCLAD:
                player = new IroncladPlayer(primaryStage);
                break;
            case SILENT:
                player = new SilentPlayer(primaryStage);
                break;
            default:
                LoggingAssistant.log("Unknown player type: " + playerTypeAsString, Color.RED);
                return null;
        }

        player.setCurrentHealth(Integer.parseInt(gameData.get("currentHealth")));
        player.setMaxHealth(Integer.parseInt(gameData.get("maxHealth")));

        player.setCurrentAct(Integer.parseInt(gameData.get("currentAct")));

        player.setCurrentField(gameData.get("field"));
        player.setGold(Integer.parseInt(gameData.get("gold")));
        return player;
    }

    private void readPotions(Map<String, String> gameData, Player player) {
        List<Potion> potions = new ArrayList<>();
        for (int i = 0; gameData.get("potion" + i) != null; i++) {
            String potionName = gameData.get("potion" + i);
            Potion potion = PotionFactory.potionFor(potionName);
            potions.add(potion);
        }
        player.setPotions(potions);
    }

    private void readRelic(Map<String, String> gameData, Player player) {
        List<Relic> relics = new ArrayList<>();
        for (int i = 0; gameData.get("relic" + i) != null; i++) {
            String relicName = gameData.get("relic" + i);
            Relic relic = RelicFactory.relicFor(relicName);
            relics.add(relic);
        }
        player.setRelics(relics);
    }

    private void readStatistics(Map<String, String> gameData) {
        int receivedGoldStats = Integer.parseInt(gameData.get("receivedGoldStats"));
        int receivedDamageStats = Integer.parseInt(gameData.get("receivedDamageStats"));
        int distributedDamageStats = Integer.parseInt(gameData.get("distributedDamageStats"));
        int energySpentStats = Integer.parseInt(gameData.get("energySpentStats"));

        GameSettings.setStats(receivedGoldStats, receivedDamageStats, distributedDamageStats, energySpentStats);
    }

    private void readTimePlayed(Map<String, String> gameData) {
        GameSettings.setTimerSeconds(Integer.parseInt(gameData.get("seconds")));
        GameSettings.setTimerMinutes(Integer.parseInt(gameData.get("minutes")));
        GameSettings.setTimerHours(Integer.parseInt(gameData.get("hours")));
    }

    /**
     * Startet das Spiel mit den Daten aus der angegebenen Speicherdatei.
     *
     * @param id Die ID der Speicherdatei, die geladen werden soll.
     */
    private void startLoadedGame(int id) {
        Map<String, String> gameData = gameSaveManager.loadGame(id);
        Player player = readPlayer(gameData);
        if (player == null) {
            return;
        }
        readDeck(gameData, player);
        readPotions(gameData, player);
        readRelic(gameData, player);
        readGameSettings(gameData);
        readStatistics(gameData);

        GuiHelper.Scenes.startMapScene(player);

        GameSettings.restartTimer();
        readTimePlayed(gameData);
    }

}
