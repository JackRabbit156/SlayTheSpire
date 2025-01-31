package de.bundeswehr.auf.slaythespire.helper;

import java.util.Collection;

public class TestHelper {

    public static <T> Class<? extends T>[] toArray(Collection<Class<? extends T>> classes) {
        Class[] result = new Class[classes.size()];
        int i = 0;
        for (Class<? extends T> aClass : classes) {
            result[i++] = aClass;
        }
        return result;
    }

}
