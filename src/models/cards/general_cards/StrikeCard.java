package models.cards.general_cards;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.io.InputStream;
import java.net.URL;

public class StrikeCard extends AttackCard {
    public StrikeCard() {
        super("Strike", "Deal 6 damage.", 1, 6, CardRarity.COMMON, CardGrave.DISCARD);
        String path = "";

        path = this.getClass().getResource(this.getClass().getSimpleName() + ".class").toString();

        path = path.replaceFirst("out/production/Java-2024-2_SlayTheSpire/models", "resources/images");
        path = path.replaceFirst(".*images", "/images");
        path = path.replaceFirst(".class", ".png");


        setImagePath(path);
//        setImagePath("/images/cards/general_cards/StrikeCard.png");
//        String path = String.valueOf(this.getClass().getResourceAsStream(this.getClass().getName() + ".png")).substring(7);
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {

        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}