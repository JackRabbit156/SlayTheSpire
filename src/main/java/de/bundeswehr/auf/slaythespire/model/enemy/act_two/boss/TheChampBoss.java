package de.bundeswehr.auf.slaythespire.model.enemy.act_two.boss;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Boss;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ.DefensiveStanceEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ.ExecuteEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ.FaceSlapEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ.HeavySlashEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class TheChampBoss extends Boss {
    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     */
    public TheChampBoss() {
        super("The Champ", 420, 440);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new DefensiveStanceEnemyCard());
        deck.add(new FaceSlapEnemyCard());
        deck.add(new HeavySlashEnemyCard());
        deck.add(new ExecuteEnemyCard());

        setEnemyDeck(deck);
    }

}
