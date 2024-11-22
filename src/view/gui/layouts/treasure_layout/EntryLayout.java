package view.gui.layouts.treasure_layout;

import helper.Color;
import helper.ConsoleAssistent;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import view.gui.TreasureView;

public class EntryLayout extends BorderPane {
    private String playerImagePath;
    private TreasureView treasureView;
    private BorderPane layout;
    private Image treasureImg;
    private ImageView treasureImgView;

    public EntryLayout(TreasureView treasureView, String playerImagePath) {
        this.treasureView = treasureView;
        this.playerImagePath = playerImagePath;
        this.layout = new BorderPane();
        setPickOnBounds(false);
        init();
    }

    public void init() {
        setTreasureImageView(false);
        initLeft();
        initRight();
        initBottom();
        setCenter(this.layout);
    }

    private void initBottom() {
        this.layout.setBottom(new Region());
    }

    private void initLeft() {
        HBox hBox = new HBox();
        HBox.setHgrow(hBox, Priority.ALWAYS);
        hBox.setAlignment(Pos.CENTER);
        hBox.setTranslateY(150);
        hBox.setTranslateX(150);
        hBox.getChildren().add(getPlayerImageView());
        this.layout.setLeft(hBox);
    }

    private void initRight() {
        HBox hBox = new HBox();
        HBox.setHgrow(hBox, Priority.ALWAYS);
        hBox.setAlignment(Pos.CENTER);
        hBox.setTranslateY(50);
        hBox.setTranslateX(-150);

        this.treasureImgView.setOnMouseClicked(event -> {
            ConsoleAssistent.println(Color.YELLOW, "Clicked on Treasure");
            setTreasureImageView(true);
            onTreasureClick();
            this.treasureImgView.setDisable(true);
        });

        hBox.getChildren().add(this.treasureImgView);
        this.layout.setRight(hBox);
    }

    private ImageView getPlayerImageView() {
        Image image = new Image(getClass().getResource(this.playerImagePath).toExternalForm());
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    private void setTreasureImageView(boolean open) {
        if (!open) {
            this.treasureImg = new Image(getClass().getResource("/images/treasure/treasure.png").toExternalForm());
        } else {
            ConsoleAssistent.println(Color.YELLOW, "Treasure Open");
            this.treasureImg = new Image(getClass().getResource("/images/treasure/treasureOpen.png").toExternalForm());
        }
        this.treasureImgView = new ImageView(this.treasureImg);
    }

    private void onTreasureClick(){
        this.treasureView.onTreasureClick();
        initRight();
    }
}
