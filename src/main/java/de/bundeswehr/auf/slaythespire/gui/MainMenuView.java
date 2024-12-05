package de.bundeswehr.auf.slaythespire.gui;


import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
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
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;
import de.bundeswehr.auf.slaythespire.model.settings.structure.GameMode;

/**
 * Darstellungskomponente für das hauptmenü
 *
 * @author Loeschner, Marijan
 */
public class MainMenuView {

    private final Button continueButton = new Button("Continue");
    private final Button credits = new Button();
    private final Button deleteSaveGame = new Button();
    private final HBox diffButtonsHBox = new HBox();
    private final VBox diffModeButtonsVBox = new VBox();
    private final VBox diffModeMessage = new VBox();
    private final Button easyDifficulty = new Button(DifficultyLevel.EASY.name());
    private final Font font = Font.font("Kreon", FontWeight.BOLD, 20);
    private final String fontPath = "/font/kreon/static/Kreon-Bold.ttf";
    private final Button hardcoreMode = new Button(GameMode.HARDCORE.name());
    private final String highlightPath = "/images/buttons/menu_highlight.png";
    private final Button loadGame = new Button();
    private final HBox modeButtonsHBox = new HBox();
    private final VBox msg = new VBox();
    private final Button newGame = new Button();
    private final Button no = new Button("No");
    private final Button normalDifficulty = new Button(DifficultyLevel.NORMAL.name());
    private final Button normalMode = new Button(GameMode.NORMAL.name());
    private final Button[] modeButtons = {normalMode, hardcoreMode};
    private final HBox nrg = new HBox();
    private final Button quit = new Button();
    private final BorderPane selection = new BorderPane();
    private final Button superEasyDifficulty = new Button(DifficultyLevel.SUPER_EASY.name());
    private final Button[] diffModeButtons = { superEasyDifficulty, easyDifficulty, normalDifficulty, normalMode, hardcoreMode };
    private final Button[] diffButtons = { superEasyDifficulty, easyDifficulty, normalDifficulty };
    private final Button yes = new Button("Yes");

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

        diffModeMessage.setBackground(new Background(GuiHelper.background("/images/popup/popupBg.png")));
        diffModeMessage.setPrefSize(1800, 1000);
        diffModeMessage.setAlignment(Pos.CENTER);

        diffModeMessage.getChildren().addAll(difficultyText, diffButtonsHBox, modeText, modeButtonsHBox);

        diffButtonsHBox.getChildren().addAll(getSuperEasyDifficulty(), getEasyDifficulty(), getNormalDifficulty());
        diffButtonsHBox.setAlignment(Pos.CENTER);
        diffButtonsHBox.setTranslateY(70);
        continueButton.setTranslateX(225);
        continueButton.setTranslateY(80);
        modeButtonsHBox.getChildren().addAll(getNormalMode(), getHardcoreMode(), continueButton);
        modeButtonsHBox.setTranslateX(-60);
        modeButtonsHBox.setAlignment(Pos.CENTER);

        return diffModeMessage;
    }

    public VBox displayQuitMessage() {
        Text text = new Text("Are you sure that you\n" +
                "want to Quit the Game?");
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), 30));

        msg.setBackground(new Background(GuiHelper.background("/images/popup/popupBg.png")));
        msg.setPrefSize(900, 500);
        msg.setAlignment(Pos.CENTER);
        if (msg.getChildren().isEmpty()) {
            msg.getChildren().addAll(text, nrg);
            nrg.getChildren().addAll(getYes(), getNo());
            nrg.setAlignment(Pos.BOTTOM_CENTER);
        }
        return msg;
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

    public Button getNo() {
        return no;
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

    public Button getYes() {
        return yes;
    }

    public void initButtons() {
        for (Button diffModeButton : diffModeButtons) {
            diffModeButton.setTextFill(Color.WHITE);
            diffModeButton.setFont(font);
            diffModeButton.setAlignment(Pos.CENTER);
            diffModeButton.setBackground(new Background(GuiHelper.background("/images/buttons/endTurnButton.png")));
            diffModeButton.setPrefSize(240, 240);
        }

        continueButton.setTextFill(Color.WHITE);
        continueButton.setFont(font);
        continueButton.setAlignment(Pos.CENTER);
        continueButton.setBackground(new Background(GuiHelper.background("/images/buttons/endTurnButton.png")));
        continueButton.setPrefSize(120, 120);

        continueButton.setOnMouseEntered(event1 -> {
            continueButton.setBackground(new Background(
                    GuiHelper.background("/images/buttons/endTurnButtonGlow.png")));
        });
        continueButton.setOnMouseExited(event1 -> {
            continueButton.setBackground(new Background(
                    GuiHelper.background("/images/buttons/endTurnButton.png")));
        });

        no.setText("No");
        no.setTextFill(Color.WHITE);
        no.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), 24));
        no.setAlignment(Pos.CENTER);
        no.setBackground(new Background(GuiHelper.background("/images/buttons/endTurnButton.png")));
        no.setPrefSize(120, 120);

        no.setOnMouseEntered(event1 -> {
            no.setBackground(new Background(
                    GuiHelper.background("/images/buttons/endTurnButtonGlow.png")));
        });
        no.setOnMouseExited(event1 -> {
            no.setBackground(new Background(
                    GuiHelper.background("/images/buttons/endTurnButton.png")));
        });

        yes.setText("Yes");
        yes.setTextFill(Color.WHITE);
        yes.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), 24));
        yes.setAlignment(Pos.CENTER);
        yes.setBackground(new Background(GuiHelper.background("/images/buttons/endTurnButton.png")));
        yes.setPrefSize(120, 120);
        yes.setOnMouseEntered(event1 -> {
            yes.setBackground(new Background(
                    GuiHelper.background("/images/buttons/endTurnButtonGlow.png")));
        });
        yes.setOnMouseExited(event1 -> {
            yes.setBackground(new Background(
                    GuiHelper.background("/images/buttons/endTurnButton.png")));
        });

        newGame.setText("New Game");
        newGame.setTextFill(Color.WHITE);
        newGame.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), 30));
        newGame.setAlignment(Pos.BASELINE_LEFT);
        newGame.setBackground(Background.EMPTY);
        newGame.setMinSize(180, 50);
        newGame.setOnMouseEntered(event -> {
            newGame.setBackground(new Background(GuiHelper.background(highlightPath)));
        });
        newGame.setOnMouseExited(event -> {
            newGame.setBackground(Background.EMPTY);
        });

        loadGame.setText("Load Game");
        loadGame.setTextFill(Color.WHITE);
        loadGame.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), 30));
        loadGame.setAlignment(Pos.BASELINE_LEFT);
        loadGame.setBackground(Background.EMPTY);
        loadGame.setMinSize(180, 50);
        loadGame.setOnMouseEntered(event -> {
            loadGame.setBackground(new Background(GuiHelper.background(highlightPath)));
        });
        loadGame.setOnMouseExited(event -> {
            loadGame.setBackground(Background.EMPTY);
        });

        deleteSaveGame.setText("Delete Save");
        deleteSaveGame.setTextFill(Color.WHITE);
        deleteSaveGame.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), 30));
        deleteSaveGame.setAlignment(Pos.BASELINE_LEFT);
        deleteSaveGame.setBackground(Background.EMPTY);
        deleteSaveGame.setMaxSize(200, 50);
        deleteSaveGame.setOnMouseEntered(event -> {
            deleteSaveGame.setBackground(new Background(GuiHelper.background(highlightPath)));
        });
        deleteSaveGame.setOnMouseExited(event -> {
            deleteSaveGame.setBackground(Background.EMPTY);
        });

        credits.setText("Credits");
        credits.setTextFill(Color.WHITE);
        credits.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), 30));
        credits.setAlignment(Pos.BASELINE_LEFT);
        credits.setBackground(Background.EMPTY);
        credits.setMinSize(180, 50);
        credits.setOnMouseEntered(event -> {
            credits.setBackground(new Background(GuiHelper.background(highlightPath)));
        });
        credits.setOnMouseExited(event -> {
            credits.setBackground(Background.EMPTY);
        });

        quit.setText("Quit");
        quit.setTextFill(Color.WHITE);
        quit.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), 30));
        quit.setAlignment(Pos.BASELINE_LEFT);
        quit.setBackground(Background.EMPTY);
        quit.setMinSize(180, 50);
        quit.setOnMouseEntered(event -> {
            quit.setBackground(new Background(GuiHelper.background(highlightPath)));
        });
        quit.setOnMouseExited(event -> {
            quit.setBackground(Background.EMPTY);
        });
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

}
