package de.bundeswehr.auf.slaythespire.model.potion;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.Model;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

/**
 * Die PotionFactory-Klasse ist verantwortlich für die Erstellung der Trankkarten eines Spielers.
 *
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public final class PotionFactory {

    private static final Random rnd = new Random();

    public static Potion copy(Potion potion) {
        return potionFor(potion.getClass().getSimpleName());
    }

    /**
     * Generiert eine zufällige Trankkarte aus einer Liste verfügbarer Tränke.
     * Zunächst wird eine Liste aller verfügbaren Tränke erstellt. Danach wird ein zufälliger Trank ausgewählt
     * und eine entsprechende Trankkarte zurückgegeben.
     * Falls die Liste der verfügbaren Tränke nicht korrekt initialisiert wurde, wird eine Fehlermeldung ausgegeben.
     *
     * @return Eine Trankkarte, die dem zufällig ausgewählten Trank entspricht.
     * Falls ein Fehler auftritt, kann die Methode in einem fehlerhaften Zustand enden.
     */
    public static Potion generatePotion() {
        List<Class<? extends Potion>> availablePotions = Model.potions();
        int randomNumber = rnd.nextInt(availablePotions.size());
        try {
            return availablePotions.get(randomNumber).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LoggingAssistant.log(e, Color.RED);
            return null;
        }
    }

    public static Potion potionFor(String potionName) {
        Potion potion = null;
        try {
            potion = Model.ofPotions(potionName);
        } catch (IllegalArgumentException e) {
            LoggingAssistant.log("Error in DeckFactory creating Potion: " + potionName, Color.RED);
        }
        return potion;
    }

    private PotionFactory() {
    }

}
