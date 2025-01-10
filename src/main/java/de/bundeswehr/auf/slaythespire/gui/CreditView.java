package de.bundeswehr.auf.slaythespire.gui;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.geometry.Pos;
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

/**
 * Darstellungskomonente der Credits
 *
 * @author Loeschner, Marijan
 */
public class CreditView {

    private final Button back = new Button();
    private final VBox output = new VBox();

    public CreditView() {
        initButton();
    }

    /**
     * Methode die die Credits darstellt
     *
     * @return output VBox
     */
    public VBox display() {
        output.setBackground(new Background(GuiHelper.backgroundInHD("/images/backgrounds/border.jpg")));
        Text creditText = new Text("Re-Engineered by:\nDaniel Willig\nVladislav Keil\nAlexander Warawa\nMarijan Löschner");
        creditText.setFont(Font.loadFont(getClass().getResourceAsStream(GuiHelper.DEFAULT_FONT_BOLD), 24));
        creditText.setFill(Color.WHITE);
        creditText.setTextAlignment(TextAlignment.CENTER);
        ImageView creditImage = new ImageView(new Image(Objects.requireNonNull(
                GuiHelper.class.getResource("/images/backgrounds/logo.png")).toExternalForm()));

        output.setAlignment(Pos.CENTER);
        output.getChildren().clear();
        output.getChildren().addAll(creditImage, creditText, back);

        return output;
    }

    public Button getBackButton() {
        return back;
    }

    public VBox getOutput() {
        return output;
    }

    public void initButton() {
        back.setText("Back");
        back.setTextFill(Color.WHITE);
        back.setFont(Font.font(GuiHelper.DEFAULT_FONT_BOLD, 24));
        back.setTextAlignment(TextAlignment.CENTER);
        back.setBackground(new Background(GuiHelper.backgroundInHD("/images/buttons/end_turn.png")));
        back.setMinSize(200, 200);

        back.setOnMouseEntered(event -> back.setBackground(new Background(GuiHelper.backgroundInHD("/images/buttons/end_turn_glow.png"))));
        back.setOnMouseExited(event -> back.setBackground(new Background(GuiHelper.backgroundInHD("/images/buttons/end_turn.png"))));
    }

}
