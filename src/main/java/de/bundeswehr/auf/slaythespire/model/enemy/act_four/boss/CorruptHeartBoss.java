package de.bundeswehr.auf.slaythespire.model.enemy.act_four.boss;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Boss;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.boss.corrupt_heart.BloodShotsEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.boss.corrupt_heart.EchoEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class CorruptHeartBoss extends Boss {

    public CorruptHeartBoss() {
        super("CorruptHeart", 750, 750);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new BloodShotsEnemyCard());
        deck.add(new EchoEnemyCard());

        setEnemyDeck(deck);
    }

}
