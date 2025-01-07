package de.bundeswehr.auf.slaythespire.model;

import de.bundeswehr.auf.slaythespire.model.card.CheaterCard;
import de.bundeswehr.auf.slaythespire.model.card.CheaterDefendCard;
import de.bundeswehr.auf.slaythespire.model.card.CheaterEnergyCard;
import de.bundeswehr.auf.slaythespire.model.card.CheaterHealCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.potion.CheaterPotion;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ModelInitializer {

    private static final String CARD = ".card.";
    private static final String DEFAULT_PACKAGE = "de.bundeswehr.auf.slaythespire.model";
    private static final String POTION = ".potion";
    private static final String RELIC = ".relic";

    public static void initModel() {
        try {
            Map<String, Set<Class<? extends Card>>> cardCache = new HashMap<>();
            loadCardClasses(cardCache, "ironclad");
            cardCache.get("ironclad").add(CheaterCard.class);
            cardCache.get("ironclad").add(CheaterDefendCard.class);
            cardCache.get("ironclad").add(CheaterEnergyCard.class);
            cardCache.get("ironclad").add(CheaterHealCard.class);
            loadCardClasses(cardCache, "silent");
            Field cardCacheField = Model.class.getDeclaredField("cardCache");
            cardCacheField.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(cardCacheField, cardCacheField.getModifiers() & ~Modifier.FINAL);
            cardCacheField.set(Model.class, cardCache);

            Set<Class<? extends Potion>> potionCache = loadPotionClasses();
            potionCache.add(CheaterPotion.class);
            Field potionCacheField = Model.class.getDeclaredField("potionCache");
            potionCacheField.setAccessible(true);
            potionCacheField.set(Model.class, potionCache);

            Set<Class<? extends Relic>> relicCache = loadRelicClasses();
            Field relicCacheField = Model.class.getDeclaredField("relicCache");
            relicCacheField.setAccessible(true);
            relicCacheField.set(Model.class, relicCache);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void loadCardClasses(Map<String, Set<Class<? extends Card>>> cardCache, String key) {
        Reflections reflections = new Reflections(DEFAULT_PACKAGE + CARD + key);
        cardCache.put(key, reflections.getSubTypesOf(Card.class).stream()
                .filter(cls -> !Modifier.isAbstract(cls.getModifiers()))
                .collect(Collectors.toSet()));
    }

    private static Set<Class<? extends Potion>> loadPotionClasses() {
        Reflections reflections = new Reflections(DEFAULT_PACKAGE + POTION);
        return reflections.getSubTypesOf(Potion.class).stream()
                .filter(cls -> !Modifier.isAbstract(cls.getModifiers()))
                .collect(Collectors.toSet());
    }

    private static Set<Class<? extends Relic>> loadRelicClasses() {
        Reflections reflections = new Reflections(DEFAULT_PACKAGE + RELIC);
        return reflections.getSubTypesOf(Relic.class).stream()
                .filter(cls -> !Modifier.isAbstract(cls.getModifiers()))
                .collect(Collectors.toSet());
    }

}
