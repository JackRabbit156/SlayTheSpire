package de.bundeswehr.auf.slaythespire.model.enemy.act_one.boss;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Boss;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.the_guardian.ChargingUpEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.the_guardian.FierceBashEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.the_guardian.WhirlwindEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * https://slay-the-spire.fandom.com/wiki/The_Guardian
 *
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class TheGuardianBoss extends Boss {

    public TheGuardianBoss() {
        super("The Guardian", 240, 240);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new ChargingUpEnemyCard());
        deck.add(new FierceBashEnemyCard());
        deck.add(new WhirlwindEnemyCard());

        setEnemyDeck(deck);
    }

}
