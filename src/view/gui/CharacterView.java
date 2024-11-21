package view.gui;

import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CharacterView {
    private BorderPane ground = new BorderPane();
    private HBox buttonBox = new HBox();
    private HBox right = new HBox();
    private HBox left = new HBox();
    private VBox wrap = new VBox();
    private Background icBG = new Background(GuiHelper.background("/images/backgrounds/ironcladPortrait.jpg"));
    private Background slBG = new Background(GuiHelper.background("/images/backgrounds/silentPortrait.jpg"));
    private Text text = new Text("Choose your Character");
    private Button ic = new Button();
    private Button sl = new Button();
    private Button backs = new Button();
    private Button embark = new Button();

    public CharacterView(){
        initButtons();
    }

    private void initButtons() {

        ic.setBackground(new Background(GuiHelper.background("/images/buttons/ironcladButton.png")));
        ic.setPrefSize(120, 120);
        ic.setOnMouseEntered(event -> {
            ic.setBackground(new Background(
                    GuiHelper.background("/images/buttons/ironcladButtonHighlighted.png")));
        });
        ic.setOnMouseExited(event -> {
            ic.setBackground(new Background(GuiHelper.background("/images/buttons/ironcladButton.png")));
        });

        sl.setBackground(new Background(GuiHelper.background("/images/buttons/silentButton.png")));
        sl.setPrefSize(120, 120);
        sl.setOnMouseEntered(event -> {
            sl.setBackground(new Background(
                    GuiHelper.background("/images/buttons/silentButtonHighlighted.png")));
        });
        sl.setOnMouseExited(event -> {
            sl.setBackground(new Background(GuiHelper.background("/images/buttons/silentButton.png")));
        });

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

    }

    public Button getIc() {
        return ic;
    }

    public Button getSl() {
        return sl;
    }

    public Button getBacks() {
        return backs;
    }

    public Button getEmbark() {
        return embark;
    }

    public BorderPane display() {
        left.getChildren().clear();
        right.getChildren().clear();
        wrap.getChildren().clear();
        buttonBox.getChildren().clear();
        ground.getChildren().clear();

        ground.setBackground(new Background(GuiHelper.background("/images/backgrounds/MainMenuBG.png")));

        left.setAlignment(Pos.BASELINE_LEFT);
        left.getChildren().add(backs);

        right.setAlignment(Pos.BASELINE_RIGHT);
        right.getChildren().add(embark);

        HBox.setHgrow(left, Priority.ALWAYS);
        HBox.setHgrow(ic, Priority.ALWAYS);
        HBox.setHgrow(sl, Priority.ALWAYS);
        HBox.setHgrow(right, Priority.ALWAYS);

        text.setFont(Font.font("/resources/font/kreon/static/Kreon-Bold.ttf", 30));
        text.setFill(Color.WHITE);

        buttonBox.getChildren().addAll(left, ic, sl, right);
        buttonBox.setAlignment(Pos.CENTER);
        wrap.getChildren().addAll(text, buttonBox);
        wrap.setAlignment(Pos.CENTER);
        ground.setBottom(wrap);

        return ground;
    }

    public void selectIC(){
        ground.setBackground(icBG);
    }

    public void selectSL(){
        ground.setBackground(slBG);
    }
}
