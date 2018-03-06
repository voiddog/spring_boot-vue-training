package org.voiddog.spring.test;

import java.lang.reflect.Field;

public class DogObjectUtils {
    public static void copyPropIfNotNull(Object from, Object to) {
        if (from == null || to == null) {
            return;
        }
        Field[] fields = from.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean oldAccessible= field.isAccessible();
            field.setAccessible(true);
            try {
                Field toField = to.getClass().getDeclaredField(field.getName());
                boolean oldToAccessible = toField.isAccessible();
                toField.setAccessible(true);
                Object value = field.get(from);
                if (value != null) {
                    toField.set(to, value);
                }
                toField.setAccessible(oldToAccessible);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
            field.setAccessible(oldAccessible);
        }
    }
}
