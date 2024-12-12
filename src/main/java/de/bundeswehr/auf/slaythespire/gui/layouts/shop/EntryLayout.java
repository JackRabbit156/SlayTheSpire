package de.bundeswehr.auf.slaythespire.gui.layouts.shop;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.ConsoleAssistant;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import de.bundeswehr.auf.slaythespire.gui.ShopView;


public class EntryLayout extends HBox {

    private final String playerImagePath;
    private final ShopView parentView;
    private ImageView merchantImgView;


    public EntryLayout(ShopView parentView, String playerImagePath) {
        this.parentView = parentView;
        this.playerImagePath = playerImagePath;
        init();
    }

    public void init() {
        initLeft();
        initRight();
    }

    private void initLeft() {
        VBox player = new VBox();
        HBox.setHgrow(player, Priority.ALWAYS);
        player.setAlignment(Pos.CENTER);
        player.setTranslateY(150);
        player.setTranslateX(150);
        player.getChildren().add(getPlayerImageView());
        getChildren().add(player);
    }

    private void initRight() {
        VBox merchant = new VBox();
        setMerchantImageView();
        HBox.setHgrow(merchant, Priority.ALWAYS);
        merchant.setAlignment(Pos.CENTER);
        merchant.setTranslateY(150);
        merchant.setTranslateX(-100);

        this.merchantImgView.setOnMouseClicked(event -> {
            ConsoleAssistant.log("Clicked on Merchant");
            onMerchantClick();
            this.merchantImgView.setDisable(true);
        });

        GuiHelper.setHoverEffect(this.merchantImgView);

        merchant.getChildren().add(this.merchantImgView);
        getChildren().add(merchant);
    }

    private ImageView getPlayerImageView() {
        Image image = new Image(getClass().getResource(this.playerImagePath).toExternalForm());
        return new ImageView(image);
    }

    private void setMerchantImageView() {
        Image merchantImg = new Image(getClass().getResource("/images/shop/MerchantWithProps.png").toExternalForm());
        this.merchantImgView = new ImageView(merchantImg);
    }

    private void onMerchantClick() {
        this.parentView.onMerchantClick();
    }

}
