package view.gui;

import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

public class CreditView {
    private Button back = new Button();
    private VBox output = new VBox();

    public CreditView(){
        initButton();
    }

    public void initButton(){
        back.setText("Back");
        back.setTextFill(Color.WHITE);
        back.setFont(Font.font("/resources/font/kreon/static/Kreon-Bold.ttf", 24));
        back.setTextAlignment(TextAlignment.CENTER);
        back.setBackground(new Background(GuiHelper.background("/images/buttons/endTurnButton.png")));
        back.setMinSize(200, 200);

        back.setOnMouseEntered(event -> {
            back.setBackground(new Background(
                    GuiHelper.background("/images/buttons/endTurnButtonGlow.png")));
        });
        back.setOnMouseExited(event -> {
            back.setBackground(new Background(
                    GuiHelper.background("/images/buttons/endTurnButton.png")));
        });
    }

    public Button getBackButton() {
        return back;
    }

    public VBox getOutput() {
        return output;
    }

    public VBox display(){
        output.setBackground(new Background(GuiHelper.background("/images/backgrounds/greenBg.jpg")));
        Text creditText = new Text("Re-Engineered by:\nDaniel Willig\nVladislav Keil\nAlexander Warawa\nMarijan Loeschner");
        creditText.setFont(Font.loadFont(getClass().getResourceAsStream("/font/kreon/static/Kreon-Bold.ttf"), 24));
        creditText.setFill(Color.WHITE);
        creditText.setTextAlignment(TextAlignment.CENTER);
        ImageView creditImage = new ImageView(new Image(Objects.requireNonNull(
                GuiHelper.class.getResource("/images/backgrounds/STSLogo.png")).toExternalForm()));

        output.setAlignment(Pos.CENTER);
        output.getChildren().clear();
        output.getChildren().addAll(creditImage, creditText, back);

        return output;
    }

}
