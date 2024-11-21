package view.gui;

import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

public class CharacterView {
    private BorderPane ground = new BorderPane();
    private HBox buttonBox = new HBox();
    private HBox right = new HBox();
    private HBox left = new HBox();
    private VBox wrap = new VBox();
    private Background bgImg = new Background(GuiHelper.background("/images/backgrounds/MainMenuBG.png"));
    private Background icImg = new Background(GuiHelper.background("/images/buttons/ironcladButton.png"));
    private Background slImg = new Background(GuiHelper.background("/images/buttons/silentButton.png"));
    private Background icBG = new Background(GuiHelper.background("/images/backgrounds/ironcladPortrait.jpg"));
    private Background slBG = new Background(GuiHelper.background("/images/backgrounds/silentPortrait.jpg"));
    private Background backBG = new Background(GuiHelper.background("/images/buttons/cancelButton.png"));
    private Background embarkBG = new Background(GuiHelper.background("/images/buttons/confirmButton.png"));
    private Text text = new Text("Choose your Character");
    private Button ic = new Button();
    private Button sl = new Button();
    private Button back = new Button();
    private Button embark = new Button();

    public CharacterView(){
        initButtons();
    }

    private void initButtons() {

        ic.setBackground(icImg);
        ic.setPrefSize(120, 120);
        ic.setOnMouseEntered(event -> {
            ic.setBackground(new Background(
                    GuiHelper.background("/images/buttons/ironcladButtonHighlighted.png")));
        });
        ic.setOnMouseExited(event -> {
            ic.setBackground(icImg);
        });

        sl.setBackground(slImg);
        sl.setPrefSize(120, 120);
        sl.setOnMouseEntered(event -> {
            sl.setBackground(new Background(
                    GuiHelper.background("/images/buttons/silentButtonHighlighted.png")));
        });
        sl.setOnMouseExited(event -> {
            sl.setBackground(slImg);
        });

        embark.setText("Embark");
        embark.setBackground(embarkBG);
        embark.setFont(Font.font("/resources/font/kreon/static/Kreon-Bold.ttf", 30));
        embark.setTextFill(Color.WHITE);
        embark.setPrefSize(200, 200);
        embark.setOnMouseEntered(event -> {
            embark.setBackground(new Background(
                    GuiHelper.background("/images/buttons/confirmButtonSelected.png")));
        });
        embark.setOnMouseExited(event -> {
            embark.setBackground(embarkBG);
        });

        back.setText("Back");
        back.setBackground(backBG);
        back.setFont(Font.font("/resources/font/kreon/static/Kreon-Bold.ttf", 30));
        back.setTextFill(Color.WHITE);
        back.setPrefSize(220, 200);
        back.setOnMouseEntered(event -> {
            back.setBackground(new Background(
                    GuiHelper.background("/images/buttons/cancelButtonSelected.png")));
        });
        back.setOnMouseExited(event -> {
            back.setBackground(backBG);
        });

    }


    public BorderPane display() {
        ground.setBackground(bgImg);

        left.setAlignment(Pos.BASELINE_LEFT);
        left.getChildren().add(back);

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
