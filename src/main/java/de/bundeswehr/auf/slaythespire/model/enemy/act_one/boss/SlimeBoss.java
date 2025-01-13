package de.bundeswehr.auf.slaythespire.model.enemy.act_one.boss;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Boss;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.slime_boss.GoopSprayEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.slime_boss.SlamEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * https://slay-the-spire.fandom.com/wiki/Slime_Boss
 *
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class SlimeBoss extends Boss {

    public SlimeBoss() {
        super("Slime Boss", 150, 150);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new GoopSprayEnemyCard());
        deck.add(new SlamEnemyCard());

        setEnemyDeck(deck);
    }

}
