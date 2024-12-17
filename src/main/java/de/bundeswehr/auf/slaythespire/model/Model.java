package de.bundeswehr.auf.slaythespire.model;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.potion.structure.PotionCard;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Model {

    public static final String DEFAULT_PACKAGE = "de.bundeswehr.auf.slaythespire.model";
    public static final String CARD = ".card.";
    public static final String POTION = ".potion";
    public static final String RELIC = ".relic";

    public static List<Class<? extends Card>> cards(String packageName) {
        Reflections reflections = new Reflections(DEFAULT_PACKAGE + CARD + packageName);
        return new ArrayList<>(reflections.getSubTypesOf(Card.class));
    }

    @SuppressWarnings("unchecked")
    public static <C extends Card> C ofCards(String packageName, String name) {
        Reflections reflections = new Reflections(DEFAULT_PACKAGE + CARD + packageName);
        Set<Class<? extends Card>> allClasses = reflections.getSubTypesOf(Card.class);
        for (Class<? extends Card> cls : allClasses) {
            if (cls.getSimpleName().equals(name)) {
                try {
                    return (C) cls.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    LoggingAssistant.log(e, Color.RED);
                    LoggingAssistant.debug(e, Color.RED);
                }
            }
        }
        throw new IllegalArgumentException(packageName + " Card unknown: " + name);
    }

    @SuppressWarnings("unchecked")
    public static <P extends PotionCard> P ofPotions(String name) {
        Reflections reflections = new Reflections(DEFAULT_PACKAGE + POTION);
        Set<Class<? extends PotionCard>> allClasses = reflections.getSubTypesOf(PotionCard.class);
        for (Class<? extends PotionCard> cls : allClasses) {
            if (cls.getSimpleName().equals(name)) {
                try {
                    return (P) cls.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    LoggingAssistant.log(e, Color.RED);
                    LoggingAssistant.debug(e, Color.RED);
                }
            }
        }
        throw new IllegalArgumentException("Potion unknown: " + name);
    }

    @SuppressWarnings("unchecked")
    public static <R extends Relic> R ofRelics(String name) {
        Reflections reflections = new Reflections(DEFAULT_PACKAGE + RELIC);
        Set<Class<? extends Relic>> allClasses = reflections.getSubTypesOf(Relic.class);
        for (Class<? extends Relic> cls : allClasses) {
            if (cls.getSimpleName().equals(name)) {
                try {
                    return (R) cls.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    LoggingAssistant.log(e, Color.RED);
                    LoggingAssistant.debug(e, Color.RED);
                }
            }
        }
        throw new IllegalArgumentException("Relic unknown: " + name);
    }

    public static List<Class<? extends PotionCard>> potions() {
        Reflections reflections = new Reflections(DEFAULT_PACKAGE + POTION);
        return new ArrayList<>(reflections.getSubTypesOf(PotionCard.class));
    }

    public static List<Class<? extends Relic>> relics() {
        Reflections reflections = new Reflections(DEFAULT_PACKAGE + RELIC);
        return new ArrayList<>(reflections.getSubTypesOf(Relic.class));
    }

}
