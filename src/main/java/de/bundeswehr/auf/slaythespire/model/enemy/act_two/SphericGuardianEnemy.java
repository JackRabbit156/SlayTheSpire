package de.bundeswehr.auf.slaythespire.model.enemy.act_two;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.spheric_guardian.ActivateEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.spheric_guardian.HardenEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.spheric_guardian.SlamSEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert den Gegner "Spheric Guardian" im zweiten Akt des Spiels.
 * Spheric Guardian hat spezifische Angriffsfähigkeiten, die bei jedem
 * Angriff zufällig ausgewählt werden.
 *
 * @author Warawa Alexander
 * @author OF Daniel Willig
 */
public class SphericGuardianEnemy extends Enemy {
    public SphericGuardianEnemy() {
        super("Spheric Guardian", 20, 20);
        setImagePath(new PathAssistent().toPath(this));
        setBlock(40);
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new SlamSEnemyCard());
        deck.add(new ActivateEnemyCard());
        deck.add(new HardenEnemyCard());

        setEnemyDeck(deck);
    }

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}
