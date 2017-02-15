package cheanxin.util;

import java.lang.reflect.Field;

/**
 * Created by Jawinton on 2017/2/15.
 */
public class ReflectUtil {

    // merge origin's not null attributes to destination.
    public static final <T> void mergeObject(T origin, T destination) throws IllegalAccessException {
        if (origin == null || destination == null) return;
        if (!origin.getClass().equals(destination.getClass())) return;

        Field[] fields = origin.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Object value = fields[i].get(origin);
            if (null != value) {
                fields[i].set(destination, value);
            }
            fields[i].setAccessible(false);
        }
    }
}
