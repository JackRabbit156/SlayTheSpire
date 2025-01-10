package de.bundeswehr.auf.slaythespire.gui;


import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * Darstellungskomponente für das Hauptmenü
 *
 * @author Loeschner, Marijan
 */
public class MainMenuView {

    private static final String END_TURN = "/images/buttons/end_turn.png";
    private static final String END_TURN_GLOW = "/images/buttons/end_turn_glow.png";
    private static final String HIGHLIGHT = "/images/buttons/menu_highlighted.png";

    private final Button continueButton = new Button("Continue");
    private final Button credits = new Button("Credits");
    private final Button deleteSaveGame = new Button("Delete Save");
    private final Button easyDifficulty = new Button("Easy");
    private final Button hardDifficulty = new Button("Hard");
    private final Button hardcoreMode = new Button("Hardcore");
    private final Button impossibleDifficulty = new Button("Impossible");
    private final Button loadGame = new Button("Load Game");
    private final Button newGame = new Button("New Game");
    private final Button normalDifficulty = new Button("Normal");
    private final Button normalMode = new Button("Normal");
    private final Button quit = new Button("Quit");
    private final Button superEasyDifficulty = new Button("Super Easy");

    public MainMenuView() {
        initButtons();
    }

    public Pane createPopupContent() {
        Label difficultyText = new Label("Difficulty");
        initializeHeader(difficultyText);
        Label modeText = new Label("Mode");
        initializeHeader(modeText);
        modeText.setPadding(new Insets(25, 0, 0, 0));

        HBox difficultyButtons1 = new HBox(superEasyDifficulty, easyDifficulty, normalDifficulty);
        difficultyButtons1.setAlignment(Pos.CENTER);

        HBox difficultyButtons2 = new HBox(hardDifficulty, impossibleDifficulty);
        difficultyButtons2.setAlignment(Pos.CENTER);

        HBox modeButtons = new HBox(normalMode, hardcoreMode);
        modeButtons.setPadding(new Insets(0, 256, 0, 0));
        modeButtons.setAlignment(Pos.CENTER);

        HBox continuePane = new HBox(continueButton);
        continuePane.setAlignment(Pos.BOTTOM_RIGHT);

        VBox content = new VBox(difficultyText, difficultyButtons1, difficultyButtons2, modeText, modeButtons, continuePane);
        content.setPadding(new Insets(100, 0, 0, 0));
        content.setMaxSize(1000, 600);
        content.setAlignment(Pos.CENTER);
        content.setSpacing(5);

        BorderPane background = new BorderPane(content);
        background.setBackground(new Background(GuiHelper.backgroundInHD("/images/popup/popupBg.png")));
        background.setPrefSize(1920, 1080);
        return background;
    }

    public BorderPane display() {
        VBox menu = new VBox();
        menu.setAlignment(Pos.BOTTOM_LEFT);
        menu.getChildren().addAll(newGame, loadGame, deleteSaveGame, credits, quit);
        BorderPane background = new BorderPane();
        background.setBackground(new Background(GuiHelper.backgroundInHD("/images/backgrounds/main_menu.png")));
        background.setLeft(menu);
        return background;
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

    public Button getHardDifficulty() {
        return hardDifficulty;
    }

    public Button getHardcoreMode() {
        return hardcoreMode;
    }

    public Button getImpossibleDifficulty() {
        return impossibleDifficulty;
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
        initialize(superEasyDifficulty);
        initialize(easyDifficulty);
        initialize(normalDifficulty);
        initialize(hardDifficulty);
        initialize(impossibleDifficulty);
        initialize(normalMode);
        initialize(hardcoreMode);

        continueButton.setTextFill(Color.WHITE);
        continueButton.setFont(Font.loadFont(getClass().getResourceAsStream(GuiHelper.DEFAULT_FONT_BOLD), 24));
        continueButton.setBackground(new Background(GuiHelper.backgroundEndTurn(END_TURN)));
        continueButton.setMinSize(256, 100);
        continueButton.setOnMouseEntered(event -> continueButton.setBackground(new Background(GuiHelper.backgroundEndTurn(END_TURN_GLOW))));
        continueButton.setOnMouseExited(event -> continueButton.setBackground(new Background(GuiHelper.backgroundEndTurn(END_TURN))));

        initializeMenuButton(newGame);
        initializeMenuButton(loadGame);
        initializeMenuButton(deleteSaveGame);
        initializeMenuButton(credits);
        initializeMenuButton(quit);
    }

    public void setDifficultyButtons(Button button) {
        Button[] diffButtons = {superEasyDifficulty, easyDifficulty, normalDifficulty, hardDifficulty, impossibleDifficulty};
        for (Button diffButton : diffButtons) {
            diffButton.setBackground(new Background(GuiHelper.backgroundEndTurn(END_TURN)));
        }
        button.setBackground(new Background(GuiHelper.backgroundEndTurn(END_TURN_GLOW)));
    }

    public void setModeButtons(Button button) {
        Button[] modeButtons = {normalMode, hardcoreMode};
        for (Button modeButton : modeButtons) {
            modeButton.setBackground(new Background(GuiHelper.backgroundEndTurn(END_TURN)));
        }
        button.setBackground(new Background(GuiHelper.backgroundEndTurn(END_TURN_GLOW)));
    }

    private void initialize(Button button) {
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Kreon", FontWeight.BOLD, 20));
        button.setAlignment(Pos.CENTER);
        button.setBackground(new Background(GuiHelper.backgroundEndTurn(END_TURN)));
        button.setMinSize(256, 100);
    }

    private void initializeHeader(Label text) {
        text.setTextFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(Font.font("Kreon", FontWeight.BOLD, 20));
    }

    private void initializeMenuButton(Button button) {
        button.setTextFill(Color.WHITE);
        button.setFont(Font.loadFont(getClass().getResourceAsStream(GuiHelper.DEFAULT_FONT_BOLD), 30));
        button.setAlignment(Pos.BASELINE_LEFT);
        button.setBackground(Background.EMPTY);
        button.setMinSize(180, 50);
        button.setOnMouseEntered(event -> button.setBackground(new Background(GuiHelper.backgroundInHD(HIGHLIGHT))));
        button.setOnMouseExited(event -> button.setBackground(Background.EMPTY));
    }

}
