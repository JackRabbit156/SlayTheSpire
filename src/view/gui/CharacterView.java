package view.gui;

import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import models.player.player_structure.Player;

public class CharacterView {
    private BorderPane ground = new BorderPane();
    private HBox buttonBox = new HBox();
    private HBox right = new HBox();
    private HBox left = new HBox();
    private VBox wrap = new VBox();
    private VBox infoBox = new VBox();
    private Background icBG = new Background(GuiHelper.background("/images/backgrounds/ironcladPortrait.jpg"));
    private Background slBG = new Background(GuiHelper.background("/images/backgrounds/silentPortrait.jpg"));
    private Background icHighlight = new Background(GuiHelper.background("/images/buttons/ironcladButtonHighlighted.png"));
    private Background slHighlight = new Background(GuiHelper.background("/images/buttons/silentButtonHighlighted.png"));
    private Background slButtonBG = new Background(GuiHelper.background("/images/buttons/silentButton.png"));
    private Background icButtonBG = new Background(GuiHelper.background("/images/buttons/ironcladButton.png"));
    private Text text = new Text("Choose your Character");
    private Text head = new Text();
    private Text hp = new Text();
    private ToggleButton ic = new ToggleButton();
    private ToggleButton sl = new ToggleButton();
    private Button backs = new Button();
    private Button embark = new Button();
    private Player player;

    public CharacterView(){
        initButtons();
    }

    private void initButtons() {

        ic.setBackground(new Background(GuiHelper.background("/images/buttons/ironcladButton.png")));
        ic.setPrefSize(120, 120);


        sl.setBackground(new Background(GuiHelper.background("/images/buttons/silentButton.png")));
        sl.setPrefSize(120, 120);



        embark.setText("Embark");
        embark.setBackground(new Background(GuiHelper.background("/images/buttons/confirmButton.png")));
        embark.setFont(Font.font("/resources/font/kreon/static/Kreon-Bold.ttf", 30));
        embark.setTextFill(Color.WHITE);
        embark.setPrefSize(200, 200);
        embark.setOnMouseEntered(event -> {
            embark.setBackground(new Background(
                    GuiHelper.background("/images/buttons/confirmButtonSelected.png")));
        });
        embark.setOnMouseExited(event -> {
            embark.setBackground(new Background(GuiHelper.background("/images/buttons/confirmButton.png")));
        });

        backs.setText("Back");
        backs.setBackground(new Background(GuiHelper.background("/images/buttons/cancelButton.png")));
        backs.setFont(Font.font("/resources/font/kreon/static/Kreon-Bold.ttf", 30));
        backs.setTextFill(Color.WHITE);
        backs.setPrefSize(220, 200);
        backs.setOnMouseEntered(event -> {
            backs.setBackground(new Background(
                    GuiHelper.background("/images/buttons/cancelButtonSelected.png")));
        });
        backs.setOnMouseExited(event -> {
            backs.setBackground(new Background(GuiHelper.background("/images/buttons/cancelButton.png")));
        });

        text.setFont(Font.font("/resources/font/kreon/static/Kreon-Bold.ttf", 30));
        text.setFill(Color.WHITE);

        head.setFont(Font.font("/resources/font/kreon/static/Kreon-Bold.ttf", 44));
        head.setFill(Color.GOLD);
        head.setTextAlignment(TextAlignment.LEFT);

        hp.setFont(Font.font("/resources/font/kreon/static/Kreon-Bold.ttf", 30));
        hp.setFill(Color.RED);
        hp.setTextAlignment(TextAlignment.LEFT);
    }

    public ToggleButton getIc() {
        return ic;
    }

    public ToggleButton getSl() {
        return sl;
    }

    public Button getBacks() {
        return backs;
    }

    public Button getEmbark() {
        return embark;
    }

    public Background getIcButtonBG() {
        return icButtonBG;
    }

    public Background getSlButtonBG() {
        return slButtonBG;
    }

    public BorderPane display() {
        left.getChildren().clear();
        right.getChildren().clear();
        wrap.getChildren().clear();
        buttonBox.getChildren().clear();
        ground.getChildren().clear();
        infoBox.getChildren().clear();

        ic.setOnMouseEntered(event -> {
            ic.setBackground(icHighlight);
        });
        ic.setOnMouseExited(event -> {
            if(!ic.isSelected()) { ic.setBackground(icButtonBG); }
        });

        sl.setOnMouseEntered(event -> {
            sl.setBackground(slHighlight);
        });
        sl.setOnMouseExited(event -> {
                    if(!sl.isSelected()) { sl.setBackground(slButtonBG); }
        });

        ground.setBackground(new Background(GuiHelper.background("/images/backgrounds/MainMenuBG.png")));

        left.setAlignment(Pos.BASELINE_LEFT);
        left.getChildren().add(backs);

        right.setAlignment(Pos.BASELINE_RIGHT);
        right.getChildren().add(embark);

        HBox.setHgrow(left, Priority.ALWAYS);
        HBox.setHgrow(ic, Priority.ALWAYS);
        HBox.setHgrow(sl, Priority.ALWAYS);
        HBox.setHgrow(right, Priority.ALWAYS);

        buttonBox.getChildren().addAll(left, ic, sl, right);
        buttonBox.setAlignment(Pos.CENTER);

        wrap.getChildren().addAll(text, buttonBox);
        wrap.setAlignment(Pos.CENTER);

        ground.setBottom(wrap);

        return ground;
    }
    //TODO: HP Alignment to left
    public void selectIC(){
        infoBox.getChildren().clear();
        sl.setSelected(false);

        head.setText("\nThe Ironclad");
        hp.setText("\nHP: 80/80");
        text.setText(" \n\tThe remaining soldier of the Ironclads.\n\tSold his soul to harness demonic energies.");
        infoBox.setBackground(Background.EMPTY);
        infoBox.setAlignment(Pos.BASELINE_RIGHT);
        infoBox.getChildren().addAll(head, text, hp);
        ground.setLeft(infoBox);
        ground.setBackground(icBG);
        ic.isSelected();
    }

    public void selectSL(){
        infoBox.getChildren().clear();
        ic.setSelected(false);

        head.setText("\nThe Silent");
        hp.setText("\nHP: 70/70");
        text.setText(" \n\tA deadly huntress from the foglands. \n\tEradicates foes with daggers and poisons.");
        infoBox.setBackground(Background.EMPTY);
        infoBox.setAlignment(Pos.BASELINE_RIGHT);
        infoBox.getChildren().addAll(head, text, hp);
        ground.setLeft(infoBox);
        ground.setBackground(slBG);
        sl.isSelected();
    }
}
