package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.model.Model;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import org.mockito.internal.util.collections.Sets;
import org.reflections.Reflections;

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
        Map<String, Set<Class<? extends Card>>> cardCache = new HashMap<>();
        loadCardClasses(cardCache, "ironclad");
        cardCache.get("ironclad").add(CheaterCard.class);
        loadCardClasses(cardCache, "silent");
        Set<Class<? extends Potion>> potionCache = loadPotionClasses();
        potionCache.add(CheaterPotion.class);
        Set<Class<? extends Relic>> relicCache = loadRelicClasses();
        Model.load(cardCache, potionCache, relicCache);
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
