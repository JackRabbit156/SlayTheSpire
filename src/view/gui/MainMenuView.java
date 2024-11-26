package view.gui;


import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import models.game_settings.structure.DifficultyLevel;
import models.game_settings.structure.GameMode;

public class MainMenuView {
    private BorderPane selection = new BorderPane();
    private String fontPath = "/font/kreon/static/Kreon-Bold.ttf";
    private String highlightPath = "/images/buttons/menu_highlight.png";
    private Button newGame = new Button();
    private Button loadGame = new Button();
    private Button delSaveGame = new Button();
    private Button credits = new Button();
    private Button quit = new Button();
    private Button yes = new Button("Yes");
    private Button no = new Button("No");

    private Button supereasyDifficulty = new Button(DifficultyLevel.SUPEREASY.name());
    private Button easyDifficulty = new Button(DifficultyLevel.EASY.name());
    private Button normalDifficulty = new Button(DifficultyLevel.NORMAL.name());
    private Button normalMode = new Button(GameMode.NORMAL.name());
    private Button hardcoreMode = new Button(GameMode.HARDCORE.name());
    private Button continueButton = new Button("Continue");
    private Button[] diffModeButtons = {supereasyDifficulty, easyDifficulty, normalDifficulty, normalMode, hardcoreMode};
    private Button[] diffButtons = {supereasyDifficulty, easyDifficulty, normalDifficulty};
    private Button[] modeButtons = {normalMode, hardcoreMode};

    private final Font font = Font.font("Kreon", FontWeight.BOLD, 20);


    private VBox msg = new VBox();
    private VBox diffModeMessage = new VBox();
    private HBox nrg = new HBox();


    private VBox diffModeButtonsVBox = new VBox();
    private HBox diffButtonsHBox = new HBox();
    private HBox modeButtonsHBox = new HBox();

    public MainMenuView(){
        initButtons();
    }

    public void initButtons(){

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

        delSaveGame.setText("Delete Save");
        delSaveGame.setTextFill(Color.WHITE);
        delSaveGame.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), 30));
        delSaveGame.setAlignment(Pos.BASELINE_LEFT);
        delSaveGame.setBackground(Background.EMPTY);
        delSaveGame.setMaxSize(200, 50);
        delSaveGame.setOnMouseEntered(event -> {
            delSaveGame.setBackground(new Background(GuiHelper.background(highlightPath)));
        });
        delSaveGame.setOnMouseExited(event -> {
            delSaveGame.setBackground(Background.EMPTY);
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

    public Button getNo() {
        return no;
    }

    public Button getYes() {
        return yes;
    }

    public Button getSupereasyDifficulty() {
        return supereasyDifficulty;
    }

    public Button getEasyDifficulty() {
        return easyDifficulty;
    }

    public Button getNormalDifficulty() {
        return normalDifficulty;
    }

    public Button getNormalMode() {
        return normalMode;
    }

    public Button getHardcoreMode() {
        return hardcoreMode;
    }
    public Button getContinueButton() {
        return continueButton;
    }

    public Button getNewGameButton(){
        return newGame;
    }

    public Button getLoadGameButton() {
        return loadGame;
    }

    public Button getDelSaveGameButton() {
        return delSaveGame;
    }

    public Button getCreditsButton() {
        return credits;
    }

    public Button getQuitButton() {
        return quit;
    }

    public BorderPane display(){
        VBox grp = new VBox();
        grp.setAlignment(Pos.BOTTOM_LEFT);
        grp.getChildren().addAll(getNewGameButton(), getLoadGameButton(), getDelSaveGameButton(), getCreditsButton(), getQuitButton());
        selection.setLeft(grp);
        selection.setBackground(new Background(GuiHelper.background("/images/backgrounds/MainMenuBG.png")));
        return selection;
    }

    public VBox displayQuitMessage(){
        Text text = new Text("Are you sure that you \nwant to Quit the Game?");
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

    public VBox displayDiffModeMessage(){
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

        diffButtonsHBox.getChildren().addAll(getSupereasyDifficulty(), getEasyDifficulty(), getNormalDifficulty());
        diffButtonsHBox.setAlignment(Pos.CENTER);
        diffButtonsHBox.setTranslateY(70);
        continueButton.setTranslateX(225);
        continueButton.setTranslateY(80);
        modeButtonsHBox.getChildren().addAll(getNormalMode(), getHardcoreMode(), continueButton);
        modeButtonsHBox.setTranslateX(-60);
        modeButtonsHBox.setAlignment(Pos.CENTER);

        return diffModeMessage;
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
