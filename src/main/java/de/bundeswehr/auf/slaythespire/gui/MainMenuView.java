package de.bundeswehr.auf.slaythespire.gui;


import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;
import de.bundeswehr.auf.slaythespire.model.settings.structure.GameMode;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Darstellungskomponente für das hauptmenü
 *
 * @author Loeschner, Marijan
 */
public class MainMenuView {

    private final Button continueButton = new Button();
    private final Button credits = new Button();
    private final Button deleteSaveGame = new Button();
    private final Button easyDifficulty = new Button(DifficultyLevel.EASY.name());
    private final Font font = Font.font("Kreon", FontWeight.BOLD, 20);
    private final String fontPath = "/font/kreon/static/Kreon-Bold.ttf";
    private final Button hardcoreMode = new Button(GameMode.HARDCORE.name());
    private final String highlightPath = "/images/buttons/menu_highlight.png";
    private final Button loadGame = new Button();
    private final Button newGame = new Button();
    private final Button normalDifficulty = new Button(DifficultyLevel.NORMAL.name());
    private final Button normalMode = new Button(GameMode.NORMAL.name());
    private final Button[] modeButtons = {normalMode, hardcoreMode};
    private final Button quit = new Button();
    private final BorderPane selection = new BorderPane();
    private final Button superEasyDifficulty = new Button(DifficultyLevel.SUPER_EASY.name());
    private final Button[] diffModeButtons = {superEasyDifficulty, easyDifficulty, normalDifficulty, normalMode, hardcoreMode};
    private final Button[] diffButtons = {superEasyDifficulty, easyDifficulty, normalDifficulty};

    public MainMenuView() {
        initButtons();
    }

    public BorderPane display() {
        VBox grp = new VBox();
        grp.setAlignment(Pos.BOTTOM_LEFT);
        grp.getChildren().addAll(getNewGameButton(), getLoadGameButton(), getDelSaveGameButton(), getCreditsButton(), getQuitButton());
        selection.setLeft(grp);
        selection.setBackground(new Background(GuiHelper.background("/images/backgrounds/MainMenuBG.png")));
        return selection;
    }

    public VBox displayDiffModeMessage() {
        Text difficultyText = new Text("Difficulty");
        Text modeText = new Text("Mode");
        Text[] diffModeTexts = {difficultyText, modeText};

        for (Text diffModeText : diffModeTexts) {
            diffModeText.setFill(Color.WHITE);
            diffModeText.setTextAlignment(TextAlignment.CENTER);
            diffModeText.setFont(font);
        }
        modeText.setTranslateY(70);
        difficultyText.setTranslateY(130);

        VBox diffModeMessage = new VBox();
        diffModeMessage.setBackground(new Background(GuiHelper.background("/images/popup/popupBg.png")));
        diffModeMessage.setPrefSize(1800, 1000);
        diffModeMessage.setAlignment(Pos.CENTER);

        HBox difficultyButtons = new HBox();
        difficultyButtons.getChildren().addAll(getSuperEasyDifficulty(), getEasyDifficulty(), getNormalDifficulty());
        difficultyButtons.setAlignment(Pos.CENTER);
        difficultyButtons.setTranslateY(70);
        continueButton.setTranslateX(225);
        continueButton.setTranslateY(80);

        HBox modeButtons = new HBox();
        modeButtons.getChildren().addAll(getNormalMode(), getHardcoreMode(), continueButton);
        modeButtons.setTranslateX(-60);
        modeButtons.setAlignment(Pos.CENTER);

        diffModeMessage.getChildren().addAll(difficultyText, difficultyButtons, modeText, modeButtons);
        return diffModeMessage;
    }

    public Button getContinueButton() {
        return continueButton;
    }

    public Button getCreditsButton() {
        return credits;
    }

    public Button getDelSaveGameButton() {
        return deleteSaveGame;
    }

    public Button getEasyDifficulty() {
        return easyDifficulty;
    }

    public Button getHardcoreMode() {
        return hardcoreMode;
    }

    public Button getLoadGameButton() {
        return loadGame;
    }

    public Button getNewGameButton() {
        return newGame;
    }

    public Button getNormalDifficulty() {
        return normalDifficulty;
    }

    public Button getNormalMode() {
        return normalMode;
    }

    public Button getQuitButton() {
        return quit;
    }

    public Button getSuperEasyDifficulty() {
        return superEasyDifficulty;
    }

    public void initButtons() {
        for (Button diffModeButton : diffModeButtons) {
            diffModeButton.setTextFill(Color.WHITE);
            diffModeButton.setFont(font);
            diffModeButton.setAlignment(Pos.CENTER);
            diffModeButton.setBackground(new Background(GuiHelper.background("/images/buttons/endTurnButton.png")));
            diffModeButton.setPrefSize(240, 240);
        }

        continueButton.setText("Continue");
        continueButton.setTextFill(Color.WHITE);
        continueButton.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), 24));
        continueButton.setAlignment(Pos.CENTER);
        continueButton.setBackground(new Background(GuiHelper.background("/images/buttons/endTurnButton.png")));
        continueButton.setPrefSize(180, 120);
        continueButton.setOnMouseEntered(event -> continueButton.setBackground(new Background(GuiHelper.background("/images/buttons/endTurnButtonGlow.png"))));
        continueButton.setOnMouseExited(event -> continueButton.setBackground(new Background(GuiHelper.background("/images/buttons/endTurnButton.png"))));

        initialize(newGame, "New Game");
        initialize(loadGame, "Load Game");
        initialize(deleteSaveGame, "Delete Save");
        initialize(credits, "Credits");
        initialize(quit, "Quit");
    }

    public void setDifficultyButton(Button button) {
        for (Button diffButton : diffButtons) {
            diffButton.setBackground(new Background(GuiHelper.background("/images/buttons/endTurnButton.png")));
        }

        button.setBackground(new Background(GuiHelper.background("/images/buttons/endTurnButtonGlow.png")));
    }

    public void setModeButton(Button button) {
        for (Button modeButton : modeButtons) {
            modeButton.setBackground(new Background(GuiHelper.background("/images/buttons/endTurnButton.png")));
        }

        button.setBackground(new Background(GuiHelper.background("/images/buttons/endTurnButtonGlow.png")));
    }

    private void initialize(Button button, String text) {
        button.setText(text);
        button.setTextFill(Color.WHITE);
        button.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), 30));
        button.setAlignment(Pos.BASELINE_LEFT);
        button.setBackground(Background.EMPTY);
        button.setMinSize(200, 50);
        button.setOnMouseEntered(event -> button.setBackground(new Background(GuiHelper.background(highlightPath))));
        button.setOnMouseExited(event -> button.setBackground(Background.EMPTY));
    }

}
